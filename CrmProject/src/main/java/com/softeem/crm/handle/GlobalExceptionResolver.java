package com.softeem.crm.handle;

import com.alibaba.fastjson.JSON;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.exceptions.NoAuthException;
import com.softeem.crm.exceptions.NoLoginException;
import com.softeem.crm.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 全局异常统一处理器
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        /**方法返回值类型判断:
         *    如果方法级别存在@ResponseBody 方法响应内容为json  否则视图
         *    handler 参数类型为HandlerMethod
         * 返回值
         *    视图:默认错误页面
         *    json:错误的json信息
         */

        /**
         * 首先判断异常类型
         *   如果异常类型为未登录异常  执行视图转发
         */
        ModelAndView mv = new ModelAndView();
        if (ex instanceof NoLoginException) {
            NoLoginException ne = (NoLoginException) ex;
            mv.setViewName("no_login");
            mv.addObject("msg", ne.getMsg());
            mv.addObject("ctx", request.getContextPath());
            return mv;
        }

        /**
         后续代码省略
         */
        mv.setViewName("error");
        mv.addObject("code", 400);
        mv.addObject("msg", "系统异常,请稍后再试...");
        if (handler instanceof HandlerMethod) {
            //handel（控制器）就是controller，handleMethod就是控制器(Controller)里面的方法
            HandlerMethod hm = (HandlerMethod) handler;
            //看方法头上有没有ResponseBody这个注解
            ResponseBody responseBody = hm.getMethod().getDeclaredAnnotation(ResponseBody.class);
            if (null == responseBody) {
                /**
                 * 方法返回视图
                 */
                if (ex instanceof ParamsException) {
                    ParamsException pe = (ParamsException) ex;
                    mv.addObject("msg", pe.getMsg());
                    mv.addObject("code", pe.getCode());
                }
                return mv;
            } else {
                /**
                 * 方法返回json
                 */
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统错误，请稍后再试!");
                if (ex instanceof ParamsException) {
                    ParamsException pe = (ParamsException) ex;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                } else if (ex instanceof NoAuthException) {
                    NoAuthException ne = (NoAuthException) ex;
                    resultInfo.setCode(ne.getCode());
                    resultInfo.setMsg(ne.getMsg());
                }
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter pw = null;
                try {
                    pw = response.getWriter();
                    pw.write(JSON.toJSONString(resultInfo));
                    pw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != pw) {
                        pw.close();
                    }
                }
                return null;
            }
        } else {
            return mv;
        }
    }
}