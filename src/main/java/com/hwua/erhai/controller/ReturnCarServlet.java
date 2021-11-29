package com.hwua.erhai.controller;

import com.hwua.erhai.entity.Record;
import com.hwua.erhai.model.MUser;

import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.impl.CarService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ReturnCarServlet", value = "/returnCar")
public class ReturnCarServlet extends HttpServlet {
    ICarService carService=new CarService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carId = request.getParameter("carId");


        HttpSession session = request.getSession();
        MUser mUser =(MUser)session.getAttribute("mUser");
        String id = mUser.getId();
        Record record = carService.returnCar(Long.parseLong(id), Long.parseLong(carId));

        if (record == null){
            request.setAttribute("result", "failed");
            session.setAttribute("message", "还车失败，请稍后再试");
        }else {
            request.setAttribute("result", "succeed");
            session.setAttribute("message", "还车成功!");
        }
        request.getRequestDispatcher("doReturnCar.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
