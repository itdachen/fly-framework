package com.github.itdachen.boot.oss;

import com.github.itdachen.boot.oss.entity.FileInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 文件上传/下载对外接口
 * Created by 王大宸 on 2023/02/13 9:49
 * Created with IntelliJ IDEA.
 */
public interface FileHelper {

    /***
     * 文件上传
     *
     * @author 王大宸
     * @date 2023/12/27 21:59
     * @param file file
     * @return com.github.itdachen.boot.oss.entity.FileInfo
     */
    FileInfo upload(MultipartFile file) throws Exception;

    FileInfo upload(MultipartFile file, String diskFolder) throws Exception;

    /***
     * 文件下载
     *
     * @author 王大宸
     * @date 2023/12/27 21:59
     * @param response response
     * @param uri uri
     * @param filename filename
     * @return void
     */
    void download(HttpServletResponse response, String uri, String filename) throws Exception;

    /***
     * 获取文件 MD5
     *
     * @author 王大宸
     * @date 2023/12/27 21:59
     * @param file file
     * @return java.lang.String
     */
    String md5Hex(MultipartFile file) throws Exception;

}
