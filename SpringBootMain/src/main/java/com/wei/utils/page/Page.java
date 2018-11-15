package com.wei.utils.page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanshoaxing on 2018/11/26.
 */
public class Page {
    private static final long serialVersionUID = 1L;
    //前一页
    private Boolean hasPrePage;
    //后一页
    private Boolean hasNextPage;
    //每页显示多少条:默认20条
    private Long everyPage = 20L;
    //总页数
    private Long totalPage;
    //当前第多少页:默认第1页
    private Long currentPage = 1L;
    //开始下标
    private Long beginIndex;
    //结束下标
    private Long endinIndex;
    //总共多少条
    private Long totalCount;
    //排序列名
//    private String sortName;
    //排序状态
    private String sortState = null;
    //排序信息
    private String sortInfo = null;
    //是否排序
    private Boolean sort = false;
    private String defaultInfo = null;
    //返回集合数据
    private List<?> list = new ArrayList();


    public String getDefaultInfo() {
        return defaultInfo;
    }


    public void setDefaultInfo(String defaultInfo) {
        this.defaultInfo = defaultInfo;
    }

    public String getSortInfo() {
        return sortInfo;
    }

    public void setSortInfo(String sortInfo) {
        this.sortInfo = sortInfo;
    }

//    public String getSortName() {
//        return sortName;
//    }

//    public void setSortName(String sortName) {
//        setPageSortState(sortName);
//    }

    public String getSortState() {
        return sortState;
    }

    public void setSortState(String sortState) {
        this.sortState = sortState;
    }


    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }


    public Page() {
    }

    /**
     * 常用,用于计算分页
     * */
    public Page(Long totalRecords){
        this.totalCount = totalRecords;
        setTotalPage(getTotalPage(totalRecords));
    }

    /**
     * 设置每页显示多少条时使用
     * */
    public Page(Long everyPage,Long totalRecords){
        this.everyPage = everyPage;
        this.totalCount = totalRecords;
        setTotalPage(getTotalPage(totalRecords));
    }

    /**
     * @param index   状态值
     * @param value   到第多少页或者设置每页显示多少条或者为排序列名
     */
    public void pageState(int  index,String value) {
        sort = false;
        switch (index) {
            case 0 :setEveryPage(Long.parseLong(value));break;
            case 1 :first();break;
            case 2: previous();break;
            case 3: next();break;
            case 4: last();break;
//            case 5: sort = true;sort(value);break;
            case 6 ://到指定第多少页
                setCurrentPage(Long.parseLong(value));
                break;
        }
    }

    /**
     * 最前一页
     */
    private void first() {
        currentPage = 1L;
    }

    private void previous() {
        currentPage--;
    }

    private void next() {
        currentPage++;
    }

    private void last() {
        currentPage = totalPage;
    }

//    private void sort(String sortName) {
//        //设置排序状态
//        setPageSortState(sortName);
//    }



    /**
     * 计算总页数
     * */
    private Long getTotalPage(Long totalRecords) {
        Long totalPage = 0L;
        everyPage = everyPage == null ? 10L : everyPage;
        if (totalRecords % everyPage == 0)
            totalPage = totalRecords / everyPage;
        else {
            totalPage = totalRecords / everyPage + 1;
        }
        return totalPage;
    }


    public Long getBeginIndex() {
        this.beginIndex = (currentPage - 1) * everyPage;
        return this.beginIndex;
    }

    public void setBeginIndex(Long beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Long getCurrentPage() {
        this.currentPage = currentPage == 0 ? 1 : currentPage;
        return this.currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        if(0 == currentPage){
            currentPage = 1L;
        }
        this.currentPage = currentPage;
    }

    public Long getEveryPage() {
        this.everyPage = everyPage == 0 ? 10 : everyPage;
        return this.everyPage;
    }

    public void setEveryPage(Long everyPage) {
        this.everyPage = everyPage;
    }

    public Boolean getHasNextPage() {
        this.hasNextPage = (currentPage != totalPage) && (totalPage != 0);
        return this.hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public Boolean getHasPrePage() {
        this.hasPrePage = currentPage != 1;
        return this.hasPrePage;
    }

    public void setHasPrePage(Boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public Long getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Long totalPage) {
        if(this.currentPage > totalPage){
//            this.currentPage = totalPage;
            this.totalPage = this.currentPage;
        }
        this.totalPage = totalPage;
    }
    public void setTotalPage(Long totalRecords,Long everyPage) {
        everyPage = everyPage == null ? 10L : everyPage;
        if (totalRecords % everyPage == 0)
            this.totalPage = totalRecords / everyPage;
        else {
            this.totalPage = totalRecords / everyPage + 1;
        }
    }

    public Long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Long totalCount) {
        setTotalPage(getTotalPage(totalCount));
        this.totalCount = totalCount;
    }




    public Boolean isSort() {
        return sort;
    }

    public void setSort(Boolean sort) {
        this.sort = sort;
    }


    public Long getEndinIndex() {
        //this.endinIndex = (currentPage) * everyPage;
        return getEveryPage();
    }

    public void setEndinIndex(Long endinIndex) {
        this.endinIndex = endinIndex;
    }


}
