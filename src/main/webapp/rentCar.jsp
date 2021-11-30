<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>汽车管理</title>
    <link href="static/libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>
    <link href="static/css/style.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">


    <script src="//cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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

        .right {
            float: right;
        }

        .grid:hover {
            background: #efefef;
        }
        .open > a {
            background-color: lightseagreen !important;
        }
    </style>
</head>
<body>
<div class="container" id="container" style="width: 100%;">   <div class="row clearfix" id="head">
    <div class="col-md-10 column" style="line-height:100px;text-align:center;
            font-size: xxx-large;color:gold;background-color: lightseagreen;">
        二嗨租车
    </div>
    <div class="col-lg-2" style="background: lightseagreen;height: 100px ;text-align: right">
        <div class="right"style="text-align: right">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle"
                       data-toggle="dropdown"
                       style="height: 50px;text-align: right">
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
                            <div class="col-md-4 text-center grid" onclick="location.href='myList';">
                                <i class="fa fa-user" style="font-size: 25px;line-height: 45px;"></i>
                                <a href="myList" style="padding: 0;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                    个人中心</a>
                            </div>
                            <div class="col-md-4 text-center grid" onclick="location.href='userUpdate?userId=${sessionScope.mUser.id}';">
                                <i class="fa fa-gear" style="font-size: 25px;line-height: 45px;"></i>
                                &nbsp;
                                <a href="userUpdate?userId=${sessionScope.mUser.id}" style="padding: 0;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                    账号管理</a>
                            </div>

                            <div class="col-md-4 text-center grid"onclick="location.href='rentList';">
                                <i class="fa fa-gear" style="font-size: 25px;line-height: 45px;"></i>
                                <a href="rentList" style="padding: 0;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                    租车记录</a>
                            </div>
                        </div>


                        <div class="row" style="margin-top: 20px">
                            <div class="text-center"
                                 style="padding: 15px;margin: 0;background: #f6f5f5;color: #323534;"
                                 onclick="location.href='loginOut';">
                                <a href="loginOut" class="fa fa-sign-out">退出登入界面</a>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>

    </div>
</div>
    <div class="row clearfix" id="body" style="display: flex;flex-direction: row;min-height: 600px">
        <jsp:include page="left.jsp">
            <jsp:param name="navItem" value="carAdd"/>
        </jsp:include>
        <div class="col-md-10 column" id="content"style="background-image:url('static/image/kyaru11.gif');background-size: 100%">
            <div class="row clearfix">
                <div class="col-md-12 column" id="search" style="background-color: lightskyblue;
                        height: 50px;display: flex;flex-direction: column;justify-content: center;text-align: center">
                    <h3>租车</h3>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column" id="main" style="padding: 0;display: flex;justify-content: center">
                    <form role="form" action="doRentCar" method="get" enctype="multipart/form-data"
                          style="width: 50%;margin-top: 20px">
                        <div class="form-group">
                            <label for="carId">汽车ID</label>
                            <input type="text" class="form-control" id="carId"
                                   name="carId"  placeholder="请输入汽车ID">
                        </div>

                        <div class="form-group" style="display: flex;justify-content: flex-end">
                            <button class="btn btn-default" type="reset">重置
                            </button>
                            <button class="btn btn-primary" type="submit" style="margin-left: 30px">租车
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix" id="foot">
        <div class="col-md-12 column"
             style="height: 50px;line-height: 50px;text-align: center;background-color: lightskyblue;">
            二嗨租车&copy;版权所有<a href="#" style="color: white">erhai@erhai.com</a>
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
    $(document).ready(function () {
        document.getElementById("image").addEventListener("change", function (event) {
            event.preventDefault()
            event.stopPropagation()

            const file = this.files[0]
            const url = window.URL.createObjectURL(file)
            $("#imagePreview").attr("src",url)
        })
    })
</script>
</body>
</html>
