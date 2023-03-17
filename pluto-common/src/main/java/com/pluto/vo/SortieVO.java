package com.pluto.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.pluto.warFrame.entity.Variant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * warframe官方突击信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortieVO implements Serializable {

    private static final long serialVersionUID = -7413567793616900473L;

    /**
     * 突击任务日期
     */
    private Date sortieDate;

    /**
     * 突击任务1地点
     */
    private String sortie1Address;

    /**
     * 突击任务1任务
     */
    private String sortie1Work;

    /**
     * 突击任务1描述
     */
    private String sortie1Desc;

    /**
     * 突击任务2地点
     */
    private String sortie2Address;

    /**
     * 突击任务2任务
     */
    private String sortie2Work;

    /**
     * 突击任务2描述
     */
    private String sortie2Desc;

    /**
     * 突击任务3地点
     */
    private String sortie3Address;

    /**
     * 突击任务3任务
     */
    private String sortie3Work;

    /**
     * 突击任务3描述
     */
    private String sortie3Desc;

    /**
     * 头目
     */
    private String boss;

    /**
     * 派系
     */
    private String faction;
}
