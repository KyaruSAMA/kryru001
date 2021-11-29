<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>汽车管理</title>
    <link href="static/libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>
    <link href="static/css/style.css" type="text/css" rel="stylesheet">
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

        <div class="col-md-12 column" id="content">
            <div class="row clearfix">
                <div class="col-md-12 column" id="search" style="background-color: lightskyblue;
                        height: 50px;display: flex;flex-direction: column;justify-content: center;text-align: center">
                    <h3>注册用户</h3>
                </div>
            </div>
            <div class="row clearfix"style="background-image: url('image/⑨.jpeg')">
                <div class="col-md-12 column" id="main" style="padding: 0;display: flex;justify-content: center " >
                    <form role="form" action="doRegister" method="post" enctype="multipart/form-data"
                          style="width: 50%;margin-top: 20px">
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <input type="text" class="form-control" id="username"
                                   name="username"  placeholder="请输入用户名">
                        </div>
                        <div class="form-group">
                            <label for="password">密码</label>
                            <input type="text"  class="form-control" id="password"
                                   name="password" placeholder="请输入密码">
                        </div>
                        <div class="form-group">
                            <label for="sex">性别</label>
                            <div class="radio" id="sex">
                                <label><input type="radio" name="sex" id="man" value="0"> 男</label>
                                <label><input type="radio" name="sex"  id="woman" value="1">女</label>
                            </div>
                        </div>&nbsp;
                        <div class="form-group">
                            <label for="tel">电话</label>
                            <input type="text"  class="form-control" id="tel"
                                   name="tel" placeholder="请输入电话">
                        </div>
                        <div class="form-group">
                            <label for="idNumber">身份证号码</label>
                            <input type="text"  class="form-control" id="idNumber"
                                   name="idNumber" placeholder="请输入身份证号码">
                        </div>
                        <div class="form-group">
                            <label for="addr">地址</label>
                            <input type="text"  class="form-control" id="addr"
                                   name="addr" placeholder="请输入地址">
                        </div>
                        <div class="form-group">
                            <label for="image">用户照片</label>
                            <div>
                                <img class="img-rounded" id="imagePreview" src="" alt="无图片"
                                     style="width:200px;height: 150px;margin-bottom: 5px">
                            </div>
                            <input type="file" id="image" name="image">
                        </div>
                        <div class="form-group">
                            <label><input type="hidden" name="type" id="user1" value="普通用户"></label>
                        </div>
                        <div class="form-group" style="display: flex;justify-content: flex-end">
                            <button class="btn btn-default" type="reset">重置
                            </button>
                            <button class="btn btn-primary" type="submit" style="margin-left: 30px">提交
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
