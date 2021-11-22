package com.hwua.erhai.controller;

import com.hwua.erhai.common.PageNavUtil;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.model.MCar;
import com.hwua.erhai.model.MPageNav;
import com.hwua.erhai.model.MUser;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.impl.MockCarService;
import com.hwua.erhai.servlet.impl.MockUserService;
import com.hwua.erhai.servlet.query.QueryCondition;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminRecordServlet", value = "/AdminRecordServlet")
public class RecordListServlet extends HttpServlet {
ICarService carService= new MockCarService();
IUserService userService=new MockUserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从客户端请求里读取出与汽车查询条件相关的参数
        String carId=request.getParameter("carId");
        String userId=request.getParameter("userId");

        String page=request.getParameter("page");
        String pageSize=request.getParameter("pageSize");

        List<String> params=new ArrayList<>();
        List<QueryCondition> queryCondition=new ArrayList<>();

        if (StringUtils.isNotEmpty(carId)){params.add(
                String.format("carId=%s",carId));
        queryCondition.add(new QueryCondition("carId",carId));

            if (StringUtils.isNotEmpty(userId)){
                params.add(String.format("userId=%s",userId));
                queryCondition.add(new QueryCondition("userId",userId));
            }
            HttpSession session=request.getSession(false);
            MUser mUser=(MUser)session.getAttribute("mUser");
            if ("普通用户".equals(mUser.getType())){
                queryCondition.add(new QueryCondition("usable","0"));
            }
            String queryParams=String.join("&",params);
            String baseUrl="recordList";
            if (StringUtils.isNotEmpty(queryParams)){
                baseUrl=baseUrl+"?"+queryParams;
            }
            //根据指定的条件，查询符合条件的所有汽车总数
            //相当于select count(*)from t_car where ${queryConditions}
            //获取这个总数的目的，是为了知道分页导航的最大页数是多少，也就是分页导航中尾页的索引号
            int records=carService.countRecord(queryCondition);
            //根据基准url baseUrl,查询页面所以好可以查看页游的，每页的记录数，总计数数，这4个参数
            //生成需要分页导航的model数据，也就是MPpageNav
            //MpPageNav mpageNav=pogeNavtion
            MPageNav mPageNav= PageNavUtil.genMPageNav(baseUrl,page,pageSize,records);

            request.setAttribute("mPageNav",mPageNav);
            //根据条件查询出来的queryCondition，以及分导航中指定页面需要查询的数据范围，也就是limit和offset参数
            //查询出页面需要的数据
            List<Record> recordList=carService.queryRecord(queryCondition,Integer.parseInt(mPageNav.limit),Integer.parseInt(mPageNav.offset));
            List<MCar> mCars=new ArrayList<>(recordList.size());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
