package com.github.itdachen.framework.file;

import com.github.itdachen.framework.file.entity.FileInfo;
import com.github.itdachen.framework.file.factory.FileFactory;
import com.github.itdachen.framework.file.utils.FileMd5HexUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Description: 文件上传/下载对外接口
 * Created by 王大宸 on 2023/02/13 9:49
 * Created with IntelliJ IDEA.
 */
@Component
public class FileHelper {

    public final FileFactory factory;

    public FileHelper(FileFactory factory) {
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
        return factory.build().upload(file);
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
    public String md5Hex(MultipartFile file) throws IOException {
        return FileMd5HexUtils.md5Hex(file);
    }


}
