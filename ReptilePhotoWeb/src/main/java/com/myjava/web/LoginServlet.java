package com.myjava.web;
import com.myjava.domain.PageBean;
import com.myjava.domain.Photo;
import com.myjava.domain.User;
import com.myjava.service.PhotoService;
import com.myjava.service.PhotoServiceImpl;
import com.myjava.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码防止乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //接收参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        //            BeanUtils.populate(user,parameterMap);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);
        //调用业务层
        UserServiceImpl userService = new UserServiceImpl();
        user = userService.getUser(user);
        if (user==null){
            //登录失败
            request.setAttribute("loginInfo","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            String currentPage = request.getParameter("currentPage");
            String pageSize = request.getParameter("pageSize");
            if (currentPage == null || "".equals(currentPage)){
                currentPage = "1";
            }
            if (pageSize == null || "".equals(pageSize)){
                pageSize = "15";
            }
            /**从数据库当中 获取信息 展示*/
            PhotoServiceImpl photoService = new PhotoServiceImpl();
            /*List<News> allNews = photoService.getNewsWithCategory(categoryId);*/
            PageBean pageBean = photoService.getPageData(Integer.valueOf(currentPage), Integer.valueOf(pageSize), user.getU_id());
            //登录成功,查询用户对应的图片,把图片集合,用户存到session中
//            ArrayList<Photo> photos = photoService.getPhotoByUid(user.getU_id());
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            session.setAttribute("photos",pageBean.getDataList());
            session.setAttribute("pageBean",pageBean);
            //获取用户下载图片的默认路径,再提供两个路径
//            String defaultPath=request.getSession().getServletContext().getRealPath("/")+"images\\image/";
//            String defaultUserPath=request.getSession().getServletContext().getRealPath("/")+"image\\image\\"+user.getU_id();
//            session.setAttribute("defaultPath",defaultPath);
//            session.setAttribute("defaultUserPath",defaultUserPath);
            response.setHeader("refresh","2;url=/index.jsp");
            request.getRequestDispatcher("/index.jsp");

        }
    }
}
