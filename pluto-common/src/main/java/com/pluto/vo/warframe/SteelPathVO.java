package com.pluto.vo.warframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * warframe官方钢铁奖励信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SteelPathVO implements Serializable {

    private static final long serialVersionUID = 8021880513574583438L;

    /**
     * 当前奖励
     */
    private SteelPathReWardVO current;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 剩余时间
     */
    private String remaining;

    /**
     * 下一个奖励
     */
    private SteelPathReWardVO next;

    /**
     * 常驻奖励
     */
    private List<SteelPathReWardVO> resident;
}
