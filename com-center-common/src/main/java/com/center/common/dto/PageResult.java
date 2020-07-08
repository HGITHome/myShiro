package com.center.common.dto;

import java.util.List;

/**
 * @author heyutang
 * @Title: com.center.common.dto
 * @Package
 * @Description:
 * @Company
 * @date 2020/7/8 15:04
 */
public class PageResult {

    private Long total;
    private List rows;

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
