package com.comtom.brdcast.common.natives.entity;

import lombok.Data;

@Data
public class NativeDevInitReq {

    /**
     * 设备ID
     */
    private String DevId;

    /**
     * 音量
     */
    private int Vol;
}
