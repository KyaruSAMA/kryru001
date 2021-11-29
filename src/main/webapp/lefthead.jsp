<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/29
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>

<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">


<script src="//cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        /*淘宝CSS格式化代码*/
        body,
        h1, h2, h3, h4, h5, h6, hr, p, blockquote, dl, dt, dd, ul, ol, li, pre, form, fieldset, legend, button, input, textarea, th, td {
            margin: 0;
            padding: 0;
        }

        body, button, input, select, textarea {
            font: 12px/1.5 tahoma, arial, \5b8b\4f53;
        }

        h1, h2, h3, h4, h5, h6 {
            font-size: 100%;
        }

        address, cite, dfn, em, var {
            font-style: normal;
        }

        code, kbd, pre, samp {
            font-family: couriernew, courier, monospace;
        }

        small {
            font-size: 12px;
        }

        ul, ol {
            list-style: none;
        }

        a {
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        sup {
            vertical-align: text-top;
        }

        sub {
            vertical-align: text-bottom;
        }

        legend {
            color: #000;
        }

        fieldset, img {
            border: 0;
        }

        button, input, select, textarea {
            font-size: 100%;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }

        body, html {
            background: #EAEEF2;
            width: 100%;
            height: 100%;
        }

        .container-fluid {
            background: aquamarine;
            height: 100%;
        }

        .logo {
            background: lightseagreen;
            color: black;
            font-size: 40px;
            padding-left: 20px;
            height: 60px;
            min-height: 60px;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 100;
            width: 100%;
        }

        .nav > li > a:hover {
            color: #262626;
            text-decoration: none;
            background-color: #354144;
        }

        .open > a {
            background-color: #354144 !important;
        }

        .right {
            float: right;
        }

        .grid:hover {
            background: #efefef;
        }

    </style>
</head>
<body>
<div class="logo" style="width: 100%;">
    <div class="row clearfix">
        <div class="row">
            <div class="col-lg-10" style="text-align:center;    background: #354144;color: white">二嗨租车</div>
            <div class="col-lg-2" style="background: #354144">
                <div class="right">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle"
                               data-toggle="dropdown"
                               style="height: 60px">
                                <img alt="" class="img-circle" src="${sessionScope.mUser.imagePath}" width="38px" height="38px"/>
                                <span style="color: #FFFFFF;font-size: 15px">
                                <i>${sessionScope.mUser.username}</i>
                            </span>
                            </a>
                            <div class="dropdown-menu pull-right"
                                 style="background: #FFFFFF;width: 320px;overflow: hidden">
                                <div style="margin-top: 16px;border-bottom: 1px solid #eeeeee">
                                    <div style="text-align: center">
                                        <img class="img-circle" src="${sessionScope.mUser.imagePath}"
                                             style="width: 38px;height: 38px;"/>
                                    </div>
                                    <div style="color: #323534;text-align: center;line-height: 36px;font-size: 15px">
                                        <span>${sessionScope.mUser.username}</span>
                                    </div>
                                </div>

                                <div class="row" style="margin-left: 15px;margin-right: 15px;margin-top: 10px">
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-user" style="font-size: 25px;line-height: 45px;"></i>
                                        <a href="myList" style="padding: 0;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            个人中心</a>
                                    </div>
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-gear" style="font-size: 25px;line-height: 45px;"></i>
                                        <a href="userUpdate?userId=${sessionScope.mUser.id}" style="padding: 0;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            账号管理</a>
                                    </div>

                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-gear" style="font-size: 25px;line-height: 45px;"></i>
                                        <a href="rentList" style="padding: 0;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            租车记录</a>
                                    </div>
                                </div>


                                <div class="row" style="margin-top: 20px">
                                    <div class="text-center"
                                         style="padding: 15px;margin: 0;background: #f6f5f5;color: #323534;">
                                        <a href="loginOut" class="fa fa-sign-out"></a> 退出登入界面
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
    </div>


</div>
<script>
    $(function () {
        $(".dropdown").mouseover(function () {
            $(this).addClass("open");
        });

        $(".dropdown").mouseleave(function () {
            $(this).removeClass("open");
        })
    })
</script>
</body>
</html>
