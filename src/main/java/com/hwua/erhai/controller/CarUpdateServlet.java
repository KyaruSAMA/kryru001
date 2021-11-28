package com.hwua.erhai.controller;

import com.hwua.erhai.entity.Car;
import com.hwua.erhai.model.MCar;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.impl.CarService;
import com.hwua.erhai.servlet.impl.MockCarService;
import com.hwua.erhai.servlet.query.QueryCondition;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CarUpdateServlet", value = "/carUpdate")
public class CarUpdateServlet extends HttpServlet {
    ICarService carService=new CarService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String carId=request.getParameter("carId");
        List<QueryCondition> queryConditionList=new ArrayList<>();
        queryConditionList.add(new QueryCondition("carId",carId));
        List<Car>carList =carService.queryCars(queryConditionList,1,0);
        if (carList==null||carList.size()==0){
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
        MCar mCar=new MCar();
        mCar.setId(String.valueOf(car.getId()));
        mCar.setModel(String.valueOf(car.getModel()));
        mCar.setColor(String.valueOf(car.getColor()));
        mCar.setComments(String.valueOf(car.getComments()));
        mCar.setCarNumber(String.valueOf(car.getCarNumber()));
        mCar.setBrand(String.valueOf(car.getBrandName()));
        mCar.setCategory(String.valueOf(car.getCategoryName()));
        mCar.setPrice(String.valueOf(car.getPrice()));
        mCar.setRent(String.valueOf(car.getRent()));
        mCar.setUsable(car.getUsable()==0?"是":"否");
        mCar.setStatus(car.getStatus()==0?"是":"否");
        mCar.setImagePath(String.format("upload/car_%d.img",car.getId()));
        request.setAttribute("mCar",mCar);
    request.getRequestDispatcher("/carUpdate.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
