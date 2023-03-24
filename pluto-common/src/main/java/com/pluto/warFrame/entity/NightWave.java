package com.pluto.warFrame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * warframe官方午夜电波
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NightWave implements Serializable {

    private static final long serialVersionUID = -2833717817082423183L;

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
     * 数量
     */
    private String season;

    /**
     * 任务类型
     */
    private String tag;

    /**
     * ...?
     */
    private Long phase;

    /**
     * ...?
     */
    private Object params;

    /**
     * ...?
     */
    private List<String> possibleChallenges;

    /**
     * 午夜电波挑战
     */
    private List<ActiveChallenge> activeChallenges;

    /**
     * 奖励类型
     */
    private List<String> rewardTypes;
}
