package com.example.workflow;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private int pageNumber;
    private int totalPage;
    private boolean hasPrev;
    private boolean hasNext;

    public Page() {
        this.pageNumber = 1;
        this.totalPage = 1;
        this.hasPrev = false;
        this.hasNext = false;
    };

    public Page(int pageNumber, int totalPage) {
        this.pageNumber = pageNumber;
        this.totalPage = totalPage;
        this.hasPrev = this.hasPrev();
        this.hasNext = this.hasNext();
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean getHasPrev() {
        return this.hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public boolean getHasNext() {
        return this.hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    private boolean hasPrev() {
        return this.pageNumber > 1;
    }

    private boolean hasNext() {
        return this.pageNumber < totalPage;
    }
}
