package com.tc.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ToString
public class PageResult<T> {
    private Long total;// 总条数
    private Integer totalPage;// 总页数
    private List<T> items;// 当前页数据
    public PageResult(){

    }

    public PageResult(Long total) {
        this.total = total;
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public static <T> PageResult newPageResult(List<T> list){
        if (list instanceof Page){
            PageInfo<T> info = new PageInfo<>(list);
            return new PageResult<>(info.getTotal(),info.getList());
        }
        throw new IllegalArgumentException("错误的参数");
    }
}