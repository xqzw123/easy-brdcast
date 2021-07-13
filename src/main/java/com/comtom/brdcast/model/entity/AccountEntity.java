package com.comtom.brdcast.model.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName(value = "eb_account_info", resultMap = "accountMap")
@ApiModel(value = "用户表", description = "用户表")
public class AccountEntity implements Serializable {

	private static final long serialVersionUID = 1L;

     /**
      * id 主键
      **/
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id 主键", name = "id")
    private Long id;
    /**
     * 用户名
     **/
    @TableField(value = "account")
    @ApiModelProperty(value = "用户名", name = "account")
    private String account;
    /**
     * 登陆密码
     **/
    @TableField(value = "password")
    @ApiModelProperty(value = "登陆密码", name = "password")
    private String password;


}
