package com.myjava.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageBean {
    /**当前页*/
    private Integer currentPage;
    /**共多少页*/
    private Integer totalPage;
    /**共多少记录*/
    private Long totalCount;
    /**一页显示多少数据*/
    private Integer pageSize;
    /**当前页数据*/
    private List dataList = new ArrayList<Photo>();

    public PageBean() {
    }

    public PageBean(Integer currentPage,Integer pageSize, Integer totalPage, Long totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
    }

}
