<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>我的账户</title>
    <link href="static/libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>
    <link href="static/css/style.css" type="text/css" rel="stylesheet">
    <style>
        .table-main {
            font-size: large;
            font-weight: bold;
            color: goldenrod;
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
            <jsp:param name="navItem" value="myList"/>
        </jsp:include>
        <div class="col-md-10 column" id="content">
            <div class="row clearfix">
                <div class="col-md-12 column" id="search" style="background-color: lightskyblue;
                        height: 50px;display: flex;flex-direction: column;justify-content: center;text-align: center">
                    <h3 style="margin: 0">用户信息</h3>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column" id="main">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td class="table-left">用户编号</td>
                            <td class="table-main">${sessionScope.mUser.id}</td>
                        </tr>
                        <tr>
                            <td class="table-left">用户名</td>
                            <td class="table-main">${sessionScope.mUser.username}</td>
                        </tr>
                        <tr>
                            <td class="table-left">用户密码</td>
                            <td class="table-main">${sessionScope.mUser.password}</td>
                        </tr>
                        <tr>
                            <td class="table-left">性别</td>
                            <td class="table-main">${"0".equals(sessionScope.mUser.sex)?"男":"女"}</td>
                        </tr>
                        <tr>
                            <td class="table-left">身份证号</td>
                            <td class="table-main">${sessionScope.mUser.idNumber}</td>
                        </tr>
                        <tr>
                            <td class="table-left">电话</td>
                            <td class="table-main">${sessionScope.mUser.tel}</td>
                        </tr>
                        <tr>
                            <td class="table-left">地址</td>
                            <td class="table-main">${sessionScope.mUser.addr}</td>
                        </tr>
                        <tr>
                            <td class="table-left">图片</td>
                            <td class="table-main">
                                <div>
                                    <img class="img-rounded" id="imagePreview"
                                         src="${sessionScope.mUser.imagePath}"
                                         alt="无图片"
                                         style="width: 150px;height: 150px;margin-bottom: 5px">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div style="display: flex;justify-content: center">
                        <a href="userUpdate?userId=${sessionScope.mUser.id}" class="btn btn-danger" role="button">修改信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="loginOut" class="btn btn-danger" role="button">退出登录</a>
                    </div>
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
            $("#imagePreview").attr("src", url)
        })
    })

</script>
</body>
</html>
