package com.myjava.service.Impl;

import com.myjava.domain.Department;
import com.myjava.mapper.DepartmentMapper;
import com.myjava.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public List<Department> getDepartmentList() {
        List<Department> departments = departmentMapper.selectAll();
        return departments;
    }
}
