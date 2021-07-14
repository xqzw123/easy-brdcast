package com.comtom.brdcast.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表实体类
 *
 * @author zengwei
 * @date 2020-09-01 13:24:08
 */
@Data
@TableName(value = "eb_device_info", resultMap = "deviceMap")
@ApiModel(value = "设备表", description = "设备表")
public class DeviceEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id 主键", name = "id")
    private String id;

    @TableField(value = "sequence" )
    @ApiModelProperty(value = "序列号", name = "sequence")
    private String sequence;

    @TableField(value = "name")
    @ApiModelProperty(value = "设备名称", name = "name")
    private String name;

    @TableField(value = "volume")
    @ApiModelProperty(value = "设备音量", name = "volume")
    private int volume;

    @TableField(value = "status")
    @ApiModelProperty(value = "设备工作状态  0-空闲 1-工作", name = "status")
    private String status;

    @TableField(value = "online")
    @ApiModelProperty(value = "设备在线状态 0-离线 1-在线", name = "name")
    private String online;

    @TableField(value = "ip")
    @ApiModelProperty(value = "设备ip", name = "ip")
    private String ip;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private String createTime;

    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    private String updateTime;

    @ApiModelProperty(value = "设备标签id,逗号分隔", name = "tags")
    @TableField(value = "tags")
    private String tags;

    @ApiModelProperty(value = "设备标签,逗号分隔", name = "tag_names")
    @TableField(value = "tag_names")
    private String tagNames;




}
