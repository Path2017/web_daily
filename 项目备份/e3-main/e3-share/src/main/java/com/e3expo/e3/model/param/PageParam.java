package com.e3expo.e3.model.param;

import java.io.Serializable;
import java.util.List;

public class PageParam<T> implements Serializable{
    // 页码
    private Integer pageIndex = 1;
    // 每页条目数
    private Integer pageSize = 10;
    // 分页数据
    private List<T> data;
    // 起始位置
    private Integer beginIndex;
    // 总条数
    private Integer total;
    // 总页数
//    private int totalPages;
    // 分页半径
    private Integer pageRadius = 5;

    public int getStartPageIndex() {
        return pageIndex - pageRadius <= 0 ? 1 : pageIndex - pageRadius;
    }

    public int getEndPageIndex() {
        return pageIndex + pageRadius > getTotalPages() ? getTotalPages() : pageIndex + pageRadius;
    }

    public int getTotalPages() {
        int totalPages = (total + pageSize - 1) / pageSize;
        return totalPages == 0 ? 1 : totalPages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        // 设置总条目数
        if (pageIndex * pageSize > total) {
            // 如果输入页码过大，将页码设置为最后一页
            this.pageIndex = getTotalPages();
        }
    }

    public int getBeginIndex() {
        // 从0开始
        return (pageIndex - 1) * pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}