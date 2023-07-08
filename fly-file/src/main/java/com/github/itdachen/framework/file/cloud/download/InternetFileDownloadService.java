package com.github.itdachen.framework.file.cloud.download;

import com.github.itdachen.framework.autoconfigure.properties.oss.FlyOssAutoconfigureProperties;
import com.github.itdachen.framework.file.cloud.DownloadService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description: 网址文件下载(其他服务器)
 * Created by 王大宸 on 2022-08-16 9:59
 * Created with IntelliJ IDEA.
 */
public class InternetFileDownloadService extends DownloadService {
    private static final Logger logger = LoggerFactory.getLogger(InternetFileDownloadService.class);

    public InternetFileDownloadService(FlyOssAutoconfigureProperties properties) {
        this.properties = properties;
    }


    @Override
    public void download(HttpServletResponse response,
                         String uri,
                         String filename) {
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);

            // 通过流,读取文件数据
            InputStream is = conn.getInputStream();
            // 得到文件二进制数据, 以二进制封装得到数据
            byte[] data = readInputStream(is);

            // 创建下载临时文件夹
            String uploadPath = properties.getLocal().getDiskFolder();
            if (StringUtils.isEmpty(uploadPath)) {
                uploadPath = "/home/download";
            } else {
                uploadPath += "/download";
            }
            uploadPath = uploadPath.replaceAll("//", "/");
            File fileDir = new File(uploadPath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            filename = findFileName(uri, filename);

            // 创建一个文件对象保存文件,默认保存当前工程根目录
            String filePath = uploadPath + filename;
            File file = new File(filePath);
            // 创建输出流
            FileOutputStream outputStream = new FileOutputStream(file);
            // 写入数据
            outputStream.write(data);
            // 关闭输出流, 释放资源
            outputStream.close();

            downloadFile(response, filePath, filename);

            // 删除临时文件(下载完成就删除)
            file.delete();
            // 删除临时文件(下载完成不直接删除, 项目关闭时删除)
            // file.deleteOnExit();

        } catch (Exception e) {
            logger.error("网络文件下载失败", e);
        }
    }


    private static byte[] readInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 创建一个 Buffer 字符串
        byte[] buffer = new byte[6024];
        // 每次读取的字符串长度,如果为 -1,代表全部读取完毕
        int len;
        while ((len = is.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        is.close();
        // 把 outputStream 里的数据写入到内存
        return outputStream.toByteArray();
    }

}
