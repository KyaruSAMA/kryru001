package com.hwua.erhai.servlet.impl;

import com.hwua.erhai.dao.IUserDao;
import com.hwua.erhai.dao.impl.UserDaoImpl;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.util.List;

public class UserService implements IUserService {
    private final IUserDao userDao=new UserDaoImpl();
    @Override
    public int countUser(List<QueryCondition> conditions) {
        return 0;
    }

    @Override
    public List<User> queryUser(List<QueryCondition> conditions, int limit, int offset) {
        return null;
    }

    @Override
    public User login(String userName, String password, int type) {
        return null;
    }

    @Override
    public User addAndReturnUser(User user) {
        return null;
    }

    @Override
    public User updateAndReturnUser(User user) {
        return null;
    }

    @Override
    public User login(String userName, String password) {
        User user =userDao.queryUser(userName,password);

            if (user.getUserName().equals(userName)&&user.getPassword().equals(password)){
                return user;
            }

        return null;
    }

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public User updateAndReturnCar(User user) {
        return null;
    }

    @Override
    public User deleteUser(long userId) {
        return null;
    }
}
