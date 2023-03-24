package com.pluto.vo.warframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryVO implements Serializable {

    private static final long serialVersionUID = -8677071153546927502L;

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