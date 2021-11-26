package com.hwua.erhai.controller;

import com.hwua.erhai.common.PageNavUtil;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.model.MCar;
import com.hwua.erhai.model.MCarSearch;
import com.hwua.erhai.model.MPageNav;
import com.hwua.erhai.model.MUser;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.impl.CarService;
import com.hwua.erhai.servlet.impl.MockCarService;
import com.hwua.erhai.servlet.query.QueryCondition;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CarListServlet", value = "/carList")
public class CarListServlet extends HttpServlet {
   ICarService carService=new CarService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从客户端请求里读取出与汽车查询条件相关的参数
        String carId=request.getParameter("carId");
        String carBrand=request.getParameter("carBrand");
        String carCategory=request.getParameter("carCategory");
        String priceOrder=request.getParameter("priceOrder");
//从客户端请求里读取出分页相关的内容。其中：
        //page代表查询的是第几个分页，从1开始
        //pageSize代表的是每个分页的记录行数
        String page=request.getParameter("page");
        String pageSize=request.getParameter("pageSize");
        //根据汽车的查询条件的参数，生成用于分页基准的url，也就是baseurl
        //整个baseurl会用于生成分页导航中的a标签的href属性，也就是超链接的值
        //同时，还会生成数据库查询sql的条件清单，也就是queryconditions
        List<String>params=new ArrayList<>();
        List<QueryCondition> queryCondition=new ArrayList<>();
        if (StringUtils.isNotEmpty(carId)){
            params.add(String.format("carId=%s",carId));
            queryCondition.add(new QueryCondition("carId",carId));
        }
        if (StringUtils.isNotEmpty(carBrand)){
            params.add(String.format("carBrand=%s",carBrand));
            queryCondition.add(new QueryCondition("carBrand",carBrand));
        }
        if (StringUtils.isNotEmpty(carCategory)){
            params.add(String.format("carCategory=%s",carCategory));
            queryCondition.add(new QueryCondition("carCategory",carCategory));
        }
        if (StringUtils.isNotEmpty(priceOrder)){
            params.add(String.format("priceOrder=%s",priceOrder));
            queryCondition.add(new QueryCondition("priceOrder",priceOrder));
        }
        HttpSession session=request.getSession(false);
        MUser mUser=(MUser)session.getAttribute("mUser");
        if ("普通用户".equals(mUser.getType())){
            queryCondition.add(new QueryCondition("usable","0"));
        }
        String queryParams=String.join("&",params);
        String baseUrl="carList";
        if (StringUtils.isNotEmpty(queryParams)){
            baseUrl=baseUrl+"?"+queryParams;
        }


        //根据指定的条件，查询符合条件的所有汽车总数
        //相当于select count(*)from t_car where ${queryConditions}
        //获取这个总数的目的，是为了知道分页导航的最大页数是多少，也就是分页导航中尾页的索引号
        int records=carService.countCars(queryCondition);
        //根据基准url baseUrl,查询页面所以好可以查看页游的，每页的记录数，总计数数，这4个参数
        //生成需要分页导航的model数据，也就是MPpageNav
        //MpPageNav mpageNav=pogeNavtion
        MPageNav mPageNav= PageNavUtil.genMPageNav(baseUrl,page,pageSize,records);

        request.setAttribute("mPageNav",mPageNav);
        //根据条件查询出来的queryCondition，以及分导航中指定页面需要查询的数据范围，也就是limit和offset参数
        //查询出页面需要的数据
        List<Car> Cars=carService.queryCars(queryCondition,Integer.parseInt(mPageNav.limit),Integer.parseInt(mPageNav.offset));
        List<MCar> mCars=new ArrayList<>(Cars.size());
        for (Car car: Cars
        ) {
            MCar mCar=new MCar();
            mCar.setId(String.valueOf(car.getId()));
            mCar.setModel(String.valueOf(car.getModel()));
            mCar.setComments(String.valueOf(car.getComments()));
            mCar.setBrand(String.valueOf(car.getBrandName()));
            mCar.setCategory(String.valueOf(car.getCategoryName()));
            mCar.setRent(String.format("%.2f/天",car.getRent()));
            mCar.setUsable(car.getUsable()==0?"是":"否");
            mCar.setStatus(car.getUsable()==0?"是":"否");
            mCars.add(mCar);

        }
        //将model数据放入re，之后再jsp中能通过requestScope访问到model数据
        request.setAttribute("mCars",mCars);
        //McarSearch用于保存查询的条件，将其按照查询传入的值，填写到搜索表单中
        MCarSearch mCarSearch=new MCarSearch(carId,carBrand,carCategory,priceOrder);
        request.setAttribute("mCarSearch",mCarSearch);
        //使用forward方法将M和V结合起来，从而生成动态的html页面，并最终返回给客户端浏览器
        //这就是完整的Mvc处理张
        request.getRequestDispatcher("/carList.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
