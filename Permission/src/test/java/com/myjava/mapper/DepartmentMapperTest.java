package com.myjava.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DepartmentMapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Test
    public void selectByPrimaryKey(){

    }

}
