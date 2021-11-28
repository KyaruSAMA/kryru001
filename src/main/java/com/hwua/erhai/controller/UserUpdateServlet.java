package com.hwua.erhai.controller;


import com.hwua.erhai.entity.User;

import com.hwua.erhai.model.MUser;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.impl.MockUserService;
import com.hwua.erhai.servlet.impl.UserService;
import com.hwua.erhai.servlet.query.QueryCondition;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserUpdateServlet", value = "/userUpdate")
public class UserUpdateServlet extends HttpServlet {
    IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");

        List<QueryCondition> queryConditionList=new ArrayList<>();
        queryConditionList.add(new QueryCondition("userId",userId));
        List<User>userList = userService.queryUser(queryConditionList,1,0);
        if (userList==null||userList.size()==0){
            request.setAttribute("message","找不到该用户");
            request.getRequestDispatcher("/userFoundError.jsp").forward(request,response);
            return;
        }
        if (userList.size()>1){
            request.setAttribute("message","找到多位用户");
            request.getRequestDispatcher("/userFoundError.jsp").forward(request,response);
            return;
        }
        User user = userList.get(0);
        MUser mUser = new MUser();
        mUser.setId(String.valueOf(user.getId()));
        mUser.setUsername(String.valueOf(user.getUserName()));
        mUser.setPassword(String.valueOf(user.getPassword()));
        mUser.setSex(String.valueOf(user.getSex()));
        mUser.setIdNumber(String.valueOf(user.getIdNumber()));
        mUser.setTel(String.valueOf(user.getTel()));
        mUser.setAddr(String.valueOf(user.getAddr()));
        mUser.setImagePath(String.format("upload/user_%d.img",user.getId()));

        request.setAttribute("mUser",mUser);

        request.getRequestDispatcher("/userUpdate.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
