<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>汽车列表</title>
    <link href="static/web-libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/web-libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/web-libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>
    <link href="static/css/style.css" type="text/css" rel="stylesheet">
    <style>
        #main dl dt{
            margin-top: 20px;
        }
        #main dl dd{
            margin-top: 5px;
        }
        #main dl {
            font-size: large;
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
            <jsp:param name="navItem" value="carList"/>
        </jsp:include>
        <div class="col-md-10 column" id="content">
            <div class="row clearfix">
                <div class="col-md-12 column" id="search" style="background-color: lightskyblue;
                        height: 50px;display: flex;flex-direction: column;justify-content: center;text-align: center">
                    <h3 style="margin: 0">汽车详情</h3>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column" id="main"
                     style="padding: 0;margin-left: 30px">
                    <dl>
                        <dt>汽车编号：</dt>
                        <dd>${requestScope.mCar.id}</dd>

                        <dt>品牌名：</dt>
                        <dd>${requestScope.mCar.brand}</dd>

                        <dt>类型名：</dt>
                        <dd>${requestScope.mCar.category}</dd>

                        <dt>型号：</dt>
                        <dd>${requestScope.mCar.model}</dd>

                        <dt>车牌号：</dt>
                        <dd>
                            <code style="font-size: xx-large;background-color: deepskyblue;color: white">
                                ${requestScope.mCar.carNumber}
                            </code>
                           </dd>
                        <dt>简介：</dt>
                        <dd>${requestScope.mCar.comments}</dd>

                        <dt>颜色：</dt>
                        <dd>${requestScope.mCar.color}</dd>

                        <dt>汽车价格(元)：</dt>
                        <dd>${requestScope.mCar.price}</dd>

                        <dt>每日租金(元)：</dt>
                        <dd>${requestScope.mCar.rent}</dd>
                        <dt>汽车图片：</dt>
                        <dd>
                            <div>
                                <img class="img-rounded" id="imagePreview"
                                src="${requestScope.mCar.imagePath}"
                                alt="无图片"
                                style="width: 480px;height: 320px;margin-bottom: 5px">
                            </div>
                        </dd>

                    </dl>
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
