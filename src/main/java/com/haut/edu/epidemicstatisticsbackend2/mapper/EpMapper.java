package com.haut.edu.epidemicstatisticsbackend2.mapper;

import com.haut.edu.epidemicstatisticsbackend2.entity.Ep;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author TYF
 * @since 2022-12-24
 */
public interface EpMapper {

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by accAsymptomatic/accConfirmedCases desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectAsymptomaticByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by date desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectAsymptomaticByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by addAsymptomaticPeople desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectAsymptomaticByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by accConfirmedCases desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectConfirmedCasesByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by date desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectConfirmedCasesByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by addConfirmedCases desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectConfirmedCasesByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by accCured/accConfirmedCases desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectCuredByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by date desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectCuredByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by addCured desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectCuredByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by accDeaths/accConfirmedCases desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectDeathsByPageOrderRaito(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by date desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectDeathsByPageOrderTime(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where date >= #{startTime} and date <= #{endTime} order by addDeaths desc limit #{pageNum}, #{pageSize}")
    List<Ep> selectDeathsByPageOrderNum(Integer pageNum, Integer pageSize, String startTime, String endTime);

    @Select("select * from sys_ep  where deadline >= #{startTime} and date <= #{endTime}")
    List<Ep> selectEpByTime(String startTime, String endTime);

    @Select("select * from sys_ep  where deadline = #{time}")
    Ep selectEpRaitoByTime(String time);
}
