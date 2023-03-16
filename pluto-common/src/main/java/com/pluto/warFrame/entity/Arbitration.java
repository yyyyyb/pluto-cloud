package com.pluto.warFrame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * warframe官方仲裁信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arbitration implements Serializable {

    private static final long serialVersionUID = 3537051316603832988L;

    /**
     * 数据id
     */
    private String id;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 敌人
     */
    private String enemy;

    /**
     * 任务类型
     */
    private String type;


    /**
     * 任务类型
     */
    private String typeKey;

    /**
     * 是否archWing
     */
    private Boolean archWing;

    /**
     * 是否archWing
     */
    private Boolean sharkWing;

    /**
     * 任务地点
     */
    private String node;

    /**
     * 任务地点
     */
    private String nodeKey;

    /**
     * 是否游戏
     */
    private Boolean expired;

}
