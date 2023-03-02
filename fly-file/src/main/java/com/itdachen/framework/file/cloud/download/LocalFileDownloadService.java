package com.itdachen.framework.file.cloud.download;

import com.itdachen.framework.file.cloud.DownloadService;
import com.itdachen.framework.file.properties.LocalCloudStorageProperties;
import com.itdachen.framework.file.utils.MapPathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * Description: 本地服务器文件下载
 * Created by 王大宸 on 2022-08-16 9:44
 * Created with IntelliJ IDEA.
 */
public class LocalFileDownloadService extends DownloadService {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileDownloadService.class);

    public LocalFileDownloadService(LocalCloudStorageProperties properties) {
        this.properties = properties;
    }

    @Override
    public void download(HttpServletResponse response, String uri, String filename) {
        try {
            // 将文件路径转换成本地文件路径
            uri = uri.replace(properties.getLocalHttp(), properties.getDiskFolder());

            uri = uri.replaceAll("//", "/");
            String mapPath = MapPathUtils.mapPath(properties.getMapPath()) + MapPathUtils.mapPath(properties.getMapPath());
            uri = uri.replaceAll(mapPath.replaceAll("//", "/"), "/upload");

            // if (StringUtils.isEmpty(filename)){
            filename = findFileName(uri, filename);
            //  }
            downloadFile(response, uri, filename);

        } catch (Exception e) {
            logger.error("文件下载异常", e);
        }
    }
}
