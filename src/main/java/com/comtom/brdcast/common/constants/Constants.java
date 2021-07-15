package com.comtom.brdcast.common.constants;

import lombok.Getter;
import lombok.ToString;

/**
 * @Description: 返回码枚举
 * @Author zengwei
 * @Date 2021/07/09
 */
@Getter
@ToString
public enum Constants {

    /**
     * 成功与失败
     */
    FILE_STATUS_OK(0, "上传成功"),
    FILE_STATUS_PROG(1, "上传中"),
    FILE_STATUS_FAIL(2, "上传失败"),

    FILE_TYPE_DIRECTORY(0,"文件夹"),
    FILE_TYPE_FILE(1,"文件"),
    ;


    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    private Constants(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
