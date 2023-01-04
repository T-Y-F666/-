package com.haut.edu.epidemicstatisticsbackend2.service;

import com.haut.edu.epidemicstatisticsbackend2.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TYF
 * @since 2022-12-24
 */
public interface IUserService extends IService<User> {

    User login(User user);

    User register(User user);
}
