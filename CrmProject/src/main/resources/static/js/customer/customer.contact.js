layui.use(['table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //计划项数据展示
    var tableIns = table.render({
        elem: '#cusContactList',
        url: ctx + '/contact/list?cusId=' + $("input[name='id']").val(),
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "cusContactList",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "id", title: '编号', fixed: "true"},
            {field: 'contactTime', title: '交易往来时间', align: "center"},
            {field: 'address', title: '地址', align: "center"},
            {field: 'overview', title: 'overview', align: "center"},
            {field: 'createDate', title: '创建时间', align: "center"},
            {field: 'updateDate', title: '更新时间', align: "center"},
            {title: '操作', fixed: "right", align: "center", minWidth: 150, templet: "#cusContactListBar"}
        ]]
    });
    //头工具栏事件
    table.on('toolbar(cusContacts)', function (obj) {
        switch (obj.event) {
            case "add":
                openAddOrUpdateCusContactDialog();
                break;
        }
        ;
    });


    /**
     * 行监听
     */
    table.on("tool(cusContacts)", function (obj) {
        var layEvent = obj.event;
        if (layEvent === "edit") {
            openAddOrUpdateCusContactDialog(obj.data.id);
        } else if (layEvent === "del") {
            layer.confirm('确定删除当前数据？', {icon: 3, title: "交易往来管理"}, function (index) {
                $.post(ctx + "/contact/delete", {id: obj.data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg("操作成功！");
                        tableIns.reload();
                    } else {
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            })
        }
    });


    // 打开添加计划项数据页面
    function openAddOrUpdateCusContactDialog(id) {
        var url = ctx + "/contact/addOrUpdateCusContactPage?cusId=" + $("input[name='id']").val();
        var title = "客户联系人管理-添加联系人";
        if (id) {
            url = url + "&id=" + id;
            title = "客户联系人管理-修改联系人";
        }
        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "400px"],
            maxmin: true,
            content: url
        });
    }
});