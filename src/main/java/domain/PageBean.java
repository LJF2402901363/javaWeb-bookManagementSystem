package domain;

import java.util.List;

/**
 * @author 陌意随影
 * @create 2020-02-02 12:23
 * @desc  封装了分页查询的条件以及查询的结果
 **/
public class PageBean<T> {
    //当前页码
    private int currPage;
    //查询的起始偏量
    private int offset;
    //当前的页面数量
    private int pageSize;
    //查询结果的集合
    private List<T> list;
    //总记录数
    private int total;

    public PageBean() {
    }

    public PageBean(int currPage, int offset, int pageSize, List<T> list, int total) {
        this.currPage = currPage;
        this.offset = offset;
        this.pageSize = pageSize;
        this.list = list;
        this.total = total;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currPage=" + currPage +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                ", list=" + list +
                ", total=" + total +
                '}';
    }
}
