package com.pluto.vo.warframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * warframe官方仲裁信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArbitrationVO implements Serializable {
    private static final long serialVersionUID = -648337815129637320L;

    /**
     * 数据id
     */
    private String id;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 敌人
     */
    private String enemy;

    /**
     * 任务类型
     */
    private String type;

    /**
     * 任务地点
     */
    private String node;

    /**
     * 是否有效
     */
    private Boolean expired;
}
