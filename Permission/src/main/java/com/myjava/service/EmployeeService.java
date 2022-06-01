package com.myjava.service;

import com.myjava.domain.*;

import java.util.List;
import java.util.Set;

/**
 * @author Pudding
 */
public interface EmployeeService {
    PageListRes getEmployeeList(QueryVo queryVo);
    AjaxResult addOneEmployee(Employee employee);
    AjaxResult updateEmployee(Employee employee);
    AjaxResult updateEmployeeState(Long id);

    List<Long> getRoleByEid(Long eid);

    Employee getEmployeeByUsername(String username);

    Set<String> getEmployeeRolesByEid(Long eid);

    Set<String> getEmployeePermissionsByEid(Long eid);
}
