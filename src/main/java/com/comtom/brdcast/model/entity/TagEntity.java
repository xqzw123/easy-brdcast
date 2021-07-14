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
@TableName(value = "eb_tag", resultMap = "tagMap")
@ApiModel(value = "标签表", description = "标签表")
public class TagEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id 主键", name = "id")
    private String id;

    @TableField(value = "name" )
    @ApiModelProperty(value = "标签名称", name = "name")
    private String name;

}
