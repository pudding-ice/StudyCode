package com.myjava.domain;

import lombok.Data;

/**
 * 接收来自前端的数据, pagenum ,pagesize,keyword
 * @author Pudding
 */
@Data
public class QueryVo {
    Integer page;
    Integer rows;
    String keyword;
}
