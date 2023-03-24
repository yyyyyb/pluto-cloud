package com.pluto.vo.warframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * warframe官方钢铁奖励信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SteelPathReWardVO implements Serializable {

    private static final long serialVersionUID = 6870594842807219053L;

    /**
     * 奖励名称
     */
    private String name;

    /**
     * 兑换奖励的需要数量
     */
    private Integer cost;
}
