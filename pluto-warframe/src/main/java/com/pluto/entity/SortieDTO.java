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
    @TableField(value = "sortie_date")
    private Date sortieDate;

    /* 地点 */
    @TableField(value = "sortie1_address")
    private String sortie1Address;

    /* 任务 */
    @TableField(value = "sortie1_work")
    private String sortie1Work;

    /* 描述 */
    @TableField(value = "sortie1_desc")
    private String sortie1Desc;

    @TableField(value = "sortie2_address")
    private String sortie2Address;

    @TableField(value = "sortie2_work")
    private String sortie2Work;

    @TableField(value = "sortie2_desc")
    private String sortie2Desc;

    @TableField(value = "sortie3_address")
    private String sortie3Address;

    @TableField(value = "sortie3_work")
    private String sortie3Work;

    @TableField(value = "sortie3_desc")
    private String sortie3Desc;

    /* 头目 */
    @TableField(value = "boss")
    private String boss;

    /* 派系 */
    @TableField(value = "faction")
    private String faction;
}
