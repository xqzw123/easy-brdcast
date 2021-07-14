package com.comtom.brdcast.common.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ApiPageResponse<T> extends ApiResult{
    @JSONField(ordinal = 4)
    @ApiModelProperty(value = "总数")
    private long totalCount;

    @JSONField(ordinal = 5)
    @ApiModelProperty(value = "每页记录数")
    private long pageSize;

    @JSONField(ordinal = 6)
    @ApiModelProperty(value = "当前页数")
    private long currPage;

    @JSONField(ordinal = 8)
    @ApiModelProperty(value = "列表数据")
    private List<T> data;

    private static ApiPageResponse response(boolean isOK, int code, String msg) {
        ApiPageResponse response = new ApiPageResponse();
        response.setCode(code);
        response.setSuccessful(isOK);
        response.setMsg(msg);
        return response;
    }

    public ApiPageResponse<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public static ApiPageResponse error(int code, String msg) {
        return response(false, code, msg);
    }



    public static ApiPageResponse ok(String msg) {
        return response(true, ResponseCode.SUCCESS.getCode(), msg);
    }


    public static <T> ApiPageResponse<T> ok(IPage<T> page) {
        return ok(page.getTotal(), page.getCurrent(), page.getSize(), page.getRecords());
    }

    public static <T> ApiPageResponse<T> ok(long total, long page, long limit, List<T> data) {
        ApiPageResponse<T> response = ok("OK");
        response.setData(data);
        response.setTotalCount(total);
        response.setCurrPage(page);
        response.setPageSize(limit);
        return response;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public ApiPageResponse<T> setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public long getPageSize() {
        return pageSize;
    }

    public ApiPageResponse<T> setPageSize(long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @JSONField(ordinal = 7)
    public int getTotalPage() {
        if (pageSize == 0) {
            return 0;
        }
        long tPage = (totalCount % pageSize) == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        return Integer.parseInt(tPage + "");
    }


    public long getCurrPage() {
        return currPage;
    }

    public ApiPageResponse<T> setCurrPage(long currPage) {
        this.currPage = currPage;
        return this;
    }

    public List<T> getData() {
        return data;
    }
}
