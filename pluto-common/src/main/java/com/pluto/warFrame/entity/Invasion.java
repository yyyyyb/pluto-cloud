package com.pluto.warFrame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * warframe官方入侵信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invasion implements Serializable {
    private static final long serialVersionUID = -8581808578744831769L;

    private String node;
    private AttackerReward attackerReward;
    private String attackingFaction;
    private DefenderReward defenderReward;
    private String defendingFaction;
    private String completion;
    private String eta;
}
