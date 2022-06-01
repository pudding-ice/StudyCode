package com.myjava.controller;

//import com.myjava.domain.Department;
import com.myjava.domain.Department;
import com.myjava.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用于处理部门请求
 * @author Pudding
 */
@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/getDepartmentList")
    @ResponseBody
    public List<Department> getDepartmentList(){
        List<Department> departmentList = departmentService.getDepartmentList();
        return departmentList;
    }
}
