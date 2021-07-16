package com.comtom.brdcast.model.entity.brd;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "eb_program")
@ApiModel(value = "节目信息", description = "节目信息")
public class ProgramEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "program_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id 主键", name = "id")
    private String id;

    @TableField(value = "program_name" )
    @ApiModelProperty(value = "节目名称", name = "programName")
    private String programName;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    @TableField(value = "valid_time")
    @ApiModelProperty(value = "有效日期", name = "validTime")
    private Date validTime;

    @TableField(value = "strategy_type")
    @ApiModelProperty(value = "设备工作状态  0-一次性 1-周期性", name = "strategyType")
    private String strategyType;

    @TableField(value = "creater")
    @ApiModelProperty(value = "节目创建者", name = "creater")
    private String creater;

    @TableField(value = "status")
    @ApiModelProperty(value = "节目状态", name = "0-待播发 1-播发中 2-播发完成")
    private String status;

}
