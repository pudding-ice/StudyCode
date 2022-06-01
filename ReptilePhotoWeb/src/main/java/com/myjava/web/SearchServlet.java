package com.myjava.web;

import com.myjava.domain.PageBean;
import com.myjava.domain.Photo;
import com.myjava.domain.User;
import com.myjava.service.PhotoServiceImpl;
import com.myjava.service.PhotoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //如果没有输入内容,直接点搜索,则返回该用户所有的图片
        HttpSession session = request.getSession();
        String searchName = request.getParameter("searchText");
        session.setAttribute("searchText",searchName);
        User user = (User) session.getAttribute("user");
        PhotoService photoService = new PhotoServiceImpl();
        if (searchName==null || "".equals(searchName)){
            searchName = "";
        }
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        if (currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (pageSize == null || "".equals(pageSize)){
            pageSize = "15";
        }
        /**从数据库当中 获取信息 展示*/
        /*List<News> allNews = photoService.getNewsWithCategory(categoryId);*/
        PageBean pageBean = photoService.getPageData(searchName,Integer.valueOf(currentPage), Integer.valueOf(pageSize), user.getU_id());
        //登录成功,查询用户对应的图片,把图片集合,用户存到session中
        session.setAttribute("user",user);
        session.setAttribute("photos",pageBean.getDataList());
        session.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }
}
