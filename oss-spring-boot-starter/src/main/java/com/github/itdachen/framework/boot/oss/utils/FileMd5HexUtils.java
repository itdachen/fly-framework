package com.github.itdachen.framework.boot.oss.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description: 获取文件 MD5
 * Created by 王大宸 on 2023/02/13 9:35
 * Created with IntelliJ IDEA.
 */
public class FileMd5HexUtils {

    public static String md5Hex(MultipartFile file) throws IOException {
        return md5Hex(file.getInputStream());
    }

    public static String md5Hex(InputStream inputStream) throws IOException {
        return DigestUtils.md5Hex(inputStream);
    }

}
