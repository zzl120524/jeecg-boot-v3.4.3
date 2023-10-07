package org.jeecg.modules.demo.cdrManage.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PageData implements Serializable {
    @ApiModelProperty("每页数据条数")
    private Integer pageSize;
    @ApiModelProperty("当前页码")
    private Integer pageNo;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
