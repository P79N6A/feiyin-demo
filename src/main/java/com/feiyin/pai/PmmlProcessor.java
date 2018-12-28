package com.feiyin.pai;

import com.alibaba.fastjson.JSON;
import com.google.common.reflect.TypeToken;
import org.dmg.pmml.DataType;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.model.PMMLUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

/**
 * @author 非音
 * @date 2018/12/27 - 上午11:20
 */
public class PmmlProcessor {

    private String modelName;
    private String modelConfig;
    private Evaluator evaluator;

    public PmmlProcessor(String modelName, String modelConfig) {
        this.modelName = modelName;
        this.modelConfig = modelConfig;
        System.out.println("JVM: modelName: " + modelName);
        System.out.println("JVM: modelConfig: " + modelConfig);
    }

    public void Load() {
        try {
            File pmmlFile = new File(modelName);
            InputStream is = new FileInputStream(pmmlFile);
            PMML pmml = PMMLUtil.unmarshal(is);
            ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
            ModelEvaluator<?> modelEvaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
            evaluator = (Evaluator)modelEvaluator;
        } catch (javax.xml.bind.UnmarshalException e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to parse the model file, please check the format of your pmml model file");
        } catch (UnsupportedFeatureException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot support this model type");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to parse the model file");
        }
        System.out.println("JVM: Load Model Done!");
    }

    private String ResponseErrorMsg(String msg) {
        return "{\"error\":\"true\", \"message\":\"" + msg + "\"}";
    }

    public String Process(String input) {
        if (input == null || input.length() == 0) {
            throw new RuntimeException(ResponseErrorMsg("input should not be empty"));
        }

        try {
            List<HashMap<String, Serializable>> inputRecords = JSON.parseObject(input,
                new TypeToken<List<HashMap<String, Serializable>>>() {}.getType());
            ArrayList<HashMap<String, ?>> output = new ArrayList<HashMap<String, ?>>();
            List<InputField> inputFields = evaluator.getInputFields();
            List<OutputField> outputFields = evaluator.getOutputFields();
            for (int i = 0; i < inputRecords.size(); i++) {
                Map<FieldName, FieldValue> arguments = new LinkedHashMap<FieldName, FieldValue>();
                HashMap<String, Serializable> inputRecord = inputRecords.get(i);
                for (InputField inputField : inputFields) {
                    String featureName = inputField.getName().getValue();
                    if (inputRecord.containsKey(featureName)) {
                        DataType inputDataType = inputField.getDataType();
                        if (inputDataType == DataType.BOOLEAN) {
                            boolean value = Boolean.parseBoolean(inputRecord.get(featureName).toString());
                            FieldValue inputFieldValue = inputField.prepare(value);
                            arguments.put(inputField.getName(), inputFieldValue);
                        } else if (inputDataType == DataType.DOUBLE) {
                            double value = Double.parseDouble(inputRecord.get(featureName).toString());
                            FieldValue inputFieldValue = inputField.prepare(value);
                            arguments.put(inputField.getName(), inputFieldValue);
                        } else if (inputDataType == DataType.FLOAT) {
                            float value = Float.parseFloat(inputRecord.get(featureName).toString());
                            FieldValue inputFieldValue = inputField.prepare(value);
                            arguments.put(inputField.getName(), inputFieldValue);
                        } else if (inputDataType == DataType.INTEGER) {
                            int value = Integer.parseInt(inputRecord.get(featureName).toString());
                            FieldValue inputFieldValue = inputField.prepare(value);
                            arguments.put(inputField.getName(), inputFieldValue);
                        } else if (inputDataType == DataType.STRING) {
                            String value = inputRecord.get(featureName).toString();
                            FieldValue inputFieldValue = inputField.prepare(value);
                            arguments.put(inputField.getName(), inputFieldValue);
                        } else {
                            FieldValue inputFieldValue = inputField.prepare(inputRecord.get(featureName));
                            arguments.put(inputField.getName(), inputFieldValue);
                        }
                    }
                }
                Map<FieldName, ?> results = evaluator.evaluate(arguments);
                HashMap<String, Object> jsonResult = new HashMap<String, Object>();
                for (OutputField outputField : outputFields) {
                    FieldName outputFieldName = outputField.getName();
                    jsonResult.put(outputFieldName.getValue(), results.get(outputFieldName));
                }
                output.add(jsonResult);
            }

            String outputJson = JSON.toJSONString(output);
            return outputJson;
        } catch (com.alibaba.fastjson.JSONException e) {
            throw new RuntimeException(ResponseErrorMsg("input json format parse error: " + e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseErrorMsg(e.getMessage()));
        }
    }

    public static void main(String[] args) {
        PmmlProcessor pred = new PmmlProcessor("/Users/xiyang.qxy/Downloads/逻辑回归二分类(基础-稠密和稀疏)-1-Model.pmml", "");
        pred.Load();
        String input ="[{\"pm10\":0.0, \"so2\":0.0, \"co\":0.0, \"no2\":0.0}]";
        String result = pred.Process(input);
        System.out.println(result);
    }
}
