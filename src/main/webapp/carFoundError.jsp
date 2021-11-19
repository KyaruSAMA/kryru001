<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>汽车管理</title>
    <link href="static/web-libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/web-libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/web-libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>
    <link href="static/css/style.css" type="text/css" rel="stylesheet">
    <style>
        a{
            width: 120px;
        }
    </style>
</head>
<body>

<div style="height: 400px;display: flex;flex-direction: row;justify-content: center;align-content: center">
        <div class="pager panel-default" style="width: 40%">
            <div class="panel-heading">
                <h3 class="panel-title">
                    结果
                </h3>
            </div>

            <div class="panel-body">
                <div style="display: flex;flex-direction: row;justify-content: center">
                    <h3 class="text-danger">找不到汽车</h3>
                </div>
                <div style="display: flex;flex-direction: row;justify-content: flex-end;margin-top: 20px">
                    <a href="carList" class="btn btn-primary" role="button" style="margin-left: 20px">
                        返回汽车列表
                    </a>
                </div>
            </div>
        </div>
</div>

</body>
</html>
