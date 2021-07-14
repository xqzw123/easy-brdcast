package com.comtom.brdcast.common.natives.entity;

import lombok.Data;

@Data
public class NativeDevInfo {
    private String DevId;

    private String Ip;
    /**
     * 音量
     */
    private int Vol;
    /**
     * 版本
     */
    private String Ver;
    /**
     * 在线状态 0-离线 1-在线
     */
    private int OnlineStat;

    /**
     * 终端状态 0-空闲 >0 广播
     */
    private int WorkStat;
}
