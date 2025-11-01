package com.github.itdachen.framework.boot.oss.cloud;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Description: 文件下载
 * Created by 王大宸 on 2023/02/10 16:45
 * Created with IntelliJ IDEA.
 */
public abstract class DownloadService {
    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);

    /***
     * 文件下载抽象方法, 交给具体的实现类去实现
     *
     * @author 王大宸
     * @date 2022/8/17 9:54
     * @param response response
     * @param uri  文件请求路径, http 请求
     * @param filename 文件名称,如果没有传入文件名称,默认为文件路径上的名称
     * @return void
     */
    public abstract void download(HttpServletResponse response,
                                  String uri,
                                  String filename);


    /***
     * 下载文件通用方法(从本地服务器下载到浏览器)
     *
     * @author 王大宸
     * @date 2022/8/16 10:44
     * @param response response
     * @param localUri 本地文件地址(服务器上存储位置)
     * @param filename 文件名
     * @return void
     */
    protected void downloadFile(HttpServletResponse response,
                                String localUri,
                                String filename) {
        try {
            File file = new File(localUri);
            if (StringUtils.isEmpty(filename)) {
                filename = file.getName();
            }
            filename = filename.replace("/", "")
                    .replaceAll("%20", "")
                    .replaceAll(" ", "").trim();

            // 以流的形式下载
            InputStream fis = new BufferedInputStream(new FileInputStream(localUri));

            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 重置 response
            response.reset();

            response.setContentType("application/octet-stream; charset=UTF-8");
            String fileName = new String(filename.getBytes("gb2312"), "iso8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            logger.error("本地下载文件失败: ", e);
        }
    }

    /***
     * 如果文件名为空, 则默认使用路径文件名, 如果文件名存在,添加文件后缀
     *
     * @author 王大宸
     * @date 2022/8/16 14:37
     * @param uri 文件http路径
     * @param filename 文件名称
     * @return java.lang.String
     */
    protected String findFileName(String uri, String filename) {
        if (StringUtils.isNotEmpty(filename) && StringUtils.isNotEmpty(uri)) {

//            String fileNameSuffix = filename.substring(filename.lastIndexOf("."));
//            String uriSuffix = uri.substring(filename.lastIndexOf("."));
//            if (fileNameSuffix.equals(uriSuffix)){
//                return filename;
//            }

            /* 为了防止文件后缀名重复, 如果有文件后缀名, 则直接返回, 如果没有文件后缀, 则添加文件后缀名 */
            String item = filename.replace(",", "").replace(".", ",");
            String[] split = item.split(",");
            if (1 < split.length) {
                return filename;
            }
        }
        if (StringUtils.isEmpty(filename)) {
            filename = uri.substring(uri.lastIndexOf("/"));
        } else {
            filename = filename + uri.substring(uri.lastIndexOf("."));
        }
        return filename;
    }

}
