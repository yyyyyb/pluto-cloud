package com.pluto.vo.warframe;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FissureVO implements Serializable {

    private static final long serialVersionUID = -4863186354640332714L;
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
     * 敌人派系
     */
    private String enemy;

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
