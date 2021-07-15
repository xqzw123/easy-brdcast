package com.comtom.brdcast.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.comtom.brdcast.common.constants.Constants;
import com.comtom.brdcast.common.util.FileuploadUtil;
import com.comtom.brdcast.dao.FileDao;
import com.comtom.brdcast.model.entity.FileEntity;
import com.comtom.brdcast.model.entity.FileResult;
import com.comtom.brdcast.model.param.file.FileSearchReq;
import com.comtom.brdcast.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public FileResult uploadFile(MultipartFile multipart, String parentNodeId) throws IOException {
        if (null == multipart || multipart.isEmpty()) {
            throw new IOException("上传的文件对象不存在");
        }
        FileEntity parentFileEntity = fileDao.selectById(parentNodeId);

        if(parentFileEntity==null){
            throw new IOException("指定父节点不存在");
        }
        FileResult fileResult = FileuploadUtil.saveFile(multipart,parentFileEntity.getFilePath());
        if(fileResult!=null){
            // 上传成功 则数据入库
            FileEntity fileEntity = new FileEntity();
            fileEntity.setCreateTime(new Date());
            fileEntity.setFileName(fileResult.getFileName());
            fileEntity.setFilePath(fileResult.getServerPath());
            fileEntity.setFileStatus(Constants.FILE_STATUS_OK.getCode());
            fileEntity.setFileType(Constants.FILE_TYPE_FILE.getCode());
            fileEntity.setParentId(parentNodeId);
            fileDao.insert(fileEntity);
        }
        return fileResult;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Boolean newFolder(String folderName, String parentNodeId) throws IOException {
        FileEntity parentFileEntity = fileDao.selectById(parentNodeId);
        if(parentFileEntity==null){
            throw new IOException("指定父节点不存在");
        }
        FileResult result = FileuploadUtil.newFolder(folderName,parentFileEntity.getFilePath());
        if(result!=null){
          // 数据入库
            FileEntity fileEntity = new FileEntity();
            fileEntity.setCreateTime(new Date());
            fileEntity.setFileName(folderName);
            fileEntity.setFilePath(result.getServerPath());
            fileEntity.setFileStatus(Constants.FILE_STATUS_OK.getCode());
            fileEntity.setFileType(Constants.FILE_TYPE_DIRECTORY.getCode());
            fileEntity.setParentId(parentNodeId);
            fileDao.insert(fileEntity);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Boolean rename(String fileId, String fileName) throws IOException {
        FileEntity fileEntity = fileDao.selectById(fileId);
        if(fileEntity==null){
            throw new IOException("文件不存在");
        }
        Boolean result = FileuploadUtil.reName(fileEntity.getFilePath(),fileEntity.getFileName(),fileName);
        if(result){
            // 数据入库
            fileEntity.setFileName(fileName);
            fileDao.insert(fileEntity);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Boolean fileDel(List<String> fileIds) throws IOException {
        int failTime = 0;
        for(String fileId: fileIds){
            FileEntity fileEntity = fileDao.selectById(fileId);
            String fullPath = fileEntity.getFilePath()+ File.separator+fileEntity.getFileName();
            Boolean result = FileuploadUtil.fileDel(fullPath);
            if(result){
                fileDao.deleteById(fileEntity.getId());
            }else{
                failTime++;
            }
        }
        if(failTime>0){
            throw new IOException("有"+failTime+"个文件删除失败");
        }
        return true;
    }

    @Override
    public IPage<FileEntity> filePage(FileSearchReq fileSearchReq) {
        QueryWrapper<FileEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("file_name",fileSearchReq.getFileName());
        IPage<FileEntity> page = new Page<>(fileSearchReq.getCurrentPage(),fileSearchReq.getPageSize());
        IPage<FileEntity> fileEntityIPage = fileDao.selectPage(page, queryWrapper);
        return fileEntityIPage;
    }

    @Override
    public void downLoad(String fileId, HttpServletResponse response) {
        FileEntity fileEntity = fileDao.selectById(fileId);
        File downFile = new File(fileEntity.getFilePath()+File.separator+fileEntity.getFileName());
        FileuploadUtil.download(response,downFile,false);
    }

    /**
     * 方法描述：判断extension中是否存在extName
     * 创建时间：2018-10-20 20:46:18
     *
     * @param extension 使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip
     * @param extName   文件的后缀名
     * @author "lixingwu"
     */
    private static void isContains(String extension, String extName) {
        if (StringUtils.isNotEmpty(extension)) {
            // 切割文件扩展名
            String[] exts = StringUtils.split(extension, ",");
            if (ArrayUtils.isNotEmpty(exts)) {
                assert exts != null;
                List<String> extList = Arrays.asList(exts);
                //判断
                if (!extList.contains(extName)) {
                    throw new RuntimeException("上传文件的类型只能是：" + extension);
                }
            } else {
                // 判断文件的后缀名是否为extension
                if (!extension.equalsIgnoreCase(extName)) {
                    throw new RuntimeException("上传文件的类型只能是：" + extension);
                }
            }
        }
    }
}
