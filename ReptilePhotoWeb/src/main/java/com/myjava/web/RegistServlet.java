package com.myjava.web;

import com.myjava.domain.User;
import com.myjava.service.UserServiceImpl;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //接收请求，把数据封装成对象
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
            //调用业务层
            UserServiceImpl userService = new UserServiceImpl();
            int j = userService.queryUserByName(user.getUsername());
            if (j==1){
                response.setHeader("refresh","url=/regist.jsp");
                return;
            }
            int i = userService.addUser(user);
            if (i==1){
                String realPath = getServletContext().getRealPath("/");
                File file = new File(realPath+"/images/image/"+user.getU_id());
                file.mkdirs();
                //跳转到登录页面
                response.setHeader("refresh","1;url=/login.jsp");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}