package com.myjava.controller.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myjava.domain.AjaxResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class LoginFormFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        AjaxResult result = AjaxResult.getAjaxResult(1, "登录成功", "");
        String jsonString = new ObjectMapper().writeValueAsString(result);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonString);
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        //获取失败信息
        String name = e.getClass().getName();
        String msg = null;
        if (name!=null){
            if (name.equals(UnknownAccountException.class.getName())){
                msg = "账号错误";
            }else if(name.equals(IncorrectCredentialsException.class.getName())){
                msg = "密码错误";
            }else {
                msg = "未知错误";
            }
        }
        AjaxResult ajaxResult = AjaxResult.getAjaxResult(0, "", msg);
        response.setCharacterEncoding("utf-8");
        try {
            String s = new ObjectMapper().writeValueAsString(ajaxResult);

            response.getWriter().print(s);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }
}
