package com.e3expo.mms.bean.param;

import java.util.List;

public class PageParam<T> {
    // 页码
    private int pageIndex = 1;
    // 每页条目数
    private int pageSize = 10;
    // 分页数据
    private List<T> data;
    // 起始位置
    private int beginIndex;
    // 总条数
    private int total;
    // 总页数
//    private int totalPages;
    // 分页半径
    private int pageRadius = 5;

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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}