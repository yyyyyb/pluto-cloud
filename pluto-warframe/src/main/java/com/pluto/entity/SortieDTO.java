package com.pluto.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pluto.SuperEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_sortie")
@AllArgsConstructor
@Builder
public class SortieDTO extends SuperEntity<Long> {

    /**
     * 突击任务日期
     */
    @TableField(value = "sortie_date")
    private Date sortieDate;

    /**
     * 突击任务1地点
     */
    @TableField(value = "sortie1_address")
    private String sortie1Address;

    /**
     * 突击任务1任务
     */
    @TableField(value = "sortie1_work")
    private String sortie1Work;

    /**
     * 突击任务1描述
     */
    @TableField(value = "sortie1_desc")
    private String sortie1Desc;

    /**
     * 突击任务2地点
     */
    @TableField(value = "sortie2_address")
    private String sortie2Address;

    /**
     * 突击任务2任务
     */
    @TableField(value = "sortie2_work")
    private String sortie2Work;

    /**
     * 突击任务2描述
     */
    @TableField(value = "sortie2_desc")
    private String sortie2Desc;

    /**
     * 突击任务3地点
     */
    @TableField(value = "sortie3_address")
    private String sortie3Address;

    /**
     * 突击任务3任务
     */
    @TableField(value = "sortie3_work")
    private String sortie3Work;

    /**
     * 突击任务3描述
     */
    @TableField(value = "sortie3_desc")
    private String sortie3Desc;

    /**
     * 头目
     */
    @TableField(value = "boss")
    private String boss;

    /**
     * 派系
     */
    @TableField(value = "faction")
    private String faction;
}
