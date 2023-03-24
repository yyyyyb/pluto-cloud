package com.pluto.warFrame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward implements Serializable {
    private static final long serialVersionUID = 4654634956331762565L;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer cost;
}
