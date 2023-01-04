package com.haut.edu.epidemicstatisticsbackend2.service;

import com.haut.edu.epidemicstatisticsbackend2.entity.Ep;
import java.util.List;

public interface IEpService {
    List<Ep> selectAsymptomaticByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectAsymptomaticByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectAsymptomaticByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectEpByTime(String startTime, String endTime);
    List<Ep> selectConfirmedCasesByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectConfirmedCasesByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectConfirmedCasesByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectCuredByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectCuredByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectCuredByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectDeathsByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectDeathsByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime);
    List<Ep> selectDeathsByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime);
    Ep selectEpRaitoByTime(String time);
}