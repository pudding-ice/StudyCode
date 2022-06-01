package com.myjava.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Role {
    private Long rid;
    private String rnum;
    private String rname;
    private List<Permission> permissionList = new ArrayList<>();
}
