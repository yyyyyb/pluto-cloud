package com.pluto.warFrame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * warframe官方虚空商人信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    /**
     * 物品名称
     */
    private String item;

    /**
     * 兑换所需杜卡德金币
     */
    private String ducats;

    /**
     * 兑换所需星币
     */
    private String credits;
}
