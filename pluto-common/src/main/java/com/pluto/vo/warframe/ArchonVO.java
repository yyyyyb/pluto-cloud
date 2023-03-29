package com.pluto.vo.warframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * warframe官方执行官信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchonVO implements Serializable {

    private static final long serialVersionUID = 5631907546425074500L;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 开始时间
     */
    private String startString;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 是否有校
     */
    private Boolean active;

    /**
     * 任务
     */
    private List<MissionVO> missions;

    /**
     * 首领
     */
    private String boss;

    /**
     * 派系
     */
    private String faction;

    /**
     * 剩余时间
     */
    private String eta;
}
