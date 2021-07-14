package com.comtom.brdcast.model.param.device;

import com.comtom.brdcast.model.param.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "终端查询列表", description = "终端查询列表")
public class DeviceSearchReq extends BasePageReq {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工作状态", name = "workSta")
    private String workSta;

    @ApiModelProperty(value = "在线状态", name = "onlineSta")
    private String onlineSta;

    @ApiModelProperty(value = "标签", name = "tags")
    private List<String> tags;

    @ApiModelProperty(value = "模糊查询，范围为名称、ip、序列号", name = "obscureSearch")
    private String  obscureSearch;
}
