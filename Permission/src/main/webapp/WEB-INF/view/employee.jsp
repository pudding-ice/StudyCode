<%--
  Created by IntelliJ IDEA.
  User: Pudding
  Date: 2022/5/20
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <title>employee</title>
    <jsp:include page="${pageContext.request.contextPath}/static/common/common.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/employee.js"></script>

</head>
<body>
<table id="dg"></table>

<div id="tb">
    <shiro:hasPermission name="employee:add">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:edit">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:delete">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delete">离职</a>
    </shiro:hasPermission>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>
    <input type="text" name="keyword" style="width: 200px; height: 30px;padding-left: 5px;">
    <a class="easyui-linkbutton" iconCls="icon-search" id="searchbtn">查询</a>
</div>
<div id="add_employee">
    <form id="add_employee_form">
        <table align="center" style="border-spacing: 0px 10px">
            <input name="id" type="text" hidden="hidden">
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="username" class="easyui-validatebox" data-options=""></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="text" name="password" class="easyui-validatebox" data-options=""></td>
            </tr>
            <tr>
                <td>手机:</td>
                <td><input type="text" name="tel" class="easyui-validatebox" data-options=""></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input type="text" name="email" class="easyui-validatebox" data-options="validType:'email'"></td>
            </tr>
            <tr>
                <td>入职日期:</td>
                <td><input type="text" id="inputtime" name="inputtime" class="easyui-datebox"></td>
            </tr>
            <tr>
                <td>是否管理员:</td>
                <td><input id="admin" name="admin" placeholder="请选择是否为管理员"></input></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td><input id="department" name="department.id" placeholder="请选择部门"></input></td>
            </tr>
            <tr>
                <td>角色:</td>
                <td><input id="role" name="role.rid" placeholder="请选择角色"></input></td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>
