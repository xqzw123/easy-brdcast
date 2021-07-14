package com.comtom.brdcast.common.natives.entity;

import lombok.Data;

import java.util.List;

@Data
public class NativeBrdReq {
    private String EbmId;

    /**
     * 设备ID列表
     */
    private List<String> DevIDList;

    private List<NativeFileObj> FileList;

    /**
     * 重复次数
     */
    private int RepeatTimes;

    private String StartTime;

    private String EndTime;
}
