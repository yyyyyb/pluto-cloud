package com.pluto.vo.warframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * warframe官方午夜电波信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NightWaveVO implements Serializable {
    private static final long serialVersionUID = -2032022390843504127L;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 是否有效
     */
    private Boolean active;

    /**
     * 是否每日任务
     */
    private Boolean isDaily;

    /**
     * 是否精英任务
     */
    private Boolean isElite;

    /**
     * 人物描述
     */
    private String desc;

    /**
     * 任务主题
     */
    private String title;

    /**
     * 人物奖励分数
     */
    private Long reputation;
}
