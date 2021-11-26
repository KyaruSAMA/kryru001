<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/23
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>记录列表</title>
    <link href="static/libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>
    <style>
        #left {
            padding: 0;
        }

        #left ul li {
            width: 100%;
        }

        #left ul li a {
            font-size: large;
            letter-spacing: 5px;
            border-radius: 0;
            border: 1px solid white;
        }
        #main a,#main a:hover,#main a:focus,#main a:visited,#main a:active{
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container" id="container" style="width: 100%;">
    <div class="row clearfix" id="head">
        <div class="col-md-12 column" style="line-height:100px;text-align:center;
            font-size: xxx-large;color:gold;background-color: lightseagreen;">
            二嗨租车
        </div>
    </div>
    <div class="row clearfix" id="body" style="display: flex;flex-direction: row;min-height: 600px">
        <jsp:include page="left.jsp">
            <jsp:param name="navItem" value="rentList"/>
        </jsp:include>
        <div class="col-md-10 column" id="content">
            <div class="row clearfix">
                <div class="col-md-12 column" id="search" style="background-color: lightskyblue;
                        height: 50px;display: flex;flex-direction: column;justify-content: center">
                    <form class="form-inline" role="form" action="rentList" method="get"
                          style="margin: 0;display: flex;justify-content: flex-end">
                        <div class="form-group">
                            <label for="carId">汽车编号</label>
                            <input class="form-control" id="carId" type="text" name="carId" style="width: 140px"
                                   placeholder="按汽车编号搜索" value="${requestScope.mRecordSearch.carId}">
                        </div>&nbsp;
<c:if test='${"管理员".equals(sessionScope.mUser.type)}'>
                        <div class="form-group" style="margin-left: 10px">
                            <label for="userName">用户名称</label>
                            <input class="form-control" id="userName" type="text" name="userName" style="width: 140px"
                                   placeholder="按用户名称搜索" value="${requestScope.mRecordSearch.userName}">
                        </div>&nbsp;
    &nbsp;</c:if>
                        <button id="resetButton" class="btn btn-default" type="reset"
                                style="margin-left: 10px">重置
                        </button>
                        <button class="btn btn-default" type="submit"
                                style="margin-left: 10px">搜索
                        </button>
                    </form>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-md-12 column" id="main" style="padding: 0">
                    <table class="table table-bordered" style="margin: 20px auto 0 auto; width: 96%;font-size: 14px">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户</th>
                            <th>汽车编号</th>
                            <th>型号</th>
                            <th>简介</th>
                            <th>品牌</th>
                            <th>类型</th>
                            <th>每日租金</th>
                            <th>详情</th>
                            <th>还车</th>
                        </tr>
                        </thead>
                        <tbody>
<c:if test='${"管理员".equals(sessionScope.mUser.type)}'>
                        <c:forEach var="mRecord" items="${requestScope.mRecords}">
                            <tr>
                                <td>${mRecord.id}</td>
                                <td>${mRecord.userName}</td>
                                <td>${mRecord.carId}</td>
                                <td>${mRecord.model}</td>
                                <td>${mRecord.comments}</td>
                                <td>${mRecord.brandName}</td>
                                <td>${mRecord.categoryName}</td>
                                <td>${mRecord.rent}</td>
                                <td><a href="carDetail?carId=${mRecord.carId}">详情</a></td>
                                <c:choose>
                                <c:when test='${"否".equals(mRecord.status)}'>
                                <td><a href="doReturnCar?carId=${mRecord.carId}">还车</a></td>
                                </c:when>
                                <c:when test='${"是".equals(mRecord.status)}'>
                                    <td>已还车</td>
                                </c:when>
                                </c:choose>
                                </tr>
                        </c:forEach>
</c:if>
<c:if test='${"普通用户".equals(sessionScope.mUser.type)}'>
<c:forEach var="mRecord" items="${requestScope.usermRecords}">
    <tr>
        <td>${mRecord.id}</td>
        <td>${mRecord.userName}</td>
        <td>${mRecord.carId}</td>
        <td>${mRecord.model}</td>
        <td>${mRecord.comments}</td>
        <td>${mRecord.brandName}</td>
        <td>${mRecord.categoryName}</td>
        <td>${mRecord.rent}</td>
        <td><a href="carDetail?carId=${mRecord.carId}">详情</a></td>
        <td><a href="doReturnCar?carId=${mRecord.carId}">还车</a></td>
    </tr>
</c:forEach>
</c:if>
                        </tbody>
                    </table>
                    <div style="display: flex;justify-content: center;font-size: 12px;">
                        <ul class="pagination">
                            <li><a href="${requestScope.mPageNav.first.url}">首页</a></li>
                            <li><a href="${requestScope.mPageNav.previous.url}">&laquo;</a></li>
                            <c:forEach var="page" items="${requestScope.mPageNav.pages}">
                                <li class="${page.index.equals(requestScope.mPageNav.currentPageIndex) ?"action" : ""}">
                                    <a href="${page.url}">${page.index}</a>
                                </li>
                            </c:forEach>

                            <li><a href="${requestScope.mPageNav.next.url}">&raquo;</a></li>
                            <li><a href="${requestScope.mPageNav.max.url}">尾页</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix" id="foot">
        <div class="col-md-12 column"
             style="height: 50px;line-height: 50px;text-align: center;background-color: lightskyblue;">
            二嗨租车&copy;版权所有<a href="erhai@erhai.com" style="color: white">erhai@erhai.com</a>
        </div>
    </div>

</div>
<script>
</script>
</body>
</html>
