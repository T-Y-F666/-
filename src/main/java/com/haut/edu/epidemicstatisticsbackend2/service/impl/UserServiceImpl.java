package com.haut.edu.epidemicstatisticsbackend2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haut.edu.epidemicstatisticsbackend2.common.Constants;
import com.haut.edu.epidemicstatisticsbackend2.entity.User;
import com.haut.edu.epidemicstatisticsbackend2.exception.ServiceException;
import com.haut.edu.epidemicstatisticsbackend2.mapper.UserMapper;
import com.haut.edu.epidemicstatisticsbackend2.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TYF
 * @since 2022-12-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User login(User user) {
        User res = getUserNameAndPassword(user);
        if (res != null) {
            return res;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(User user) {
        User user1 = getUserNameAndPassword(user);
        if (user1 != null) {
            save(user1);
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return user1;
    }

    private User getUserNameAndPassword(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        User res;
        try {
            res = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return res;
    }
}
