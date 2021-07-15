package com.comtom.brdcast.model.entity;

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
@TableName(value = "eb_file", resultMap = "fileMap")
@ApiModel(value = "文件表", description = "文件表")
public class FileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "file_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id 主键", name = "id")
    private String id;

    @TableField(value = "file_name" )
    @ApiModelProperty(value = "文件名", name = "fileName")
    private String fileName;

    @TableField(value = "file_path")
    @ApiModelProperty(value = "文件路径", name = "filePath")
    private String filePath;

    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父节点id", name = "parentId")
    private String parentId;

    @TableField(value = "file_type")
    @ApiModelProperty(value = "文件类型  0-文件夹 1-文件", name = "fileType")
    private int fileType;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    @TableField(value = "file_status")
    @ApiModelProperty(value = "文件状态 0-上传成功 1-正在上传 2-上传失败", name = "fileStatus")
    private int fileStatus;

}
