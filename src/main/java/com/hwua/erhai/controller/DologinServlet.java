package com.hwua.erhai.controller;

import com.hwua.erhai.entity.User;
import com.hwua.erhai.model.MUser;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.impl.MockUserService;
import com.hwua.erhai.servlet.impl.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/doLogin")
public class DologinServlet extends HttpServlet {
    IUserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username=request.getParameter("username");
            String password=request.getParameter("password");

            HttpSession session=request.getSession();
            session.setMaxInactiveInterval(300);

            User user=userService.login(username,password);
            if (user ==null){
                session.setAttribute("result","failed");
                response.sendRedirect("login.jsp");
                return;
            }
            session.setAttribute("result","succeed");
            session.setAttribute("username",username);

        MUser mUser=new MUser();
        mUser.setId(String.valueOf(user.getId()));
        mUser.setUsername(String.valueOf(user.getUserName()));
        mUser.setPassword(String.valueOf(user.getPassword()));
        mUser.setSex(String.valueOf(user.getSex()));
        mUser.setIdNumber(String.valueOf(user.getIdNumber()));
        mUser.setTel(String.valueOf(user.getTel()));
        mUser.setAddr(String.valueOf(user.getAddr()));
        mUser.setType(user.getType()==1?"管理员":"普通用户");

        session.setAttribute("mUser",mUser);
        response.sendRedirect("carList");
    }
}
