package com.haut.edu.epidemicstatisticsbackend2.service.impl;

import com.haut.edu.epidemicstatisticsbackend2.entity.Ep;
import com.haut.edu.epidemicstatisticsbackend2.mapper.EpMapper;
import com.haut.edu.epidemicstatisticsbackend2.service.IEpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EpServiceImpl implements IEpService {

    @Resource
    private EpMapper epMapper;


    @Override
    public List<Ep> selectAsymptomaticByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectAsymptomaticByPageOrderTime(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectAsymptomaticByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectAsymptomaticByPageOrderNum(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectAsymptomaticByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectAsymptomaticByPageOrderRaito(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectEpByTime(String startTime, String endTime) {
        return epMapper.selectEpByTime(startTime, endTime);
    }

    @Override
    public List<Ep> selectConfirmedCasesByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectConfirmedCasesByPageOrderTime(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectConfirmedCasesByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectConfirmedCasesByPageOrderNum(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectConfirmedCasesByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectConfirmedCasesByPageOrderRaito(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectCuredByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectCuredByPageOrderTime(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectCuredByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectCuredByPageOrderNum(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectCuredByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectCuredByPageOrderRaito(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectDeathsByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectDeathsByPageOrderTime(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectDeathsByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectDeathsByPageOrderNum(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public List<Ep> selectDeathsByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime) {
        return epMapper.selectDeathsByPageOrderRaito(pageNum, pageSize, startTime, endTime);
    }

    @Override
    public Ep selectEpRaitoByTime(String time) {
        return epMapper.selectEpRaitoByTime(time);
    }
}
