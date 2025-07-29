package com.github.itdachen.boot.oplog.ooxml.poi.imp.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.itdachen.boot.autoconfigure.AppContextHelper;
import com.github.itdachen.boot.autoconfigure.app.AppInfoProperties;
import com.github.itdachen.boot.autoconfigure.app.PlatformInfoProperties;
import com.github.itdachen.boot.oplog.ooxml.poi.entity.OplogPoiModel;
import com.github.itdachen.boot.oplog.ooxml.poi.entity.PoiUploadInfo;
import com.github.itdachen.boot.oplog.ooxml.poi.imp.ImpParamsSettings;
import com.github.itdachen.boot.oplog.ooxml.poi.log.IOOXmlPoiLogClient;
import com.github.itdachen.framework.context.id.IdUtils;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import com.github.itdachen.framework.core.utils.StringUtils;
import com.github.itdachen.framework.tools.ServletUtils;
import com.github.itdachen.framework.tools.ip.AddressUtils;
import com.github.itdachen.framework.tools.useragent.UserAgentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 导入日志
 *
 * @author 王大宸
 * @date 2025-07-28 10:16
 */
public class OOXmlPoiImpLogHandler {
    private static final Logger logger = LoggerFactory.getLogger(OOXmlPoiImpLogHandler.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("ooxml-poi-imp-log-thread-" + ThreadLocalRandom.current().nextInt(1000));
            return thread;
        }
    });


    /***
     * 添加导出日志文件信息
     *
     * @author 王大宸
     * @date 2025/7/22 15:08
     * @param msgId       消息ID
     * @param uploadInfo  文件上传信息
     * @return void
     */
    public static void saveLog(ImpParamsSettings settings,
                               String msgId,
                               PoiUploadInfo uploadInfo) throws JsonProcessingException {

        AppInfoProperties appClientProperties = AppContextHelper.getBean(AppInfoProperties.class);
        PlatformInfoProperties platformClientProperties = AppContextHelper.getBean(PlatformInfoProperties.class);

        final UserInfoDetails userDetails = settings.getUserDetails();

        final String userAgent = settings.getRequest().getHeader("User-Agent");
        final String jsonString = objectMapper.writeValueAsString(settings.getParams());
        final String ipAddress = ServletUtils.getIPAddress(settings.getRequest());

        String funcTitle = settings.getFuncTitle();
        if ("-".equals(funcTitle) || StringUtils.isEmpty(funcTitle)) {
            funcTitle = settings.getTitle();
        }

        OplogPoiModel oplogPoiModel = OplogPoiModel.builder()
                .id(IdUtils.getId())
                .msgId(msgId)
                .appId(appClientProperties.getAppId())
                .appTitle(appClientProperties.getAppName())
                .appVersion(appClientProperties.getVersion())
                .platId(platformClientProperties.getId())
                .platTitle(platformClientProperties.getTitle())

                /* 人员信息 */
                .tenantId(userDetails.getTenantId())
                .tenantTitle(userDetails.getTenantTitle())
                .userId(userDetails.getId())
                .nickName(userDetails.getNickName())
                .roleId(userDetails.getRoleId())
                .roleName(userDetails.getRoleName())

                /* 行政区划 */
                .provId(userDetails.getProvCode())
                .provTitle(userDetails.getProvName())
                .cityId(userDetails.getCityCode())
                .cityTitle(userDetails.getCityName())
                .countyId(userDetails.getCountyCode())
                .countyTitle(userDetails.getCountyName())

                /* 具体所属部门 */
                .deptId(userDetails.getDeptId())
                .deptTitle(userDetails.getDeptTitle())
                .deptLevel(userDetails.getDeptLevel())
                .deptParentId(userDetails.getDeptParentId())

                /* 日志标题 */
                .msgId(msgId)
                .funcId(settings.getFuncId())
                .funcTitle(funcTitle)

                /* 操作主机信息 */
                .hostIp(ipAddress)
                .hostAddress(AddressUtils.getRealAddressByIP(ipAddress))
                .hostOs(UserAgentUtils.getOSInfo(userAgent))
                .hostBrowser(UserAgentUtils.getBrowserName(userAgent))
                .hostUserAgent(userAgent)

                /* 请求信息 */
                .requestId(null == settings.getRequest().getRequestId() ? "-" : settings.getRequest().getRequestId())
                .requestUri(settings.getRequest().getRequestURI())
                .requestMethod(settings.getRequest().getMethod())
                .requestParams(jsonString)

                /* 导出信息 */
                .dataTotal(String.valueOf(uploadInfo.getDataTotal()))
                .fileName(uploadInfo.getFileName())
                .fileUrl(uploadInfo.getFileUri())
                .fileSize(String.valueOf(uploadInfo.getFileSize()))
                .fileTotal(String.valueOf(uploadInfo.getFileTotal()))

                .exInfo("-")
                .remarks("-")
                .executeTime(uploadInfo.getTakeUpTime() + " ms")

                .build();

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AppContextHelper.getBean(IOOXmlPoiLogClient.class).saveOOXmlPoiImpLog(oplogPoiModel);
                } catch (Exception e) {
                    logger.error("数据导出日志入库失败: ", e);
                }
            }
        });

    }


}
