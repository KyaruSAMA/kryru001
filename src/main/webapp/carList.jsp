<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>汽车列表</title>
    <link href="static/web-libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/web-libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/web-libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>

    <link href="static/css/style.css" type="text/css" rel="stylesheet">
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
                        height: 50px;display: flex;flex-direction: column;justify-content: center">
                    <form class="form-inline" role="form" action="carList" method="get"
                          style="margin: 0;display: flex;justify-content: flex-end">
                        <div class="form-group">
                            <label for="carId">汽车编号</label>
                            <input class="form-control" id="carId" type="text" name="carId" style="width: 140px"
                                   placeholder="按汽车编号搜索" value="${requestScope.mCarSearch.carId}">
                        </div>&nbsp;
                        <div class="form-group" style="margin-left: 10px">
                            <label for="carBrand">汽车品牌</label>
                            <input class="form-control" id="carBrand" type="text" name="carBrand" style="width: 140px"
                                   placeholder="按汽车品牌搜索" value="${requestScope.mCarSearch.carBrand}">
                        </div>&nbsp;
                        <div class="form-group" style="margin-left: 10px">
                            <label for="carCategory">汽车类别</label>
                            <input class="form-control" id="carCategory" type="text" name="carCategory"
                                   style="width: 140px"
                                   placeholder="按汽车类别搜索" value="${requestScope.mCarSearch.carCategory}">
                        </div>&nbsp;
                        <div class="form-group" style="margin-left: 10px">
                            <label for="priceOrder">价格排序</label>
                            <select class="form-control" id="priceOrder" name="priceOrder">
                                <option value="unordered"
                                ${"unordered".equals(requestScope.mCarSearch.priceOrder)?"selected" : ""}>无序
                                </option>
                                <option value="asc"
                                ${"asc".equals(requestScope.mCarSearch.priceOrder)?"selected" : ""}
                                >升序
                                </option>
                                <option value="desc"
                                ${"desc".equals(requestScope.mCarSearch.priceOrder)?"selected" : ""}
                                >降序
                                </option>
                            </select>
                        </div>&nbsp;
                        <button id="resetButton" class="btn btn-default" type="reset"
                                style="margin-left: 10px">重置
                        </button>
                        <button class="btn btn-default" type="submit"
                                style="margin-left: 10px">搜索
                        </button>
                        <c:if test='${"管理员".equals(sessionScope.mUser.type)}'>
                            <div class="form-group">
                                <a href="carAdd" class="btn btn-default" role="button"
                                   style="margin-left: 10px;display: flex;flex-direction:column;justify-content: center">
                                    <span style="display: block;text-align: center">新增</span></a>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-md-12 column" id="main" style="padding: 0">
                    <table class="table table-bordered" style="margin: 20px auto 0 auto; width: 96%;font-size: 14px">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>型号</th>
                            <th>简介</th>
                            <th>品牌</th>
                            <th>类型</th>
                            <th>每日租金</th>
                            <c:if test='${"管理员".equals(sessionScope.mUser.type)}'>
                                <th>是否上架</th>
                                <th>修改</th>
                                <th>删除</th>
                            </c:if>
                            <th>详情</th>
                            <th>是否可租</th>
                            <th>租车</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mCar" items="${requestScope.mCars}">
                            <tr>
                                <td>${mCar.id}</td>
                                <td>${mCar.model}</td>
                                <td>${mCar.coments}</td>
                                <td>${mCar.brand}</td>
                                <td>${mCar.category}</td>
                                <td>${mCar.rent}</td>
                                <c:if test='${"管理员".equals(sessionScope.mUser.type)}'>
                                    <td>
                                        <a href="javascript:void(0)" class="car-usable-field"
                                           data-erhai-car-id="${mCar.id}">
                                                ${mCar.usable}
                                        </a>
                                    </td>

                                    <td><a href="carUpdate?carId=${mCar.id}">修改</a></td>

                                    <td>
                                        <a href="javascript:void(0)" class="car-delete-field"
                                           data-erhai-delete-car-id="${mCar.id}">
                                            删除
                                        </a>
                                    </td>
                                </c:if>
                                <td><a href="carDetail?carId=${mCar.id}">详情</a></td>
                                <td>${mCar.status}</td>
                                <td><a href="#">租车</a></td>
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
    <div class="modal fade" id="carDeleteConfirmModal">
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
                    <input type="hidden" id="deleteCarId"/>
                    <p>您确认要删除该汽车吗？</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            id="carDeleteConfirmBtn">确认
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        document.getElementById("resetButton").addEventListener("click", function (event) {
            event.preventDefault()
            event.stopPropagation()

            $("#carId").val("")
            $("#carBrand").val("")
            $("#carCategory").val("")
            $("#priceOrder").val("unordered")

            $(this).blur()
        })
    })

    $(document).ready(function () {
        $(".car-usable-field").on("click", {}, function (event) {
            event.preventDefault()
            event.stopPropagation()


            const carId = $(this).attr("data-erhai-car-id")
            let usable = $(this).text().trim();
            if ("是" === usable) {
                usable = "否"
            } else {
                usable = "是"
            }

            const url = "doCarUpdateUsable"
            const data = {
                carId: carId,
                usable: usable
            }

            const outThis = this

            $.ajax({
                url: url,
                type: "post",
                data: data,
                dataType: "text",
                success: function (data, textStatus) {
                    $(outThis).text(usable)
                    const response = JSON.parse(data)
                    if (usable === "是") {
                        alert("编号为" + response.carId + "的汽车上架成功!")
                    } else {
                        alert("编号为" + response.carId + "的汽车下架成功!")
                    }
                },
                error: function (data, textStatus) {
                    if (textStatus === "timeout") {
                        alert("更新上下架状态失败,请求处理超时")
                    } else {
                        alert("更新上下架状态失败!错误信息：" + data.responseText)
                    }
                },
                timeout: 6000
            });


        })
    })

    $(document).ready(function () {
        $(".car-delete-field").on("click", {}, function (event) {
            event.preventDefault()
            event.stopPropagation()

            const carId = $(this).attr("data-erhai-delete-car-id")

            $("#deleteCarId").val(carId)//将模态框中需要删除的汽车的ID设为需要删除的ID

            $("#carDeleteConfirmModal").modal({
                backdrop: "static",
                keyboard: false
            });
        })
    })

    $(document).ready(function () {
        $("#carDeleteConfirmBtn").on("click", {}, function (event) {
            event.preventDefault()
            event.stopPropagation()


            const carId = $("#deleteCarId").val()

            const url = "doCarDelete"
            const data = {
                carId: carId
            }

            $.ajax({
                url: url,
                type: "post",
                data: data,
                dataType: "text",
                success: function (data, textStatus) {
                    $("#carDeleteConfirmModal").modal("hide")
                    const response = JSON.parse(data)
                    $("[data-erhai-delete-car-id=" + response.carId + "]").parent().parent().remove()
                    setTimeout(function () {
                        alert("编号为" + response.carId + "的汽车删除成功!")
                    }, 1000)
                },
                error: function (data, textStatus) {
                    $("#carDeleteConfirmModal").modal("hide")

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
