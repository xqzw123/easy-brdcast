package com.comtom.brdcast.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.comtom.brdcast.common.api.ApiPageResponse;
import com.comtom.brdcast.common.api.ApiResult;
import com.comtom.brdcast.model.entity.FileEntity;
import com.comtom.brdcast.model.param.file.FileSearchReq;
import com.comtom.brdcast.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/saferest/file")
@Api(description = "上传文件类", tags = {"UploadCtl"})
public class UploadController {

    @Autowired
    private FileService fileService;
    /**
     * 方法描述：文件上传
         parentNodeId 父目录ID
     */
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping("/uploadFile")
    public ApiResult uploadFile(
            MultipartFile multipart,
            @RequestParam(value = "parentNodeId", required = false, defaultValue = "") String parentNodeId
    ) throws IOException {
        fileService.uploadFile(multipart, parentNodeId);
        return ApiResult.success();
    }

    /**
     * 方法描述：文件上传
     parentNodeId 父目录ID
     */
    @ApiOperation(value = "新建文件夹", notes = "新建文件夹")
    @PostMapping("/newFolder")
    public ApiResult newFolder(
            @RequestParam(value = "parentNodeId", defaultValue = "") String parentNodeId,
            @RequestParam(value = "folderName",  defaultValue = "") String folderName
    ) throws IOException {
        fileService.newFolder(folderName, parentNodeId);
        return ApiResult.success();
    }
    /**
     * 方法描述：文件、文件夹重命名
     */
    @ApiOperation(value = "文件重命名", notes = "文件重命名")
    @PostMapping("/fileRename")
    public ApiResult fileRename(
            @RequestParam(value = "fileId", defaultValue = "") String fileId,
            @RequestParam(value = "fileName",  defaultValue = "") String fileName
    ) throws IOException {
        Boolean res = fileService.rename(fileId, fileName);
        return res?ApiResult.success():ApiResult.failure();
    }
    /**
     * 方法描述：删除文件
     parentNodeId 父目录ID
     */
    @ApiOperation(value = "文件重命名", notes = "文件重命名")
    @PostMapping("/fileDel")
    public ApiResult fileDel(
            @RequestParam(value = "fileId", defaultValue = "") List<String> fileIds
    ) throws IOException {
        Boolean res = fileService.fileDel(fileIds);
        return res?ApiResult.success():ApiResult.failure();
    }

    /**
     * 方法描述：文件查询
     */
    @ApiOperation(value = "文件列表查询", notes = "文件列表查询")
    @PostMapping("/filePage")
    public ApiPageResponse<FileEntity> filePage(
            @RequestBody FileSearchReq fileSearchReq
    ) throws IOException {
        IPage<FileEntity> res = fileService.filePage(fileSearchReq);
        return ApiPageResponse.ok(res);
    }
    /**
     */
    @ApiOperation(value = "文件下载", notes = "文件下载")
    @PostMapping("/fileDownload")
    public ApiResult fileDownload(
            @RequestParam(value = "fileId", defaultValue = "") String fileId,
            HttpServletResponse response
    ) {
        fileService.downLoad(fileId,response);
        return ApiResult.success();
    }
}
