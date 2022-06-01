package com.myjava.web;

import com.myjava.domain.PageBean;
import com.myjava.domain.User;
import com.myjava.service.PhotoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.server.UID;

@WebServlet("/photoListServlet")
public class PhotoListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PhotoServiceImpl photoService = new PhotoServiceImpl();
        /**获取当前页和一页展示多少条数据*/
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (pageSize == null || "".equals(pageSize)){
            pageSize = "15";
        }
        PageBean pageBean = null;
        String searchText = (String) session.getAttribute("searchText");
        if(searchText !=null || "".equals(searchText)){
            pageBean = photoService.getPageData(searchText,Integer.valueOf(currentPage), Integer.valueOf(pageSize), user.getU_id());
        }else {
            pageBean = photoService.getPageData(Integer.valueOf(currentPage), Integer.valueOf(pageSize), user.getU_id());
        }
        /**从数据库当中 获取信息 展示*/
        /*List<News> allNews = photoService.getNewsWithCategory(categoryId);*/
        request.setAttribute("pageBean",pageBean);
        request.setAttribute("photos",pageBean.getDataList());
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
