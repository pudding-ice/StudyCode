package com.myjava.controller;

import com.myjava.domain.AjaxResult;
import com.myjava.domain.Employee;
import com.myjava.domain.PageListRes;
import com.myjava.domain.QueryVo;
import com.myjava.service.EmployeeService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.ldap.ExtendedResponse;
import java.util.List;

/**
 * @author Pudding
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/employeeList")
    @ResponseBody
    public PageListRes getEmployeeList(QueryVo queryVo){
        PageListRes employeeList = employeeService.getEmployeeList(queryVo);
        return employeeList;
    }
    @RequestMapping("/addEmployee")
    @ResponseBody
    public AjaxResult addEmployee(Employee employee){
        AjaxResult result = employeeService.addOneEmployee(employee);
        return result;
    }
    @RequestMapping("/updateEmployee")
    @ResponseBody
    public AjaxResult updateEmployee(Employee employee){
        AjaxResult result = employeeService.updateEmployee(employee);
        return result;
    }
    @RequestMapping("/updateEmployeeState")
    @ResponseBody
    public AjaxResult updateEmployeeState(@RequestParam("id") Long id){
        AjaxResult result = employeeService.updateEmployeeState(id);
        return result;
    }
    @RequestMapping("/getRoleByEid")
    @ResponseBody
    public List<Long> getRoleByEid(Long eid){
        List<Long> res = employeeService.getRoleByEid(eid);
        return res;
    }
}
