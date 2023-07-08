package com.github.itdachen.framework.file.cloud.download;

import com.github.itdachen.framework.autoconfigure.properties.oss.FlyOssAutoconfigureProperties;
import com.github.itdachen.framework.file.cloud.DownloadService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 本地服务器文件下载
 * Created by 王大宸 on 2022-08-16 9:44
 * Created with IntelliJ IDEA.
 */
public class LocalFileDownloadService extends DownloadService {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileDownloadService.class);

    public LocalFileDownloadService(FlyOssAutoconfigureProperties properties) {
        this.properties = properties;
    }

    @Override
    public void download(HttpServletResponse response, String uri, String filename) {
        try {
            // 将文件路径转换成本地文件路径
            String mapPath;
            if (properties.getLocal().getLocalHttp().endsWith("/")) {
                mapPath = properties.getLocal().getLocalHttp() + properties.getLocal().getMapPath() + "/";
            } else {
                mapPath = properties.getLocal().getLocalHttp() + "/" + properties.getLocal().getMapPath() + "/";
            }

            // 将文件路径转换成本地文件路径
            uri = uri.replace(mapPath, properties.getLocal().getDiskFolder());

            // if (StringUtils.isEmpty(filename)){
            filename = findFileName(uri, filename);
            //  }
            downloadFile(response, uri, filename);

        } catch (Exception e) {
            logger.error("文件下载异常", e);
        }
    }
}
