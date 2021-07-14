package com.comtom.brdcast.common.natives.entity;

import lombok.Data;

@Data
public class NativeFileObj {
    private String FilePath;
    /**
     * 文件在整个文件列表中序号
     */
    private int FileNo;
}
