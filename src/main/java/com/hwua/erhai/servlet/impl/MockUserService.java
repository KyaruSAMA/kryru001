package com.hwua.erhai.servlet.impl;

import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.util.ArrayList;
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
                    "600004198901010100","15012345678","广西北海",0)
    );
    private static final  List<User> USER_LIST=new ArrayList<>(USER_TABLE);

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
    static private List<User>copyUsers(List<User> userList){
        List<User>users=new ArrayList<>();
        for (User u:USER_LIST){
            users.add(copyUser(u));
        }
        return users;
    }
    static private List<User> select (List<User>userList, List<QueryCondition> conditions){
        List <User>result=new ArrayList<>();
        for (User user:userList){
            boolean selected=true;
            for (QueryCondition condition : conditions) {
                if ("userId".equals(condition.getField())){
                    if (!String.valueOf(user.getId()).equals(condition.getValue())){
                        selected=false;
                        break;
                    }
                }


                else if ("userName".equals(condition.getField())){
                    if (!String.valueOf(user.getUserName()).equals(condition.getValue())){
                        selected=false;
                        break;
                    }
                }
                else if ("Type".equals(condition.getField())){
                    if (!String.valueOf(user.getType()).equals(condition.getValue())){
                        selected=false;
                        break;
                    }
                }
            }
            if (selected){
                result.add(user);
            }
        }

        return result;
    }
    @Override
    public int countUser(List<QueryCondition> conditions) {
        return select(USER_LIST,conditions).size();
    }

    @Override
    public List<User> queryUser(List<QueryCondition> conditions, int limit, int offset) {
        List<User>copyUsers=copyUsers(USER_TABLE);
        //select * from recordList where CarId="" and userId="" ;
        copyUsers=select(copyUsers,conditions);
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
        if (user.getId()==-1){
            long id=User_ID.addAndGet(1);
           user.setId(id);
        }
        boolean exist =false;
        boolean exist1=false;
        for (User u:USER_LIST){
            if (u.getId()==user.getId()){
                exist=true;
                break;
            }else if (u.getUserName()==user.getUserName()){
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
        USER_LIST.add(copyUser(user));
        return user;
    }

    @Override
    public User updateAndReturnUser(User user) {
        for (User u:USER_LIST){
            if (u.getId()==user.getId()){

                u.setUserName(user.getUserName());
                u.setPassword(user.getPassword());
                u.setSex(user.getSex());
                u.setIdNumber(user.getIdNumber());
                u.setTel(user.getTel());
                u.setAddr(user.getAddr());
                u.setType(user.getType());
                return copyUser(u);
            }
        }
        return  null;
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

    @Override
    public User updateAndReturnCar(User user) {
        return null;
    }

    @Override
    public User deleteUser(long userId) {
        int carIndex=-1;
        for (int i=0;i<USER_TABLE.size();i++){
            if (USER_TABLE.get(i).getId()==userId){
                carIndex=i;
                break;
            }
        }
        if (carIndex==-1){
            return null;
        }
        return copyUser(USER_TABLE.remove(carIndex));
    }
}
