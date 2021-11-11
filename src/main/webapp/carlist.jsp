<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/11
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>汽车列表</title>
    <link href="static/libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript"rel="script"></script>
    <style>
        #left {
        padding: 0;
    }
     #left ul li{

         width: 100%;

    }
    #left ul li a{
        font-size: large;
        letter-spacing: 5px;
        border-radius: 0;
        border:1px solid white;
    }</style>
</head>
<body>
<div class="container" id="contain" style="width: 100%;">
    <div class="row clearfix" id="head">
        <div class="col-md-12 column" style="height: 100px;line-height: 100px;text-align: center;font-size: xxx-large;
color: gold;background: darkslateblue ;">
            二嗨租车
        </div>
    </div>
    <div class="row clearfix" id="body">
        <div class="col-md-2 column" id="left" style="background: lightblue">
            <ul class="nav nav-pills" >
            <li class="active"><a href="bs004.html">Home</a></li>
            <li><a href="bs005.html">SVN</a></li>
            <li><a href="#">iOS</a></li>
            <li><a href="#">VB.Net</a></li>
            <li><a href="#">Java</a></li>
            <li><a href="#">PHP</a></li>
        </ul>
        </div>
        <div class="col-md-10 column "id="contend">
            <div class="row clearfix">
                <div class="col-md-12 column">
<%--                    <form class="form-inline" role="form">--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="sr-only" for="name">名称</label>--%>
<%--                            <input type="text" class="form-control" id="name"--%>
<%--                                   placeholder="请输入名称">--%>
                        </div>


                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix" id="foot">
        <div class="col-md-12 column">
        </div>
    </div>
</div>
</body>
</html>
