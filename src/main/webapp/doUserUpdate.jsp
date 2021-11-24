<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户管理</title>
    <link href="static/libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>
    <link href="static/css/style.css" type="text/css" rel="stylesheet">
    <style>
        a{
            width: 120px;
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

<div style="height: 400px;display: flex;flex-direction: row;justify-content: center;align-items: center">

    <div class="panel panel-default" style="width: 40%">
        <div class="panel-heading">
            <h3 class="panel-title">
                结果
            </h3>
        </div>
        <div class="panel-body">
            <div style="display: flex;flex-direction: row;justify-content: center">
                <c:if test='${"succeed".equals(sessionScope.result)}'>
                    <h3 class="text-success">${sessionScope.message}</h3>
                </c:if>

                <c:if test='${"failed".equals(sessionScope.result)}'>
                    <h3 class="text-danger">${sessionScope.message}</h3>
                </c:if>

            </div>
            <div style="display: flex;flex-direction: row;justify-content: flex-end;margin-top: 20px">
                <a href="userList" class="btn btn-primary" role="button" style="margin-left: 20px">
                    返回用户列表
                </a>
            </div>
        </div>
    </div>

</div>

</body>
</html>
