package com.hwua.erhai.controller;

import com.alibaba.fastjson.JSONObject;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.impl.MockUserService;
import com.hwua.erhai.vo.DoCarDeleteResponse;
import com.hwua.erhai.vo.DoUserDeleteResponse;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DoUserDeleteServlet", value = "/doUserDelete")
public class DoUserDeleteServlet extends HttpServlet {
    IUserService userService=new MockUserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userId=request.getParameter("userId");
        if (StringUtils.isBlank(userId)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("请求参数为空");
            return;
        }
       User user= userService.deleteUser(Long.parseLong(userId));
        if (user==null){
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().write("删除用户失败");
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        DoUserDeleteResponse deleteResponse=new DoUserDeleteResponse(
                HttpServletResponse.SC_OK,"删除用户成功",user.getId()
        );
        response.getWriter().write(JSONObject.toJSONString(deleteResponse));
    }
}
