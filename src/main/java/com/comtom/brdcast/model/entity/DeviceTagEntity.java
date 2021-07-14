package com.comtom.brdcast.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "eb_device_tag", resultMap = "deviceTagMap")
@ApiModel(value = "设备表", description = "设备表")
public class DeviceTagEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id 主键", name = "id")
    private String id;

    @TableField(value = "device_id")
    @ApiModelProperty(value = "终端序列号", name = "deviceId")
    private String deviceId;

    @TableField(value = "tag_id")
    @ApiModelProperty(value = "标签id", name = "tagId")
    private String tagId;
}
