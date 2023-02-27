layui.use(['table', 'layer', "form"], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //订单列表展示
    var tableIns = table.render({
        elem: '#customerLossReprieveList',
        url: ctx + '/cus_reprieve/list?lossId=' + $("input[name='id']").val(),
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "customerLossReprieveListTable",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "id", title: '编号', fixed: "true"},
            {field: 'measure', title: '暂缓措施', align: "center"},
            {field: 'createDate', title: '创建时间', align: "center"},
            {field: 'updateDate', title: '更新时间', align: "center"},
            {title: '操作', fixed: "right", align: "center", minWidth: 150, templet: "#customerLossReprieveBar"}
        ]]
    });

    //头工具栏事件
    table.on('toolbar(customerLossReprieves)', function (obj) {
        switch (obj.event) {
            case "add":
                openAddOrUpdateCusReprieveDialog();
                break;
            case "confirm":
                openConfirmCusReprieveDialog();
                break;
        }
        ;
    });


    /**
     * 行监听
     */
    table.on("tool(customerLossReprieves)", function (obj) {
        var layEvent = obj.event;
        if (layEvent === "edit") {
            openAddOrUpdateCusReprieveDialog(obj.data.id);
        } else if (layEvent === "del") {
            layer.confirm('确定删除当前数据？', {icon: 3, title: "客户联系人管理"}, function (index) {
                $.post(ctx + "/cus_reprieve/delete", {id: obj.data.id}, function (data) {
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
    function openConfirmCusReprieveDialog(id) {
        var url = ctx + "/customer_loss/updateLossById?id=" + $("input[name='id']").val();
        var title = "暂缓管理-确认流失";

        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "400px"],
            maxmin: true,
            content: url
        });
    }

    function openAddOrUpdateCusReprieveDialog(id) {
        var url = ctx + "/cus_reprieve/addOrUpdateCusReprievePage?lossId=" + $("input[name='id']").val();
        var title = "暂缓管理-添加暂缓";
        if (id) {
            url = url + "&id=" + id;
            title = "暂缓管理-修改暂缓";
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