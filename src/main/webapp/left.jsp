<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-2 column" id="left" style="background-color: lightblue">

    <ul class="nav nav-pills nav-stacked" style="background-color: lightskyblue">

        <li class="${"carList".equals(param.navItem)?"active":""}"><a href="carList">汽车列表</a></li>

        <c:if test='${"管理员".equals(sessionScope.mUser.type)}'>
        <li class="${"carAdd".equals(param.navItem)?"active":""}"><a href="carAdd">汽车管理</a></li>
        </c:if>


        <li class="${"rentList".equals(param.navItem)?"active":""}"><a href="rentList">租车记录</a></li>


        <li class="${"rentManage".equals(param.navItem)?"active":""}"><a href="rentManage">租车管理</a></li>
        <c:choose>
            <c:when test='${"管理员".equals(sessionScope.mUser.type)}'>
                <li class="${"userList".equals(param.navItem)?"active":""}"><a href="userList">用户列表</a></li>
            </c:when>
            <c:otherwise>
                <li class="${"myList".equals(param.navItem)?"active":""}"><a href="myList">我的账户</a></li>

            </c:otherwise>
        </c:choose>

        <c:if test='${"管理员".equals(sessionScope.mUser.type)}'>
        <li class="${"userAdd".equals(param.navItem)?"active":""}"><a href="userAdd">用户管理</a></li>
        </c:if>
    </ul>
</div>
