package com.comtom.brdcast.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.comtom.brdcast.model.entity.FileEntity;
import com.comtom.brdcast.model.entity.FileResult;
import com.comtom.brdcast.model.param.file.FileSearchReq;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface FileService {
    FileResult uploadFile(MultipartFile multipart,String parentNodeId) throws IOException;

    Boolean newFolder(String folderName, String parentNodeId) throws IOException;

    Boolean rename(String fileId, String fileName) throws IOException;

    Boolean fileDel(List<String> fileIds) throws IOException;

    IPage<FileEntity> filePage(FileSearchReq fileSearchReq);

    void downLoad(String fileId, HttpServletResponse response);
}
