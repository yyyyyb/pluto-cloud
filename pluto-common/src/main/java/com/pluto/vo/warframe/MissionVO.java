package com.pluto.vo.warframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * warframe官方执行官信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionVO implements Serializable {
    private static final long serialVersionUID = -2624345269082590523L;

    /**
     * 任务地点
     */
    private String node;

    /**
     * 任务类型
     */
    private String type;
}
