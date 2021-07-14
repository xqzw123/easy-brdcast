package com.comtom.brdcast.common.natives.entity;

import lombok.Data;

@Data
public class NativeBrdResp {
    private String EbmId;

    /**
     * 0-运行组
     * 1-正常停止
     * 2-媒体文件错误
     */
    private int Status;

    private String Msg;
}
