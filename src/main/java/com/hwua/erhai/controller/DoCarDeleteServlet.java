package com.hwua.erhai.controller;

import com.alibaba.fastjson.JSONObject;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.impl.MockCarService;
import com.hwua.erhai.vo.DoCarDeleteResponse;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DoCarDeleteServlet", value = "/doCarDelete")
public class DoCarDeleteServlet extends HttpServlet {
    ICarService CarService=new MockCarService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");

String carId=request.getParameter("carId");
if (StringUtils.isBlank(carId)){
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    response.getWriter().write("请求参数为空");
    return;
}
        Car car= CarService.deleteCar(Long.parseLong(carId));
if (car==null){
    response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    response.getWriter().write("删除汽车失败");
    return;
}
response.setStatus(HttpServletResponse.SC_OK);
DoCarDeleteResponse deleteResponse=new DoCarDeleteResponse(
        HttpServletResponse.SC_OK,"删除汽车成功",car.getId()
);
response.getWriter().write(JSONObject.toJSONString(deleteResponse));
    }
}
