package com.haut.edu.epidemicstatisticsbackend2.controller;

import com.haut.edu.epidemicstatisticsbackend2.entity.Ep;
import com.haut.edu.epidemicstatisticsbackend2.service.IEpService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TYF
 * @since 2022-12-24
 */
@RestController
@RequestMapping("/ep")
public class EpController {
    @Resource
    private IEpService epService;


    @GetMapping("/asymptomatic/page")
    public Map<String, Object> selectAsymptomaticByPage(@RequestParam Integer pageNum,
                                              @RequestParam Integer pageSize,
                                              @RequestParam(defaultValue = "") String startTime,
                                              @RequestParam(defaultValue = "") String endTime,
                                              @RequestParam(defaultValue = "0") Integer selectChoice) {
        pageNum = (pageNum - 1) * pageSize;
        List<Ep> data;
        if (selectChoice == 1){
            data = epService.selectAsymptomaticByPageOrderTime(pageNum, pageSize, startTime, endTime);
        } else if(selectChoice == 2){
            data = epService.selectAsymptomaticByPageOrderNum(pageNum, pageSize, startTime, endTime);
        } else {
            data = epService.selectAsymptomaticByPageOrderRaito(pageNum, pageSize, startTime, endTime);
        }
        for (Ep ep : data) {
            ep.setAsymptomaticRatio(String.format("%.3f", 1. * ep.getAccAsymptomatic() / ep.getAccConfirmedCases()));
        }
        Integer size = epService.selectEpByTime(startTime, endTime).size();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("size", size);
        return res;
    }

    @GetMapping("/confirmedCases/page")
    public Map<String, Object> selectConfirmedCasesByPage(@RequestParam Integer pageNum,
                                              @RequestParam Integer pageSize,
                                              @RequestParam(defaultValue = "") String startTime,
                                              @RequestParam(defaultValue = "") String endTime,
                                              @RequestParam(defaultValue = "0") Integer selectChoice) {
        pageNum = (pageNum - 1) * pageSize;
        List<Ep> data;
        if (selectChoice == 1){
            data = epService.selectConfirmedCasesByPageOrderTime(pageNum, pageSize, startTime, endTime);
        } else if(selectChoice == 2){
            data = epService.selectConfirmedCasesByPageOrderNum(pageNum, pageSize, startTime, endTime);
        } else {
            data = epService.selectConfirmedCasesByPageOrderRaito(pageNum, pageSize, startTime, endTime);
        }
        Integer size = epService.selectEpByTime(startTime, endTime).size();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("size", size);
        return res;
    }


    @GetMapping("/cured/page")
    public Map<String, Object> selectCuredCasesByPage(@RequestParam Integer pageNum,
                                                          @RequestParam Integer pageSize,
                                                          @RequestParam(defaultValue = "") String startTime,
                                                          @RequestParam(defaultValue = "") String endTime,
                                                          @RequestParam(defaultValue = "0") Integer selectChoice) {
        pageNum = (pageNum - 1) * pageSize;
        List<Ep> data;
        if (selectChoice == 1){
            data = epService.selectCuredByPageOrderTime(pageNum, pageSize, startTime, endTime);
        } else if(selectChoice == 2){
            data = epService.selectCuredByPageOrderNum(pageNum, pageSize, startTime, endTime);
        } else {
            data = epService.selectCuredByPageOrderRaito(pageNum, pageSize, startTime, endTime);
        }
        for (Ep ep : data) {
            ep.setCuredRatio(String.format("%.3f", 1. * ep.getAccCured() / ep.getAccConfirmedCases()));
        }

        Integer size = epService.selectEpByTime(startTime, endTime).size();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("size", size);
        return res;
    }


    @GetMapping("/deaths/page")
    public Map<String, Object> selectDeathsByPage(@RequestParam Integer pageNum,
                                                          @RequestParam Integer pageSize,
                                                          @RequestParam(defaultValue = "") String startTime,
                                                          @RequestParam(defaultValue = "") String endTime,
                                                          @RequestParam(defaultValue = "0") Integer selectChoice) {
        pageNum = (pageNum - 1) * pageSize;
        List<Ep> data;
        if (selectChoice == 1){
            data = epService.selectDeathsByPageOrderTime(pageNum, pageSize, startTime, endTime);
        } else if(selectChoice == 2){
            data = epService.selectDeathsByPageOrderNum(pageNum, pageSize, startTime, endTime);
        } else {
            data = epService.selectDeathsByPageOrderRaito(pageNum, pageSize, startTime, endTime);
        }
        for (Ep ep : data) {
            ep.setDeathsRatio(String.format("%.3f", 1. * ep.getAccDeaths() / ep.getAccConfirmedCases()));
        }

        Integer size = epService.selectEpByTime(startTime, endTime).size();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("size", size);
        return res;
    }

