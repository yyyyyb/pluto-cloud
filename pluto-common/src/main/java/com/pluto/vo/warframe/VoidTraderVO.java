package com.pluto.vo.warframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoidTraderVO implements Serializable {
    private static final long serialVersionUID = -4078615420933849012L;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * （expired为false时为距离任务开始时间，true时为任务已开始时间）
     */
    private String startString;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 是否有效
     */
    private Boolean active;

    /**
     * 角色对象
     */
    private String character;

    /**
     * 地点
     */
    private String location;

    /**
     *
     */
    private List<InventoryVO> inventory;

    /**
     * 结束时间
     */
    private String endString;
}
