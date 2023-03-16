package com.pluto.warFrame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * warframe官方突击信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Variant implements Serializable {

    private static final long serialVersionUID = 982796961134560278L;

    /**
     * 任务类型
     */
    private String missionType;

    /**
     * 任务难度
     */
    private String modifier;

    /**
     * 任务难度描述
     */
    private String modifierDescription;

    /**
     * 任务地点
     */
    private String node;
}
