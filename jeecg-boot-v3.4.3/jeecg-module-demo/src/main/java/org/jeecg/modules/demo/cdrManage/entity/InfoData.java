package org.jeecg.modules.demo.cdrManage.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class InfoData implements Serializable {

    private String id;

    @ApiModelProperty(value = "分页")
    private Integer pageNo;

    @ApiModelProperty(value = "分页")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}



