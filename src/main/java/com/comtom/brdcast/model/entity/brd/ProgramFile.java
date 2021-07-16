package com.comtom.brdcast.model.entity.brd;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "eb_program")
@ApiModel(value = "节目文件信息", description = "节目文件信息")
public class ProgramFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id 主键", name = "id")
    private String id;

    @TableField(value = "program_id" )
    @ApiModelProperty(value = "节目id", name = "programId")
    private String programId;

    @TableField(value = "file_id")
    @ApiModelProperty(value = "创建时间", name = "fileId")
    private String fileId;

    @TableField(value = "order")
    @ApiModelProperty(value = "有效日期", name = "order")
    private int order;
}
