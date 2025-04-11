package com.example.travelagency.vo;

import java.util.List;

public class PageInfo<T> {
    private List<T> items;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private int totalItems;

    public PageInfo(List<T> items, int currentPage, int totalPages, int pageSize, int totalItems) {
        this.items = items;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }

    // Getter 메소드들 (Lombok의 @Getter 어노테이션을 사용할 수도 있습니다.)

    public List<T> getItems() {
        return items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }
}