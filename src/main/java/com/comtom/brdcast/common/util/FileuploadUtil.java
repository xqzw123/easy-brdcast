package com.comtom.brdcast.common.util;


import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.comtom.brdcast.model.entity.FileResult;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传工具类（修改了bug 2019-0105）
 *
 * @author lixingwu
 */
public class FileuploadUtil {

    /**
     * 方法描述：根据文件的绝对路径创建一个文件对象.
     * 创建时间：2018-10-19 09:32:34
     *
     * @param filePath 文件的绝对路径
     * @return 返回创建的这个文件对象
     * @author "lixingwu"
     */
    public static File createFile(String filePath) throws IOException {
        // 获取文件的完整目录
        String fileDir = FilenameUtils.getFullPath(filePath);
        System.out.println(fileDir);
        System.out.println(filePath);
        // 判断目录是否存在，不存在就创建一个目录
        File file = new File(fileDir);
        if (!file.isDirectory()) {
            //创建失败返回null
            if (!file.mkdirs()) {
                throw new IOException("文件目录创建失败...");
            }
        }
        // 判断这个文件是否存在，不存在就创建
        file = new File(filePath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("目标文件创建失败...");
            }
        }
        return file;
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


    /**
     * 方法描述：生成文件在的实际的路径
     * 创建时间：2018-10-20 20:46:18
     *
     * @param
     * @author "lixingwu"
     */
    private static String getServerPath(String filePath,String name) {
        // 文件分隔符转化为当前系统的格式
        return filePath + File.separator + name;
    }

    public static FileResult newFolder(String foderName,String filePath) throws IOException {
        String finalPath = getServerPath(filePath,foderName);
        File file = new File(finalPath);
        if (!file.exists()) {
            //不存在则新建文件夹
            if (!file.mkdirs()) {
                throw new IOException("文件目录创建失败...");
            }
        }
        // 拼装返回的数据
        FileResult result = new FileResult();
        result.setFileSize(0L);
        result.setFileName(foderName);
        result.setExtName("");
        result.setServerPath(finalPath);
        return result;
    }

    /**
     * 方法描述：上传文件.
     *
     * @param multipartFile 上传的文件对象，必传
     * @return the file result
     * @throws IOException 异常信息应返回
     * @author "lixingwu"
     */
    public static FileResult saveFile(MultipartFile multipartFile,String filePath) throws IOException {

        // 文件名
        String fileName = multipartFile.getOriginalFilename();
        // 文件后缀名
        String extName = FilenameUtils.getExtension(fileName);

        // 判断文件的后缀名是否符合规则
        isContains("mp3", extName);
        // 文件的实际路径
        String serverPath = getServerPath(filePath,fileName);
        // 创建文件
        File destFile = createFile(serverPath);
        // 保存文件
        multipartFile.transferTo(destFile);

        // 拼装返回的数据
        FileResult result = new FileResult();
        result.setFileSize(multipartFile.getSize());
        result.setFileName(fileName);
        result.setExtName(extName);
        result.setServerPath(serverPath);
        return result;
    }

    /**
     * 文件重命名
     * @param fullPath
     * @param origFileName
     * @param currFileName
     * @return
     */
    public static Boolean reName(String fullPath, String origFileName, String currFileName) {
        File origFile = new File(fullPath+File.separator+origFileName);
        File currentFile = new File(fullPath+File.separator+currFileName);
        return origFile.renameTo(currentFile);
    }

    public static Boolean fileDel(String fullPath) {
        File delFile = new File(fullPath);
        if(delFile.exists()){
            deleteFile(delFile);
        }
        return true;
    }

    private static void deleteFile(File file) {
        //如果是文件，可以直接删除
        if (file.isFile())
            file.delete();
        else {
            for (File file2 : file.listFiles()) {
                deleteFile(file2);
            }
            file.delete();
        }
    }
    public static void main(String[] args) {
        final File file = new File("F:\\tools\\");
        System.out.println(file.isDirectory());
    }

    /**
     * 下载文件
     *
     * @param response
     * @param file
     * @param finishDeleteFile
     *            下载完成之后是否需要删除文件
     */
    public static void download(HttpServletResponse response, File file, boolean finishDeleteFile) {
        FileInputStream fis = null;
        ServletOutputStream out = null;
        try {
            // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("application/octet-stream");
            String downLoadName = new String(file.getName().getBytes("UTF-8"), "iso8859-1");
            // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            response.setHeader("Content-Disposition", "attachment;fileName=" + downLoadName);
            response.setHeader("Content-Length", String.valueOf(file.length()));
            fis = new FileInputStream(file);
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while ((b = fis.read(buffer)) != -1) {
                out.write(buffer, 0, b);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                fis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            if (finishDeleteFile) {
                if ((file != null) && file.exists()) {
                    file.delete();
                }
            }
        }
    }

}
