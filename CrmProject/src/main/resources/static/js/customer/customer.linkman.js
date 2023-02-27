layui.use(['table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //计划项数据展示
    var tableIns = table.render({
        elem: '#cusLinkManList',
        url: ctx + '/link/list?cusId=' + $("input[name='id']").val(),
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "cusLinkManList",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "id", title: '编号', fixed: "true"},
            {field: 'linkName', title: '联系人姓名', align: "center"},
            {field: 'sex', title: '性别', align: "center"},
            {field: 'zhiwei', title: '职位', align: "center"},
            {field: 'officePhone', title: '工作电话', align: "center"},
            {field: 'phone', title: '电话', align: "center"},
            {field: 'createDate', title: '创建时间', align: "center"},
            {field: 'updateDate', title: '更新时间', align: "center"},
            {title: '操作', fixed: "right", align: "center", minWidth: 150, templet: "#cusLinkManListBar"}
        ]]
    });
    //头工具栏事件
    table.on('toolbar(cusLinkMans)', function (obj) {
        switch (obj.event) {
            case "add":
                openAddOrUpdateCusLinkManDialog();
                break;
        }
        ;
    });


    /**
     * 行监听
     */
    table.on("tool(cusLinkMans)", function (obj) {
        var layEvent = obj.event;
        if (layEvent === "edit") {
            openAddOrUpdateCusLinkManDialog(obj.data.id);
        } else if (layEvent === "del") {
            layer.confirm('确定删除当前数据？', {icon: 3, title: "客户联系人管理"}, function (index) {
                $.post(ctx + "/link/delete", {id: obj.data.id}, function (data) {
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
    function openAddOrUpdateCusLinkManDialog(id) {
        var url = ctx + "/link/addOrUpdateCusLinkManPage?cusId=" + $("input[name='id']").val();
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