package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.service.PermissionService;
import com.softeem.crm.service.UserService;
import com.softeem.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 系统登录页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }


    // 系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 后端管理主页面
     *
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request, HttpSession session) {
        //此方法帮我们从request请求域中获取cookie对象，再从cookie对象中获取userIdStr并解码
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        request.setAttribute("user", userService.getById(id));//通过主键id查询出user对象，并将它保存到request请求域中
        List<String> permissions = permissionService.queryUserHasRolesHasPermissions(id);
        session.setAttribute("permissions", permissions);
        return "main";
    }
}
