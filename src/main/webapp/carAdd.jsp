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
        <jsp:include page="left.jsp">
            <jsp:param name="navItem" value="carManage"/>
        </jsp:include>
        <div class="col-md-10 column" id="content">
            <div class="row clearfix">
                <div class="col-md-12 column" id="search" style="background-color: lightskyblue;
                        height: 50px;display: flex;flex-direction: column;justify-content: center;text-align: center">
                    <h3>新增汽车</h3>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column" id="main" style="padding: 0;display: flex;justify-content: center">
                    <form role="form" action="doCarAdd" method="post" enctype="multipart/form-data"
                    style="width: 50%;margin-top: 20px">
                        <div class="form-group">
                            <label for="brand">品牌名</label>
                            <input type="text" class="form-control" id="brand"
                               name="brand"  placeholder="请输入品牌名">
                        </div>
                        <div class="form-group">
                            <label for="category">类型名</label>
                            <input type="text"  class="form-control" id="category"
                                   name="category" placeholder="请输入类型名">
                        </div>
                        <div class="form-group">
                            <label for="model">型号</label>
                            <input type="text"  class="form-control" id="model"
                                   name="model" placeholder="请输入型号">
                        </div>
                        <div class="form-group">
                            <label for="carNumber">车牌号</label>
                            <input type="text"  class="form-control" id="carNumber"
                                   name="carNumber" placeholder="请输入车牌号">
                        </div>
                        <div class="form-group">
                            <label for="comments">简介</label>
                            <input type="text"  class="form-control" id="comments"
                                   name="comments" placeholder="请输入简介">
                        </div>
                        <div class="form-group">
                            <label for="color">颜色</label>
                            <input type="text"  class="form-control" id="color"
                                   name="color" placeholder="请输入颜色">
                        </div>
                        <div class="form-group">
                            <label for="price">汽车价格</label>
                            <input type="text"  class="form-control" id="price"
                                   name="price" placeholder="请输入汽车价格">
                        </div>
                        <div class="form-group">
                            <label for="rent">每日租金</label>
                            <input type="text"  class="form-control" id="rent"
                                   name="rent" placeholder="请输入每日租金">
                        </div>
                        <div class="form-group">
                            <label for="image">汽车图片</label>
                            <div>
                                <img  class="img-rounded" id="imagePreview" src="" alt="无图片"
                                style="width:200px;height: 150px;margin-bottom: 5px">
                            </div>
                            <input type="file"  id="image" name="image">
                        </div>
                        <div class="form-group">
                            <label for="usable">是否上架</label>
                            <input type="checkbox" id="usable" name="usable" style="margin-left: 20px;" checked>是
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
