package com.feiyin.service;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 非音
 * @date 2018/9/28 - 下午11:00
 */
@Data
public class XSpaceDO implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 系统创建者
         */
        public static final Long SYSTEM_CREATOR = -99L;
        /**
         * 主键
         */
        private Long id;

        /**
         * 创建时间
         */
        private java.util.Date gmtCreate;

        /**
         * 修改时间
         */
        private java.util.Date gmtModified;

        /**
         * 租户ID
         */
        private Long buId;

        /**
         * 用户ID
         */
        private Long userId;

        /**
         * 角色ID
         */
        private Long roleId;

        /**
         * 资源数据
         */
        private String resourceData;

        /**
         * 创建人
         */
        private Long creatorId;

        /**
         * 失效时间
         */
        private Date expireTime;

        /**
         * 激活时间
         */
        private Date activateTime;

        /***
         * 资源类型
         */
        private Integer roleResourceType;

}
