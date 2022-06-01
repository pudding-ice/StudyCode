package com.myjava.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myjava.domain.AjaxResult;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthenticateController {
    @RequestMapping("/login")
    public String doLogin(){
        return "redirect:/login.jsp";
    }

    @RequestMapping("/employee")
    @RequiresPermissions("employee:index")
    public String goEmployee(){
        return "employee";
    }

    @RequestMapping("/role")
    public String goRole(){
        return "role";

    }

    @RequestMapping("/menu")
    public String goMenu(){
        return "menu";
    }

    //没有权限时的跳转处理
    @ExceptionHandler(AuthorizationException.class)
    public void HandelShiroException(HandlerMethod method,
                                     HttpServletResponse response) throws Exception{
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if (methodAnnotation!=null){
            //是Ajax请求,处理
            AjaxResult ajaxResult = AjaxResult.getAjaxResult(0, "", "没有权限操作!");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(new ObjectMapper().writeValueAsBytes(ajaxResult));
        }else {
            response.sendRedirect("noPermission.jsp");
        }

    }
}
