<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>211906109郭淳</title>
    <link rel="stylesheet" href="style/common.css">
    <link rel="stylesheet" href="style/regStyle.css">
    <!--设置标签图标-->
    <link href="favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="style/footerStyle.css">
</head>
<body>

<!--注册头部-->
<div id="reg_header">
    <div class="reg_h_center">

        <h3>欢迎注册</h3>

        <div class="reg_h_right">
            <span>已有账户</span><a href="login.jsp">请登录</a>
        </div>
    </div>
</div>

<div id="reg_main">
    <div class="main_left">
        <form action="/register" id="reg_form" method="post">
            <div>
                <c:if test="${!empty registerInfo}">
                    <span style="color: red;">${registerInfo}</span>
                </c:if>
                <label>用户名</label>
                <input id="username" type="text" placeholder="请输入用户名..." name="username">
            </div>
            <div>
                <label>密码</label>
                <input id="pwd" type="password" placeholder="请输入密码.." name="password">
            </div>
            <div>
                <label>确认密码</label>
                <input id="pwd2" type="password" placeholder="请再次输入密码...">
            </div>

            <div class="submit_button">
                <input type="button" value="立即注册" onclick="checkData()">
            </div>
        </form>
    </div>
    <div class="main_right">
        <img src="images/reg_right.png" alt="">
    </div>
</div>
<!--尾部-->
<div id="footer">
    <!--关于我们-->
    <div class="link">
        <a href="#">关于我们</a>
        |
        <a href="#">联系我们</a>
        |
        <a href="#">人才招聘</a>
        |
        <a href="#">商家入驻</a>
        |
        <a href="#">广告服务</a>
        |
        <a href="#">手机码蚁</a>
        |
        <a href="#">友情链接</a>
        |
        <a href="#">销售联盟</a>
        |
        <a href="#">码蚁社区</a>
        |
        <a href="#">码蚁公益</a>
    </div>
    <!--版权-->
    <div class="copyright">
        Copyright&nbsp;&copy;&nbsp;2017-2018&nbsp;&nbsp;码蚁My.com&nbsp;版权所有
    </div>
</div>


<script type="text/javascript">
    function change(obj) {
        obj.src = "${pageContext.servletContext.contextPath}/CheckCodeServlet?time=" + new Date().getTime();
    }

    function checkData() {

        //1.获取用户名， 密码   确认密码
        var username = document.getElementById("username");
        var pwd = document.getElementById("pwd");
        var pwd2 = document.getElementById("pwd2");

        //2.判断输入的内容不能为空
        if (username.value == "") {
            alert("请输入用户名");
            return;
        }
        if (pwd.value == "") {
            alert("请输入密码");
            return;
        }
        if (pwd2.value == "") {
            alert("请再次输入密码");
            return;
        }

        //3。两次密码是否一样
        if (pwd.value == pwd2.value) {
            //发送请求  form  获取form
            var form = document.getElementById("reg_form");
            form.submit();//通过js提交表单 执行action
        } else {
            alert("两次输入的密码不一样");
        }

    }


</script>


</body>
</html>


