<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>汽车列表</title>
    <link href="static/libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>

    <link href="static/css/style.css" type="text/css" rel="stylesheet">

    <style>
        #login button{
            width: 30%;
        }
        #login label{
            color: white;
        }
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
<body style="background-image:url('image/111.jpg')">
<div class="container" id="container" style="width: 100%;">
    <div class="row clearfix" id="head">
        <div class="col-md-12 column" style="line-height:100px;text-align:center;
            font-size: xxx-large;color:gold;background-color: lightseagreen;">
            二嗨租车
        </div>
    </div>
    <div class="row clearfix" id="body" style="display: flex;flex-direction: row;
    justify-content: center;min-height: 420px;">
        <form role="form" action="doLogin" method="post"
              style="margin-top: 5%;width: 25%;" id="login">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" class="form-control" id="username" name="username"
                       placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label for="password">&nbsp;&nbsp;&nbsp;密码</label>
                <input type="password" class="form-control" id="password" name="password"
                       placeholder="请输入密码">
            </div>

            <div style="display: flex;justify-content: space-between">
                <a href="register"> <button type="button" style="width: 80px;height: 34px" class="btn btn-default">注册</button></a>
                <button type="reset" class="btn btn-default" >重新输入</button>
                <button type="submit" class="btn btn-primary ">登录</button>
            </div>

        </form>

    </div>
    <div class="row clearfix" id="foot">
        <div class="col-md-12 column"
             style="height: 50px;line-height: 50px;text-align: center;color: gold">
            二嗨租车&copy;版权所有<a href="erhai@erhai.com" style="color: white">erhai@erhai.com</a>
        </div>
    </div>
</div>


<script>
    $(document).ready(function () {

<%--       <c:if test='${"failed".equals(param.result)}'>
        alert("用户名或密码错误，请重新登录")
        </c:if> --%>

     <%--   const loginResult = "${param.result}"
        if( "failed" == loginResult){
            alert("用户名或密码错误，请重新登录")
        } --%>

        const loginResult = "${sessionScope.result}";
        if( "failed" === loginResult){
            alert("用户名或密码错误，请重新登录")
        }
    })
</script>

</body>
</html>
