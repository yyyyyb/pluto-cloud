package com.pluto.warFrame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * warframe官方突击信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SteelPath implements Serializable {
    private static final long serialVersionUID = -1871091593925823129L;

    private Reward currentReward;
    private String activation;
    private String expiry;
    private String remaining;
    private List<Reward> rotation;
    private List<Reward> evergreens;

    @Data
    class Reward{
        private String name;
        private Integer cost;
    }
}
