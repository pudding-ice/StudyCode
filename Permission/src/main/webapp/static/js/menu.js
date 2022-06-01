$(function () {
    //用户菜单页面
    $("#menu_datagrid").datagrid({
        url: "/getMenuList.action",
        columns: [[
            {field: 'text', title: "名称", width: 1, align: 'center'},
            {field: 'url', title: "url", width: 1, align: 'center'},
            {
                field: 'parent', title: "父菜单", width: 1, align: 'center', formatter: function (value, row, index) {
                    return value ? value.text : '';
                }
            }
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#menu_toolbar'
    });

    /*
     * 初始化新增/编辑对话框
     */
    $("#menu_dialog").dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#menu_dialog_bt'
    });
    //保存操作
    $("#save").click(function () {
        //判断是添加还是编辑
        var id = $("[name='id']").val();
        var url = null;
        if (id) {
            url = "updateMenu.action"
        } else {
            url = "addMenu.action"
        }
        $("#menu_form").form("submit", {
            url: url,
            onSubmit: function (param) {

            },
            success: function (data) {
                data = $.parseJSON(data)
                var success = data.success
                if (success) {
                    alert("提示: " + data.msg)
                    $('#menu_dialog').dialog("close")
                    $('#menu_datagrid').datagrid('reload')
                    // $('#tree').tree("reload",{  url:'/getMenuTree.action'})
                } else {
                    alert("提示: " + data.msg)
                }
            }
        });
    })
    $("#cancel").click(function () {
        $("#menu_form").form("clear")
        $("#menu_dialog").dialog("close");
    })

    $("#add").click(function () {
        $("#menu_form").form("clear")
        $("#menu_dialog").dialog("open");
        //获取父菜单列表
        $("#parentMenu").combobox({
            url: '/getParentMenuList.action',
            valueField: 'id',
            textField: 'text',
            width: 150,
            panelHeight: 'auto',
            editable: false,
        })
    });

    $("#edit").click(function () {
        var selectRow = $("#menu_datagrid").datagrid("getSelected");
        if (!selectRow) {
            alert("please select one row")
        } else {
            console.log(selectRow);
            selectRow["parent.id"] = selectRow["parent"].id
            $("#menu_form").form("load", selectRow)
            $("#menu_dialog").dialog("open");
            //获取父菜单列表
            $("#parentMenu").combobox({
                url: '/getParentMenuList.action',
                valueField: 'id',
                textField: 'text',
                width: 150,
                panelHeight: 'auto',
                editable: false,
            })
            $("#parentMenu").combobox("setValue", selectRow["parent.id"])
        }
    })
    $("#del").click(function () {
        var selectRow = $("#menu_datagrid").datagrid("getSelected");
        if (!selectRow) {
            alert("please select one row to delete")
        } else {
            $.get("/deleteMenu.action?id=" + selectRow.id, function (data) {
                if (data.success) {
                    alert(data.msg)
                    $('#menu_datagrid').datagrid('reload')
                    // $('#tree').tree("reload",{  url:'/getMenuTree.action'})
                } else {
                    alert(data.msg)
                    $('#menu_datagrid').datagrid('reload')
                }
            })
        }
    })

    //监听刷新按钮,清除搜索框内容,重新加载数据表格
    $('#reload').click(function () {
        $('#menu_datagrid').datagrid('load', {
            url: "/getMenuList.action"
        })
    })
});