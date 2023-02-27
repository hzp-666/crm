layui.use(['form', 'jquery', 'jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    //监听提交
    form.on('submit(saveBtn)', function (data) {
        $.ajax({
            type: "post",
            url: ctx + "/user/updatePassword",
            data: {
                oldPassword: data.field.old_password,
                newPassword: data.field.new_password,
                confirmPassword: data.field.again_password
            },
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    layer.msg("密码更新成功,系统将在3秒后自动退出....", function () {
                        $.removeCookie("userIdStr", {domain: "localhost", path: "/crm"});
                        $.removeCookie("userName", {domain: "localhost", path: "/crm"});
                        $.removeCookie("trueName", {domain: "localhost", path: "/crm"});
                        setTimeout(function () {
                            // 内嵌的页面，所以要加parent才能跳转到index页面
                            window.parent.location.href = ctx + "/index";
                        }, 3000);
                    })
                } else {
                    layer.msg(data.msg);
                }
            }
        });
        return false;
    });
});