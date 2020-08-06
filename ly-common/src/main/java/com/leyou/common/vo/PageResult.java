package com.leyou.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class PageResult<T> {
    private Long total;
    private Long totalPage;
    private List<T> items;

    public PageResult() {
    }

    public PageResult(Long total, Long totalPage) {
        this.total = total;
        this.totalPage = totalPage;
    }

    public PageResult(Long total, Long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
