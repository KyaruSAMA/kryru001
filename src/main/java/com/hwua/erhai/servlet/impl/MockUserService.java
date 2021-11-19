package com.hwua.erhai.servlet.impl;

import com.hwua.erhai.entity.User;
import com.hwua.erhai.servlet.IUserService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class MockUserService implements IUserService {
    private static final AtomicLong User_ID=new AtomicLong(-1);
    private static final List<User> USER_TABLE= Arrays.asList(
            new User(User_ID.addAndGet(1),"admin","admin",0,
                    "400004198901010100","13612345678","广西北海",1),
            new User(User_ID.addAndGet(1),"zhangsan","123456",0,
                    "500004198901010100","13012345678","广西北海",1),
            new User(User_ID.addAndGet(1),"aa","123",0,
                    "600004198901010100","15012345678","广西北海",1)
    );
    private static User copyUser(User user){
        if (user==null){
            return null;
        }
        User newUser= new User();
        newUser.setId(user.getId());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setSex(user.getSex());
        newUser.setIdNumber(user.getIdNumber());
        newUser.setTel(user.getTel());
        newUser.setAddr(user.getAddr());
        newUser.setType(user.getType());
        return newUser;
    }
    @Override
    public User login(String userName, String password, int type) {
        return null;
    }

    @Override
    public User login(String userName, String password) {
        for (User user :USER_TABLE
        ) {
            if (user.getUserName().equals(userName)&&user.getPassword().equals(password)){
                return copyUser(user);
            }
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        return false;
    }
}
