package com.pluto.warFrame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * warframe官方裂缝信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fissure implements Serializable {

    private static final long serialVersionUID = -6765705305199849599L;

    /**
     * 数据id
     */
    private String id;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 任务总长时间
     */
    private String startString;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 是否有效
     */
    private Boolean active;

    /**
     * 地点
     */
    private String node;

    /**
     * 任务类型
     */
    private String missionType;

    /**
     * 任务类型
     */
    private String missionKey;

    /**
     * 敌人派系
     */
    private String enemy;

    /**
     * 敌人派系
     */
    private String enemyKey;

    /**
     * 地点
     */
    private String nodeKey;

    /**
     * 任务纪元
     */
    private String tier;

    /**
     * 任务纪元序号
     */
    private String tierNum;

    /**
     * 是否过期
     */
    private Boolean expired;

    /**
     * 已过时间
     */
    private String eta;

    /**
     * 是否九重天任务
     */
    private Boolean isStorm;

    /**
     * 是否钢铁任务
     */
    private Boolean isHard;
}
