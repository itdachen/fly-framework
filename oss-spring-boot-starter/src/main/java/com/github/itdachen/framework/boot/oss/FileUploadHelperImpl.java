package com.github.itdachen.framework.boot.oss;

import com.github.itdachen.framework.boot.oss.factory.FileFactory;
import com.github.itdachen.framework.boot.oss.utils.FileMd5HexUtils;
import com.github.itdachen.framework.context.models.FileInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileInfoHelper
 *
 * @author 王大宸
 * @date 2023-12-27 21:57
 */
public class FileUploadHelperImpl implements FileHelper {

    public final FileFactory factory;

    public FileUploadHelperImpl(FileFactory factory) {
        this.factory = factory;
    }

    /***
     * 文件上传
     *
     * @author 王大宸
     * @date 2023/4/11 21:27
     * @param file file
     * @return com.github.itdachen.framework.file.entity.FileInfo
     */
    public FileInfo upload(MultipartFile file) throws Exception {
        return factory.build().upload(file, null);
    }


    public FileInfo upload(MultipartFile file, String diskFolder) throws Exception {
        return factory.build().upload(file, diskFolder);
    }

    /***
     * 文件下载
     *
     * @author 王大宸
     * @date 2023/2/13 9:52
     * @param response response
     * @param uri      文件地址, 以 http 或 https 开头
     * @param filename 文件名
     * @return void
     */
    public void download(HttpServletResponse response, String uri, String filename) throws Exception {
        factory.build(uri).download(response, uri, filename);
    }

    /***
     * 获取文件MD5
     *
     * @author 王大宸
     * @date 2023/4/15 0:07
     * @param file file
     * @return java.lang.String
     */
    public String md5Hex(MultipartFile file) throws Exception {
        return FileMd5HexUtils.md5Hex(file);
    }

}
