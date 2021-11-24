package com.hwua.erhai.servlet;

import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.util.List;

public interface IUserService {
    int countUser(List<QueryCondition>conditions);
    List<User>queryUser(List<QueryCondition> conditions, int limit , int offset);
    /**
     * 登录业务
     *
     * @param userName 用户名
     * @param password 密码
     * @param type     账户类型 0:普通用户  1:管理员
     * @return 查找到的用户 如果没有返回null,如果有,返回对应的对象
     */
    User login(String userName, String password,
               int type);
    User addAndReturnUser(User user);
    User updateAndReturnUser(User user);
    User login(String userName, String password);
    /**
     * 注册业务
     *
     * @param user 注册的用户信息
     * @return 是否注册成功 true:成功  false:失败
     */
    boolean register(User user);
    User updateAndReturnCar(User user);
    User deleteUser(long userId);

}
