<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>211906109郭淳</title>
    <!--设置标签图标-->
    <!--设置标签图标-->
    <link rel="shortcut icon" href="/images/logo.ico"><!--需要ico文件作为图标。而rel里的内容不需要改变-->
    <!--关联CSS文件和HTML文件，实现网页格式分开储存-->
    <link rel="stylesheet" type="text/css" href="/style/login.css">
    <link rel="stylesheet" type="text/css" href="/style/footer.css">
</head>
<body>
<!--头部-->
<div id="header">

</div>
<!--中部-->
<div id="login_body">
    <div class="login_b_center"><!--网页中心区域-->
        <div class="login_window"><!--登陆窗口-->
            <h4>密码登陆
                <c:if test="${!empty loginInfo}">
                    <span style="color: red;">${loginInfo}</span>
                </c:if>
            </h4><!--设置登陆图标-->
            <form action="${pageContext.servletContext.contextPath}/login" id="login"><!--创建表单-->
                <!--用户名-->
                <div class="username">
                    <span></span><input type="text" name="username" placeholder="会员名/邮箱/手机号"><!--placeholder-->
                </div>
                <!--密码-->
                <div class="password">
                    <span></span><input type="password" name="password" placeholder="请输入登陆密码">
                </div>

                <!--登陆按钮-->
                <div class="loginIn">
                    <input type="submit" value="登陆"><!--设置成submit，点击后会有变化-->
                </div>
                <div class="loginMessage">
                    <a href="">忘记密码</a>
                    <a href="">忘记用户名</a>
                    <a href="regist.jsp">免费注册</a>
                </div>

            </form>
        </div>
    </div>
</div>
<!--尾部-->
<div id="footer">
    <div class="link">
        <a href="">关于我们</a>
        <a href="">联系我们</a>
        <a href="">人才招聘</a>
        <a href="">商家入驻</a>
        <a href="">广告服务</a>
        <a href="">友情链接</a>
        <a href="">社区论坛</a>
        <a href="">联系客服</a>
        <a href="">知识产权</a>
    </div>
    <div class="copyrigth">
        © 2003-现在 Mayi.com 版权所有
    </div>
</div>

</body>
</html>