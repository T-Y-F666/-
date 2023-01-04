package com.haut.edu.epidemicstatisticsbackend2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ep {
    private String date;
    private String deadline;
    private Integer addAsymptomatic;
    private Integer addConfirmedCases;
    private Integer addCured;
    private Integer addDeaths;
    private Integer accAsymptomatic;
    private Integer accConfirmedCases;
    private Integer accCured;
    private Integer accDeaths;
    private String asymptomaticRatio;
    private String curedRatio;
    private String deathsRatio;
}
