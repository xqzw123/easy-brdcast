package com.comtom.brdcast.model.param.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "新增终端请求数据", description = "新增终端请求数据")
public class DeviceSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序列号，多个用逗号分隔", name = "sequences", required = true)
    private List<String> sequences;

    @ApiModelProperty(value = "设备名称", name = "name", required = true)
    private String name;

    @ApiModelProperty(value = "设备音量", name = "volume", required = true)
    private int volume;

    @ApiModelProperty(value = "序列号id集合", name = "sequences")
    private List<String> tags;

    @ApiModelProperty(value = "序列号name，逗号分隔", name = "sequences")
    private String tagNames;
}
