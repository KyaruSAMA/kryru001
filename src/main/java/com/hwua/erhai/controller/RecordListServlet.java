package com.hwua.erhai.controller;

import com.hwua.erhai.common.PageNavUtil;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.model.*;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.impl.CarService;
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

@WebServlet(name = "AdminRecordServlet", value = "/rentList")
public class RecordListServlet extends HttpServlet {
    ICarService carService=new CarService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从客户端请求里读取出与汽车查询条件相关的参数
        HttpSession session=request.getSession(false);
        MUser mUser=(MUser)session.getAttribute("mUser");
        String carId=request.getParameter("carId");
        String userName=request.getParameter("userName");

        String page=request.getParameter("page");
        String pageSize=request.getParameter("pageSize");

        List<String> params=new ArrayList<>();
        List<QueryCondition> queryCondition=new ArrayList<>();
        if (StringUtils.isNotEmpty(carId)){
            params.add(
                String.format("carId=%s",carId));
        queryCondition.add(new QueryCondition("carId",carId));}

            if (StringUtils.isNotEmpty(userName)){
                params.add(String.format("userName=%s",userName));
                queryCondition.add(new QueryCondition("userName",userName));
            }
        if ("普通用户".equals(mUser.getType())) {
           queryCondition.add(new QueryCondition("userName",mUser.getUsername()));
        }

            String queryParams=String.join("&",params);
            String baseUrl="rentList";
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
            List<MRecord> mRecords=new ArrayList<>(recordList.size());

            for (Record record:recordList
            ) {
                MRecord mRecord=new MRecord();
                mRecord.setCarId(String.valueOf(record.getCarId()));
                mRecord.setUserId(String.valueOf(record.getUserId()));
                mRecord.setUserName(String.valueOf(record.getUserName()));
                mRecord.setId(String.valueOf(record.getId()));
                mRecord.setStartDate(String.valueOf(record.getStartDate()));
                mRecord.setReturnDate(String.valueOf(record.getReturnDate()));
                mRecord.setPayment(String.valueOf(record.getPayment()));
                mRecord.setStatus(record.getStatus()==0?"是":"否");
                mRecords.add(mRecord);}
                //将model数据放入re，之后再jsp中能通过requestScope访问到model数据
                request.setAttribute("mRecords",mRecords);
                //McarSearch用于保存查询的条件，将其按照查询传入的值，填写到搜索表单中
                MRecordSearch mRecordSearch=new MRecordSearch(carId,userName);
                request.setAttribute("mRecordSearch",mRecordSearch);
                //使用forward方法将M和V结合起来，从而生成动态的html页面，并最终返回给客户端浏览器
                //这就是完整的Mvc处理张




            request.getRequestDispatcher("/rentList.jsp").forward(request,response);
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
