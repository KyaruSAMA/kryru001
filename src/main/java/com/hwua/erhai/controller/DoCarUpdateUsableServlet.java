package com.hwua.erhai.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.impl.CarService;
import com.hwua.erhai.servlet.impl.MockCarService;
import com.hwua.erhai.servlet.query.QueryCondition;
import com.hwua.erhai.vo.DoCarUpdateUsableResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DoCarUpdateUsableServlet", value = "/doCarUpdateUsable")
public class DoCarUpdateUsableServlet extends HttpServlet {
    ICarService CarService=new CarService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String carId=request.getParameter("carId");
        String usable=request.getParameter("usable");
        if(StringUtils.isBlank(carId)||StringUtils.isBlank(usable)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("请求参数为空");
            return;

        }
        List<QueryCondition>queryConditionList=new ArrayList<>();
        queryConditionList.add(new QueryCondition("carId",carId));
        List<Car>carList=CarService.queryCars(queryConditionList,1,0);
        if (carList==null||carList.size()==0){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("找不到该汽车");
            return;
        }
        if (carList.size()>1){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("找到多辆汽车");
            return;
        }
        Car car=carList.get(0);
        car.setUsable("是".equals(usable)?0:1);
        Car newcar=CarService.updateusableAndReturnCar(car);
        if (newcar==null){
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().write("服务器内部错误，更新汽车上下架状态失败");
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        DoCarUpdateUsableResponse updateResponse=new DoCarUpdateUsableResponse(
                HttpServletResponse.SC_OK,"更新汽车上下架状态成功",car.getId(),car.getUsable()
        );
        response.getWriter().write(JSONObject.toJSONString(updateResponse));
    }
    }

