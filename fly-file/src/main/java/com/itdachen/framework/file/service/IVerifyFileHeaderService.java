package com.itdachen.framework.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 校验文件头
 * Created by 王大宸 on 2023/02/17 9:05
 * Created with IntelliJ IDEA.
 */
public interface IVerifyFileHeaderService {

    void verifyFileHeader(MultipartFile file) throws Exception;

}
