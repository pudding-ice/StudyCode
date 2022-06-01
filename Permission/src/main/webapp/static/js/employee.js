$(function () {
    /*员式数据列表*/
    $("#dg").datagrid({
        url: "/employeeList.action",
        columns: [[
            {field: 'username', title: '姓名', width: 100, align: 'center'},
            {field: 'password', title: '密码', width: 100, align: 'center'},
            {field: 'inputtime', title: '入职时间', width: 100, align: 'center'},
            {field: 'tel', title: '电话', width: 100, align: 'center'},
            {field: 'email', title: '邮箱', width: 100, align: 'center'},
            {
                field: 'department', title: '部门', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.department) {
                        return row.department.name;
                    }
                }
            },
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.state) {
                        return "在职";
                    } else {
                        return "<font style='color: red'>离职</font>"
                    }
                }
            },
            {
                field: 'admin', title: '管理员', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.admin) {
                        return "是";
                    } else {
                        return "否"
                    }
                }
            },
        ]],
        fit: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        toolbar: '#tb',
        pageSize: 15,
        pageList: [10, 15, 20, 30, 40, 50],
    });


    //添加员工表单框
    $('#add_employee').dialog({
        title: '添加员工',
        width: 350,
        height: 500,
        cache: false,
        modal: true,
        closed: true,
        buttons: [{
            text: '提交',
            handler: function () {
                //判断是添加操作还是更新操作,使用隐藏域id的值是否存在来判断
                var id = $("[name='id']").val();
                var url = null;
                if (id) {
                    url = "updateEmployee.action"
                } else {
                    url = "addEmployee.action"
                }
                $("#add_employee_form").form("submit", {
                    url: url,
                    onSubmit: function (param) {
                        var values = $("#role").combobox('getValues')
                        for (var i = 0; i < values.length; i++) {
                            param["roleList[" + i + "].rid"] = values[i];
                        }
                    },
                    success: function (data) {
                        data = $.parseJSON(data)
                        var success = data.success
                        if (success) {
                            alert("提示: " + data.msg)
                            $('#add_employee').dialog("close")
                            $('#dg').datagrid('reload')
                        } else {
                            alert("提示: " + data.msg)
                        }
                    }
                })
            }
        }, {
            text: '关闭',
            handler: function () {
                $('#add_employee').dialog('close')
            }
        }]
    })
    $('#add').click(function () {
        //先清理数据
        $('#add_employee').dialog("setTitle", "添加员工")
        $('#add_employee').dialog("open")
        $('#add_employee').dialog("center")
        $('#add_employee_form').form('clear')

    })
    //监听编辑按钮的点击,打开dialog,回显数据
    $('#edit').click(function () {
        var selectedRow = $('#dg').datagrid('getSelected');
        if (selectedRow == null) {
            alert("please select one row")
        } else {
            $('#add_employee_form').form('clear')
            $('#add_employee').dialog("setTitle", "编辑员工")
            $('#add_employee').dialog("open")
            $('#add_employee').dialog("center")
            selectedRow["department.id"] = selectedRow["department"].id
            //根据eid 回显对应的角角色
            var roleList = selectedRow["roleList"];
            var roles_id = new Array()
            for (var i = 0; i < roleList.length; i++) {
                roles_id.push(roleList[i].rid)
            }
            $("#role").combobox('setValues', roles_id)
            //回显管理员
            if (selectedRow["admin"] == true) {
                selectedRow["admin"] = "true"
            } else {
                selectedRow["admin"] = "false"
            }
            $('#add_employee_form').form('load', selectedRow)
        }
    })
    //监听删除按钮,执行离职操作
    $('#delete').click(function () {
        //判断是否选中,没有选中就提示先选中
        var selectedRow = $('#dg').datagrid('getSelected');
        if (!selectedRow) {
            alert("please select one row to deleted")
        } else if (selectedRow.state === false) {
            alert("employee is already leave ,please select another")
        } else {
            //选中一行,继续处理
            $.messager.confirm("确认", "是否设置为离职", function (res) {
                if (res) {
                    //执行离职更新
                    $.get("/updateEmployeeState.action?id=" + selectedRow.id, function (data) {
                        if (!data.success) {
                            $.messager.alert("提示:", data.msg);
                        } else {
                            $.messager.alert("提示", data.msg, 'info', function () {
                                $('#dg').datagrid('reload');
                            })
                        }
                    })
                }
            })
        }
    })
    //监听刷新按钮,清除搜索框内容,重新加载数据表格
    $('#reload').click(function () {
        $("[name=keyword]").val('')
        $('#dg').datagrid('load', {keyword: ""})
    })


    $("#department").combobox({
        url: '/getDepartmentList.action',
        valueField: 'id',
        textField: 'name',
        width: 150,
        panelHeight: 'auto',
        editable: false,
    })
    $("#admin").combobox({
        valueField: 'value',
        textField: 'label',
        width: 160,
        panelHeight: 'auto',
        editable: false,
        data: [{
            label: '是',
            value: 'true'
        }, {
            label: '否',
            value: 'false'
        }],
        setValue: "false",
        select: "否"
    })

    $("#role").combobox({
        url: '/getAllRoleList.action',
        valueField: 'rid',
        textField: 'rname',
        width: 150,
        panelHeight: 'auto',
        editable: false,
        multiple: true,
    })

    //用来设置选择框中的内容
    $("#department").each(function (i) {
        var span = $(this).siblings("span")[i];
        var targetInput = $(span).find("input:first");
        if (targetInput) {
            $(targetInput).attr("placeholder", $(this).attr("placeholder"));
        }
    });
    $("#admin").each(function (i) {
        var span = $(this).siblings("span")[i];
        var targetInput = $(span).find("input:first");
        if (targetInput) {
            $(targetInput).attr("placeholder", $(this).attr("placeholder"));
        }
    });
    $("#role").each(function (i) {
        var span = $(this).siblings("span")[i];
        var targetInput = $(span).find("input:first");
        if (targetInput) {
            $(targetInput).attr("placeholder", $(this).attr("placeholder"));
        }
    });

    //搜索框按钮
    $('#searchbtn').click(function () {
        var keyWord = $("[name = keyword]").val()
        $('#dg').datagrid('load', {
            keyword: keyWord
        })
    })


});