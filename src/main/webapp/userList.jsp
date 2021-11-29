<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户列表</title>
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
<div class="container" id="container" style="width: 100%;">
    <div class="row clearfix" id="head">
        <div class="col-md-10 column" style="line-height:100px;text-align:center;
            font-size: xxx-large;color:gold;background-color: lightseagreen;">
            二嗨租车
        </div>
        <div class="col-lg-2" style="background: lightseagreen;height: 100px ;text-align: right">
            <div class="right">
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
            <jsp:param name="navItem" value="userList"/>
        </jsp:include>
        <div class="col-md-10 column" id="content">
            <div class="row clearfix">
                <div class="col-md-12 column" id="search" style="background-color: lightskyblue;
                        height: 50px;display: flex;flex-direction: column;justify-content: center">
                    <form class="form-inline" role="form" action="userList" method="get"
                          style="margin: 0;display: flex;justify-content: flex-end">
                        <div class="form-group">
                            <label for="userId">用户编号</label>
                            <input class="form-control" id="userId" type="text" name="userId" style="width: 140px"
                                   placeholder="按用户编号搜索" value="${requestScope.mUserSearch.userId}">
                        </div>&nbsp;
                        <div class="form-group" style="margin-left: 10px">
                            <label for="userName">用户名</label>
                            <input class="form-control" id="userName" type="text" name="userName" style="width: 140px"
                                   placeholder="按用户名搜索" value="${requestScope.mUserSearch.userName}">
                        </div>&nbsp;
                        <div class="form-group" style="margin-left: 10px">
                            <label for="userType">用户角色</label>
                            <select class="form-control" id="userType" name="userType">
                                <option value=""
                                ${"全部".equals(requestScope.mUserSearch.type)?"selected" : ""}>全部
                                </option>
                                <option value="1"
                                ${"管理员".equals(requestScope.mUserSearch.type)?"selected" : ""}>
                                    管理员
                                </option>
                                <option value="0"
                                ${"普通用户".equals(requestScope.mUserSearch.type)?"selected" : ""}>
                                    普通用户
                                </option>
                            </select>
                        </div>
                        <button id="resetButton" class="btn btn-default" type="reset"
                                style="margin-left: 10px">重置
                        </button>
                        <button class="btn btn-default" type="submit"
                                style="margin-left: 10px">搜索
                        </button>
                        <div class="form-group">
                            <a href="userAdd" class="btn btn-default" role="button"
                               style="margin-left: 10px;display: flex;flex-direction:column;justify-content: center">
                                <span style="display: block;text-align: center">新增</span></a>
                        </div>
                    </form>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-md-12 column" id="main" style="padding: 0">
                    <table class="table table-bordered" style="margin: 20px auto 0 auto; width: 96%;font-size: 14px">
                        <thead>
                        <tr>
                            <th>用户编号</th>
                            <th>用户名</th>
                            <th>密码</th>
                            <th>性别</th>
                            <th>身份证</th>
                            <th>电话</th>
                            <th>地址</th>
                            <th>角色(账户类型)</th>
                            <th>照片</th>
                            <th>修改</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mUser" items="${requestScope.mUser}">
                            <tr>
                                <td>${mUser.id}</td>
                                <td>${mUser.username}</td>
                                <td>${mUser.password}</td>
                                <td>${"0".equals(mUser.sex)?"男":"女"}</td>
                                <td>${mUser.idNumber}</td>
                                <td>${mUser.tel}</td>
                                <td>${mUser.addr}</td>
                                <td>${"1".equals(mUser.type)?"管理员":"普通用户"}</td>
                                <td><div>
                                    <img class="img-rounded" id="imagePreview"
                                         src="${mUser.imagePath}"
                                         alt="无图片"
                                         style="width: 20px;height: 40px;margin-bottom: 5px">
                                </div></td>
                                <td><a href="userUpdate?userId=${mUser.id}">修改</a></td>
                                <td>
                                    <a href="javascript:void(0)" class="user-delete-field"
                                       data-erhai-delete-user-id="${mUser.id}">
                                        删除
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div style="display: flex;justify-content: center;font-size: 12px;">
                        <ul class="pagination">
                            <li><a href="${requestScope.mPageNav.first.url}">首页</a></li>
                            <li><a href="${requestScope.mPageNav.previous.url}">&laquo;</a></li>
                            <c:forEach var="page" items="${requestScope.mPageNav.pages}">
                                <li class="${page.index.equals(requestScope.mPageNav.currentPageIndex) ?"action" : ""}">
                                    <a href="${page.url}">${page.index}</a>
                                </li>
                            </c:forEach>

                            <li><a href="${requestScope.mPageNav.next.url}">&raquo;</a></li>
                            <li><a href="${requestScope.mPageNav.max.url}">尾页</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix" id="foot">
        <div class="col-md-12 column"
             style="height: 50px;line-height: 50px;text-align: center;background-color: lightskyblue;">
            二嗨租车&copy;版权所有<a href="erhai@erhai.com" style="color: white">erhai@erhai.com</a>
        </div>
    </div>

    <!-- 模态框   信息删除确认 -->
    <div class="modal fade" id="userDeleteConfirmModal">
        <div class="modal-dialog">
            <div class="modal-content message_align">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title">提示</h4>
                </div>
                <div class="modal-body">
                    <!-- 隐藏需要删除的id -->
                    <input type="hidden" id="deleteUserId"/>
                    <p>您确认要删除该用户吗？</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            id="userDeleteConfirmBtn">确认
                    </button>
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
    $(document).ready(function () {
        document.getElementById("resetButton").addEventListener("click", function (event) {
            event.preventDefault()
            event.stopPropagation()

            $("#userId").val("")
            $("#userName").val("")
            $("#userType").val("allRole")

            $(this).blur()
        })
    })
    $(document).ready(function () {
        $(".user-delete-field").on("click", {}, function (event) {
            event.preventDefault()
            event.stopPropagation()

            const userId = $(this).attr("data-erhai-delete-user-id")

            $("#deleteUserId").val(userId)//将模态框中需要删除的汽车的ID设为需要删除的ID

            $("#userDeleteConfirmModal").modal({
                backdrop: "static",
                keyboard: false
            });
        })
    })

    $(document).ready(function () {
        $("#userDeleteConfirmBtn").on("click", {}, function (event) {
            event.preventDefault()
            event.stopPropagation()


            const userId = $("#deleteUserId").val()

            const url = "doUserDelete"
            const data = {
                userId: userId
            }

            $.ajax({
                url: url,
                type: "post",
                data: data,
                dataType: "text",
                success: function (data, textStatus) {
                    $("#userDeleteConfirmModal").modal("hide")
                    const response = JSON.parse(data)
                    $("[data-erhai-delete-user-id=" + response.userId + "]").parent().parent().remove()
                    setTimeout(function () {
                        alert("编号为" + response.userId + "的用户删除成功!")

                    }, 1000)
                },
                error: function (data, textStatus) {
                    $("#userDeleteConfirmModal").modal("hide")

                    setTimeout(function () {
                        if (textStatus === "timeout") {
                            alert("删除失败,请求处理超时")
                        } else {
                            alert("删除失败!错误信息：" + data.responseText)
                        }
                    }, 1000)
                },
                timeout: 6000
            });
        })

    })
</script>

</body>
</html>
