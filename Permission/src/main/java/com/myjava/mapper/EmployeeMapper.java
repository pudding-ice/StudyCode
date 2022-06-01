package com.myjava.mapper;

import com.myjava.domain.Employee;

import java.util.List;
import java.util.Set;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll(String keyword);

    int updateByPrimaryKey(Employee record);

    int updateStateByPrimaryKey(Long id);

    List<Long> getRoleByEid(Long eid);

    int updateEmployeeRoleRel(Employee employee);

    int deleteEmployeeRoleRel(Long eid);

    Employee selectByUsername(String username);


    Set<String> getEmployeeRolesByEid(Long eid);

    Set<String> getEmployeePermissionsByEid(Long eid);
}