package com.pluto.warFrame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * warframe官方执行官信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Archon implements Serializable {

    private static final long serialVersionUID = 1079648583116221003L;

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
     * 奖池
     */
    private String rewardPool;

    /**
     * 任务类型
     */
    private List<Variant> variants;

    /**
     * 任务类型
     */
    private List<Mission> missions;

    /**
     * 头目
     */
    private String boss;

    /**
     * 敌人派系
     */
    private String faction;

    /**
     * 地点
     */
    private Boolean expired;

    /**
     * 已过时间
     */
    private String eta;
}
