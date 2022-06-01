package com.myjava.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myjava.domain.*;
import com.myjava.mapper.EmployeeMapper;
import com.myjava.service.EmployeeService;
import com.myjava.util.springShiro.GetMD5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public PageListRes getEmployeeList(QueryVo queryVo) {
        Page<Object> page = PageHelper.startPage(queryVo.getPage(),queryVo.getRows());
        List<Employee> employees = employeeMapper.selectAll(queryVo.getKeyword());
        PageInfo<Employee> pageInfo = new PageInfo<>(employees,queryVo.getPage());
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(pageInfo.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }

    @Override
    public AjaxResult addOneEmployee(Employee employee) {
        //对密码加密
        String password = GetMD5Password.getPassword(employee.getPassword(), employee.getUsername(), 2);
        employee.setPassword(password);
        int insert = employeeMapper.insert(employee);
        //更新员工和角色的关系表,如果角色表为空或者null,就不更新关系表
        if (employee.getRoleList() == null || employee.getRoleList().isEmpty()){
            System.out.println("员工没有角色");
        }else {
            employeeMapper.updateEmployeeRoleRel(employee);
        }
        return AjaxResult.getAjaxResult(insert,"添加成功","添加失败");
    }

    @Override
    public AjaxResult updateEmployee(Employee employee) {
        //先删除员工和角色的关系表,再更新
        employeeMapper.deleteEmployeeRoleRel(employee.getId());
        int i = employeeMapper.updateByPrimaryKey(employee);
        employeeMapper.updateEmployeeRoleRel(employee);
        return AjaxResult.getAjaxResult(i,"更新成功","更新失败");
    }

    @Override
    public AjaxResult updateEmployeeState(Long id) {
        int i = employeeMapper.updateStateByPrimaryKey(id);
        return AjaxResult.getAjaxResult(i,"设置离职成功","设置离职失败");
    }

    @Override
    public List<Long> getRoleByEid(Long eid) {
        List<Long> roleByEid = employeeMapper.getRoleByEid(eid);
        return roleByEid;
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        Employee employee = employeeMapper.selectByUsername(username);
        return employee;
    }

    @Override
    public Set<String> getEmployeeRolesByEid(Long eid) {
        return employeeMapper.getEmployeeRolesByEid(eid);
    }

    @Override
    public Set<String> getEmployeePermissionsByEid(Long eid) {
        return employeeMapper.getEmployeePermissionsByEid(eid);
    }


}
