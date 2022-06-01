package com.myjava.domain;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
    /**当前页*/
    private Integer currentPage;
    /**共多少页*/
    private Integer totalPage;
    /**共多少记录*/
    private Long totalCount;
    /**当前页数据*/
    private List dataList = new ArrayList<Photo>();

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", dataList=" + dataList +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }
}
