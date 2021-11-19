package com.hwua.erhai.controller;

import com.hwua.erhai.entity.Car;
import com.hwua.erhai.model.MCar;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.impl.MockCarService;
import com.hwua.erhai.servlet.query.QueryCondition;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CarDetailServletServlet", value = "/carDetail")
public class CarDetailServletServlet extends HttpServlet {
    ICarService carService=new MockCarService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String carId=request.getParameter("carId");
        List<QueryCondition> queryConditionList=new ArrayList<>();
        queryConditionList.add(new QueryCondition("carId",carId));
        List<Car>carList=carService.queryCars(queryConditionList,1,0);
        if (carList==null ||carList.size()==0){
            request.setAttribute("message","找不到该汽车");
            request.getRequestDispatcher("/carFoundError.jsp").forward(request,response);
            return;
        }
        if (carList.size()>1){
            request.setAttribute("message","找到多辆汽车");
            request.getRequestDispatcher("/carFoundError.jsp").forward(request,response);
            return;
        }
        Car car=carList.get(0);
        MCar mcar=new MCar();
        mcar.setId(String.valueOf(car.getId()));
        mcar.setModel(String.valueOf(car.getModel()));
        mcar.setColor(String.valueOf(car.getColor()));
        mcar.setComents(String.valueOf(car.getComments()));
        mcar.setCarNumber(String.valueOf(car.getCarNumber()));
        mcar.setBrand(String.valueOf(car.getBrandName()));
        mcar.setCategory(String.valueOf(car.getCategoryName()));
        mcar.setPrice(String.valueOf(car.getPrice()));
        mcar.setRent(String.valueOf(car.getRent()));
        mcar.setUsable(car.getUsable()==0?"是":"否");
        mcar.setStatus(car.getStatus()==0?"是":"否");
        mcar.setImagePath(String.format("upload/car_%d.img",car.getId()));
        request.setAttribute("mcar",mcar);
        request.getRequestDispatcher("/carDetail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
