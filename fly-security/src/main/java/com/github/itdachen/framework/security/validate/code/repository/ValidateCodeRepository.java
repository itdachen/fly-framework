package com.github.itdachen.framework.security.validate.code.repository;

import com.github.itdachen.framework.security.validate.code.ValidateCode;
import com.github.itdachen.framework.security.validate.code.enums.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description: 验证码存储方式
 * Created by 王大宸 on 2021-11-26 22:41
 * Created with IntelliJ IDEA.
 */
public interface ValidateCodeRepository {

    /***
     * 保存验证码
     *
     * @author 王大宸
     * @date 2021/11/26 22:42
     * @param request
     * @param code
     * @param validateCodeType
     * @return void
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /***
     * 获取验证码
     *
     * @author 王大宸
     * @date 2021/11/26 22:42
     * @param request
     * @param validateCodeType
     * @return com.itdachen.security.core.validate.code.ValidateCode
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /***
     * 移除验证码
     *
     * @author 王大宸
     * @date 2021/11/26 22:42
     * @param request
     * @param codeType
     * @return void
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);


}
