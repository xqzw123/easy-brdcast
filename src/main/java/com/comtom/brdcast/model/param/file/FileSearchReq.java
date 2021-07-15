package com.comtom.brdcast.model.param.file;

import com.comtom.brdcast.model.param.BasePageReq;
import lombok.Data;

@Data
public class FileSearchReq extends BasePageReq {
    private String fileName;
}
