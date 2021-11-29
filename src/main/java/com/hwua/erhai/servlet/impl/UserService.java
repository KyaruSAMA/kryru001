package com.hwua.erhai.servlet.impl;

import com.hwua.erhai.dao.IUserDao;
import com.hwua.erhai.dao.impl.UserDaoImpl;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    private final IUserDao userDao=new UserDaoImpl();
    @Override
    public int countUser(List<QueryCondition> conditions) {
        return userDao.queryUser(conditions).size();
    }

    @Override
    public List<User> queryUser(List<QueryCondition> conditions, int limit, int offset) {

        List<User>copyUsers=userDao.queryUser(conditions);
        //select * from recordList where CarId="" and userId="" ;

        if (copyUsers.size()==0){
            return copyUsers;
        }
        //limit ${limit},${offset}
        int fromIndex=offset;
        if (fromIndex>=copyUsers.size()){
            fromIndex=copyUsers.size()-1;
        }
        int toIndex=offset+limit;
        if (toIndex>copyUsers.size()){
            toIndex=copyUsers.size();
        }
        return copyUsers.subList(fromIndex,toIndex);
    }

    @Override
    public User login(String userName, String password, int type) {
        return null;
    }

    @Override
    public User addAndReturnUser(User user) {
      List<QueryCondition>conditions=new ArrayList<>();
        boolean exist =false;
        boolean exist1=false;
        for (User u:userDao.queryUser(conditions)){
            if (u.getId()==user.getId()){
                exist=true;
                break;
            }else if (u.getUserName().equals(user.getUserName())){
                exist1=true;
                break;
            }
        }
        if (exist){
            throw new RuntimeException(String.format("user id[%d] 已存在",user.getId()));
        }if (exist1){
            throw new RuntimeException(String.format("username[%s] 已存在",user.getId()));
        }
        //TODO:brandId和categoryId需要通过分别根据brandname和categoryname从数据库里查询得到。
        //之后将这两个id设置到car对象里即可
        userDao.addUser(user);
        user.setId(userDao.queryUser(user.getUserName()).getId());
        return user;
    }

    @Override
    public User updateAndReturnUser(User user) {
        List<QueryCondition>conditions=new ArrayList<>();



        //TODO:brandId和categoryId需要通过分别根据brandname和categoryname从数据库里查询得到。
        //之后将这两个id设置到car对象里即可
        userDao.updateUser(user);
        user.setId(userDao.queryUser(user.getUserName()).getId());
        return user;
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
    public User register(User user) {
        List<QueryCondition>conditions=new ArrayList<>();
        boolean exist =false;
        boolean exist1=false;
        for (User u:userDao.queryUser(conditions)){
            if (u.getId()==user.getId()){
                exist=true;
                break;
            }else if (u.getUserName().equals(user.getUserName())){
                exist1=true;
                break;
            }
        }
        if (exist){
            throw new RuntimeException(String.format("user id[%d] 已存在",user.getId()));
        }if (exist1){
            throw new RuntimeException(String.format("username[%s] 已存在",user.getId()));
        }
        //TODO:brandId和categoryId需要通过分别根据brandname和categoryname从数据库里查询得到。
        //之后将这两个id设置到car对象里即可
        userDao.addUser(user);
        user.setId(userDao.queryUser(user.getUserName()).getId());
        return user;
    }

    @Override
    public User updateAndReturnCar(User user) {
        return null;
    }

    @Override
    public int deleteUser(long userId) {
        int i = userDao.deleteUser(userId);

        return i;
    }
}
