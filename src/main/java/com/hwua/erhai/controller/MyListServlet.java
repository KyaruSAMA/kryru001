package com.hwua.erhai.controller;

import com.hwua.erhai.common.PageNavUtil;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.model.MPageNav;
import com.hwua.erhai.model.MUser;
import com.hwua.erhai.model.MUserSearch;
import com.hwua.erhai.servlet.IUserService;
import com.hwua.erhai.servlet.impl.MockUserService;
import com.hwua.erhai.servlet.query.QueryCondition;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MyListServlet", value = "/myList")
public class MyListServlet extends HttpServlet {
    IUserService userService=new MockUserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //从客户端请求里读取出与汽车查询条件相关的参数
            String userId=request.getParameter("userId");
            String userName=request.getParameter("userName");
            String type=request.getParameter("type");
//从客户端请求里读取出分页相关的内容。其中：
            //page代表查询的是第几个分页，从1开始
            //pageSize代表的是每个分页的记录行数
            String page=request.getParameter("page");
            String pageSize=request.getParameter("pageSize");
            //根据汽车的查询条件的参数，生成用于分页基准的url，也就是baseurl
            //整个baseurl会用于生成分页导航中的a标签的href属性，也就是超链接的值
            //同时，还会生成数据库查询sql的条件清单，也就是queryconditions
            List<String> params=new ArrayList<>();
            List<QueryCondition> queryCondition=new ArrayList<>();
        HttpSession session=request.getSession(false);
        MUser mUser=(MUser)session.getAttribute("mUser");
        params.add(String.format("userName=%s",userName));
        queryCondition.add(new QueryCondition("userName",mUser.getUsername()));
            String queryParams=String.join("&",params);
            String baseUrl="userList";
            if (StringUtils.isNotEmpty(queryParams)){
                baseUrl=baseUrl+"?"+queryParams;
            }


            //根据指定的条件，查询符合条件的所有汽车总数
            //相当于select count(*)from t_car where ${queryConditions}
            //获取这个总数的目的，是为了知道分页导航的最大页数是多少，也就是分页导航中尾页的索引号
            int records=userService.countUser(queryCondition);
            //根据基准url baseUrl,查询页面所以好可以查看页游的，每页的记录数，总计数数，这4个参数
            //生成需要分页导航的model数据，也就是MPpageNav
            //MpPageNav mpageNav=pogeNavtion
            MPageNav mPageNav= PageNavUtil.genMPageNav(baseUrl,page,pageSize,records);

            request.setAttribute("mPageNav",mPageNav);
            //根据条件查询出来的queryCondition，以及分导航中指定页面需要查询的数据范围，也就是limit和offset参数
            //查询出页面需要的数据
            List<User> Users=userService.queryUser(queryCondition,Integer.parseInt(mPageNav.limit),Integer.parseInt(mPageNav.offset));
            List<MUser> mUsers=new ArrayList<>(Users.size());
            for (User user: Users
            ) {
                MUser user1=new MUser();
                user1.setId(String.valueOf(user.getId()));
                user1.setUsername(String.valueOf(user.getUserName()));
                user1.setPassword(String.valueOf(user.getPassword()));
                user1.setSex(String.valueOf(user.getSex()));
                user1.setTel(String.valueOf(user.getTel()));
                user1.setIdNumber(String.valueOf(user.getIdNumber()));
                user1.setAddr(String.valueOf(user.getAddr()));
                user1.setType(String.valueOf(user.getType()));
                mUser.setImagePath(String.format("upload/user_%d.img",user.getId()));
                mUsers.add(user1);

            }
            //将model数据放入re，之后再jsp中能通过requestScope访问到model数据
            request.setAttribute("mUser",mUsers);

            //使用forward方法将M和V结合起来，从而生成动态的html页面，并最终返回给客户端浏览器
            //这就是完整的Mvc处理张
            request.getRequestDispatcher("/myList.jsp").forward(request,response);
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
