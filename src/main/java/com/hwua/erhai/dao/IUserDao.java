package com.hwua.erhai.dao;

import com.hwua.erhai.entity.User;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.util.List;

public interface IUserDao {
    /**
     * 通过用户名,密码,账户类型查找对应的用户
     *
     * @param userName 用户名
     * @param password 密码

     * @return 查询的用户
     */
    User queryUser(String userName, String password);

    /**
     * 通过用户名查找对应的用户
     *
     * @param userName
     * @return 查找到的用户
     */
    User queryUser(String userName);

    /**
     * 添加用户到用户表
     *
     * @param user
     * @return 添加的行数 1：添加成功  0：添加失败
     */
    int addUser(User user);
    List<User> queryUser(List<QueryCondition>conditions);
    int updateUser(User user);
    int deleteUser(Long userId);
}