    @GetMapping("/selectAccEpByTime")
    public List<Ep> selectAccEpByTime(@RequestParam String time) {
        Map<String, String> res = GetLastSevenMonths(time);
        return epService.selectEpByTime(res.get("startTime"), res.get("endTime"));
    }

    @GetMapping("/selectAddEpByTime")
    public List<Ep> selectAddEpByTime(@RequestParam String time) {
        Map<String, String> res = GetLastSevenMonths(time);
        return epService.selectEpByTime(res.get("startTime"), res.get("endTime"));
    }

    @GetMapping("/selectAccEpRaitoByTime")
    public Ep selectAccEpRaitoByTime(@RequestParam String time) {
        Integer year = Integer.parseInt(time.substring(0,4));
        Integer month = Integer.parseInt(time.substring(5,7));
        Integer days = Days(year, month);
        time = year + "." + String.format("%02d", month) + "." + String.format("%02d", days);
        Ep ep = epService.selectEpRaitoByTime(time);
        ep.setAsymptomaticRatio(String.format("%.3f", 1. * ep.getAccAsymptomatic() / ep.getAccConfirmedCases()));
        ep.setCuredRatio(String.format("%.3f", 1. * ep.getAccCured() / ep.getAccConfirmedCases()));
        ep.setDeathsRatio(String.format("%.3f", 1. * ep.getAccDeaths() / ep.getAccConfirmedCases()));
        return ep;
    }

    @GetMapping("/selectAddEpRaitoByTime")
    public Ep selectAddEpRaitoByTime(@RequestParam String time) {
        Integer year = Integer.parseInt(time.substring(0,4));
        Integer month = Integer.parseInt(time.substring(5,7));
        Integer days = Days(year, month);
        time = year + "." + String.format("%02d", month) + "." + String.format("%02d", days);
        Ep ep = epService.selectEpRaitoByTime(time);
        ep.setAsymptomaticRatio(String.format("%.3f", 1. * ep.getAddAsymptomatic() / ep.getAccConfirmedCases()));
        ep.setCuredRatio(String.format("%.3f", 1. * ep.getAddCured() / ep.getAccConfirmedCases()));
        ep.setDeathsRatio(String.format("%.3f", 1. * ep.getAddDeaths() / ep.getAccConfirmedCases()));
        return ep;
    }

    @GetMapping("/selectEpInLastYear")
    public Ep selectEpInLastYear(@RequestParam String time) {
        Integer year = Integer.parseInt(time.substring(0,4));
        Integer month = Integer.parseInt(time.substring(5,7));
        Integer days = Days(year - 1, month);
        Ep ep = epService.selectEpRaitoByTime(year - 1  + "." + String.format("%02d", month) + "." + String.format("%02d", days));
        return ep;
    }

    private Map<String, String> GetLastSevenMonths(String time) {
        Map<String, String> res = new HashMap<>();
        String startTime = "";
        String endTime = "";
        Integer year = Integer.parseInt(time.substring(0,4));
        Integer month = Integer.parseInt(time.substring(5,7));
        Integer day;
        if (year == 2020 && month <= 4){
            startTime = "2020.01.31";
            endTime = "2020.07.31";
        }
        else if (year == 2022 && month >= 5){
            startTime = "2022.02.28";
            endTime = "2022.08.31";
        }
        else {
            if (month <= 3) {
                endTime = year  + "." + String.format("%02d", month + 3) + "." + String.format("%02d", Days(year, month + 3));
                year --;
                month = month - 3 + 12;
                startTime = year + "." + String.format("%02d", month) + "." + String.format("%02d", Days(year, month));
            } else if (month >= 10) {
                System.out.println("month" + month);
                System.out.println(String.format("%02d", month - 3));

                startTime = year + "." + String.format("%02d", month - 3) + "." + String.format("%02d", Days(year, month - 3));
                year ++;
                month = month - 12 + 3;
                endTime = year  +"."+ String.format("%02d", month) + "." + String.format("%02d", Days(year, month));
            } else {
                startTime = year + "." + String.format("%02d", month - 3) + "." + String.format("%02d", Days(year, month - 3));
                endTime = year  + "." + String.format("%02d", month + 3) + "." + String.format("%02d", Days(year, month + 3));
            }
        }
        res.put("startTime", startTime);
        res.put("endTime", endTime);
        return res;
    }

    private Boolean isLeapYear(Integer year) {
        return year % 4 == 0 && year % 100 == 0 || year % 400 == 0;
    }

    private Integer Days(Integer year, Integer month) {
        Integer days = 0;
        if (month == 2){
            if (isLeapYear(year)) {
                days = 29;
            } else {
                days = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        } else {
            days = 31;
        }
        return days;
    }

}
