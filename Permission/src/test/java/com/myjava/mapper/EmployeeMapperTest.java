package com.myjava.mapper;


import com.myjava.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EmployeeMapperTest {
    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void selectAll_Test(){
//        List<Employee> employees = employeeMapper.selectAll(queryVo.getKeyword());
//        System.out.println(employees);
    }
}
