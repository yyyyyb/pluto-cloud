package com.pluto.vo.warframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvasionVO implements Serializable {
    private static final long serialVersionUID = -4183055770146772830L;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 已过时间
     *
     */
    private String startString;

    /**
     * 任务地点
     */
    private String node;

    /**
     * 任务描述
     */
    private String desc;

    /**
     * 攻击方信息
     */
    private AttackerVO attacker;

    /**
     * 防守方信息
     */
    private DefenderVO defender;

    /**
     * 对象是否为Infestation
     */
    private Boolean vsInfestation;

    /**
     * 完成度
     */
    private BigDecimal completion;

    /**
     * 是否完成
     */
    private Boolean completed;

    /**
     * 剩余时间
     */
    private String eta;
}
