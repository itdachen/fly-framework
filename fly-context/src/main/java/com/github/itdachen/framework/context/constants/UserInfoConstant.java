package com.github.itdachen.framework.context.constants;

/**
 * Description: 全局用户信息存储 key
 * Created by 王大宸 on 2022-06-28 15:37
 * Created with IntelliJ IDEA.
 */
public class UserInfoConstant {

    /**
     * 用户id
     */
    public static final String ID = "id";

    /**
     * 登录账号
     */
    public static final String ACCOUNT = "username";

    /**
     * 用户id
     */
    public static final String USER_ID = "userId";

    /**
     * 租户id
     */
    public static final String TENANT_ID = "tenantId";

    /**
     * 平台ID
     */
    public static final String PLAT_ID = "platId";

    /**
     * 平台ID
     */
    public static final String PLAT_NAME = "platName";

    /**
     * 登录应用ID
     */
    public static final String APP_ID = "appId";

    /**
     * 登录应用名称
     */
    public static final String APP_NAME = "appName";

    /**
     * 登录应用版本号
     */
    public static final String APP_VERSION = "appVersion";

    /**
     * 登录应用上下文
     */
    public static final String APP_CONTEXT_PATH = "appContextPath";

    /**
     * 登录方式
     */
    public static final String SIGN_METHOD = "SIGN_METHOD";

    /**
     * 用户姓名
     */
    public static final String NICK_NAME = "nickName";

    /**
     * 头像
     */
    public static final String AVATAR = "avatar";

    /**
     * 电子邮箱
     */
    public static final String E_MAIL = "email";

    /**
     * 性别
     */
    public static final String SEX = "sex";

    /**
     * 登录账号类型
     */
    public static final String USER_TYPE = "userType";

    /**
     * 身份ID
     */
    public static final String ROLE_ID = "roleId";

    /**
     * 身份名称
     */
    public static final String ROLE_NAME = "roleNmae";

    /**
     * 主身份标识: Y-主身份;N-非主身份标识
     */
    public static final String ROLE_FLAG = "roleFlag";

    /**
     * 电话号码
     */
    public static final String TELEPHONE = "telephone";

    /**
     * 部门id
     */
    public static final String DEPT_ID = "deptId";

    /**
     * 部门名称
     */
    public static final String DEPT_TITLE = "deptTitle";

    /**
     * 上级部门id
     */
    public static final String PARENT_DEPT_ID = "parentDeptId";

    /**
     * 部门级别
     */
    public static final String DEPT_LEVEL = "deptLevel";


    /**
     * 身份所属省份代码, 例如: 贵州-52
     */
    public static final String PROV_ID = "provId";

    /**
     * 身份所属市州代码, 例如: 贵阳-5201
     */
    public static final String CITY_ID = "cityId";

    /**
     * 身份所属区县代码, 例如: 贵阳南明-520101
     */
    public static final String COUNTY_ID = "countyId";

    /**
     * 登录主机IP
     */
    public static final String HOST_IP = "hostIp";

    /**
     * 登录主机地址代码, 例如: 贵州-52
     */
    public static final String HOST_PROV = "hostProv";

    /**
     * 录主机地址代码, 例如: 贵阳-5201
     */
    public static final String HOST_CITY = "hostCity";


    /**
     * 登录主机IP
     */
    public static final String HOST_ADDR = "hostAddr";

    /**
     * 代理信息
     */
    public static final String USER_AGENT = "userAgent";

    /**
     * 过期时间(密码过期时间, 有些系统需要定期更新账号的密码)
     */
    public static final String EXP_TIME = "expTime";

    /**
     * 其他
     */
    public static final String OTHER = "other";

    /**
     * 用户 token
     */
    public static final String TOKEN = "token";

    /**
     * 用户 token
     */
    public static final String TOKEN_ID = "tokenId";


    /**
     * 操作日志id
     */
    public static final String LOG_ID = "logId";

    /**
     * 过期时间
     */
    public static final String EXPIRES_IN = "expire_in";

    /**
     * token 请求头
     */
    public final static String HEADER_AUTHORIZATION = "Authorization";

    /**
     * token 类型
     */
    public static final String TOKEN_TYPE = "Bearer ";

    /**
     * 内部调用
     */
    public static final String NAL_FEIGN = "Nal-Feign";

    /**
     * 内部调用
     */
    public static final String NAL_FEIGN_VALUE = "nalFeign";

    /**
     * 校验票据
     */
    public static final String VERIFIED_TICKET = "Verified-Ticket";

    /**
     * 校验票据
     */
    public static final String VERIFIED_TICKET_VALUE = "verifiedTicket";

}
