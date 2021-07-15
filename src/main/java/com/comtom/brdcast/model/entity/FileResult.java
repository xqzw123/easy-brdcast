package com.comtom.brdcast.model.entity;

import lombok.Data;

/**
 * 文件上传返回的数据实体
 *
 * @author lixingwu
 */
@Data
public class FileResult {
    /**
     *
    文件名
     */
    private String fileName;
    // 扩展名
    private String extName;

    // 文件大小，字节
    private Long fileSize;

    // 文件存储在服务器的相对地址
    private String serverPath;
}