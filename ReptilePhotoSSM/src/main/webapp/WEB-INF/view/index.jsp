<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>211906109郭淳</title>
    <!--链接外部样式-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/style/headerStyle.css">
    <!--设置标签图标-->
    <link href="favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/style/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/style/footerStyle.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/js/page/pageStyle.css">
</head>
<body>
<img src="/images/list_bg.jpg" width="100%" height="100%" style="z-index:-100;position:fixed;left:0;top:0"/>
<!--头部-->
<div id="header">
    <!--头部登录，购物车-->
    <div class="header_top">
        <!--中部-->
        <div class="header_top_center">
            <!--中部左侧-->
            <div class="h_top_left">
                图片爬取展示
            </div>
            <!--中部右侧-->
            <div class="h_top_right">
                <c:if test="${!empty user}">
                    <a href="#">用户名 : ${user.username}</a>
                </c:if>
                <c:if test="${!empty user}">
                    <a href="/login.jsp">退出</a>
                </c:if>
                <c:if test="${empty user}">
                    <a href="/login.jsp">登录</a>
                    <a href="/regist.jsp">免费注册</a>
                </c:if>
            </div>
        </div>
    </div>

    <!--中部搜索-->
    <div class="header_center">
        <!--版心-->
        <div class="h_c_center">

            <!--中部搜索-->
            <div class="h_c_search">
                <form action="/searchPhoto">
                    <input type="text" placeholder="请输入要查找的图片..." class="s_input" name="searchText" value=${searchText}>
                    <input type="submit" value="搜索" class="s_button">
                </form>
            </div>
            <div class="h_c_search">
                <form action="/reptile">
                    <input type="text" placeholder="请输入要爬取的页数..." class="s_input" name="inputNum">
                    <input type="submit" value="开始爬取" class="s_button">
                </form>
            </div>


        </div>
        <%--    开始爬取按钮--%>
    </div>

</div>

<!--热买商品-->
<div id="hot_goods">
    <h3 class="hot_goods_title">爬取图片</h3>
    <div class="hot_goods_body">
        <ul>
            <c:forEach items="${photos}" var="p">
                <li>
                    <a href="/showPhoto?url=${p.p_url}">
                        <img src="${p.p_url}">
                        <p>${p.p_name}</p>
                        <p>所属用户 :${p.u_id}</p>
                        <p>文件路径 :${p.p_url}</p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div id="page" class="page_div" style="height: 80px; padding-top: 20px; text-align: right;"></div>
</div>

</body>
<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/page/paging.js"></script>
<script>

    $(function () {
        //分页
        $("#page").paging({
            pageNo:${pageBean.currentPage}, /**当前页  把第几页成为选中状态*/
            totalPage:${pageBean.totalPage}, /**共多少页*/
            totalSize: ${pageBean.totalCount}, /**共多少条数据*/
            callback: function (num) {
                /**num为当前点击的页面*/
                window.location.href = "/changePage/" + num;
            }
        });
    });

</script>
</html>


