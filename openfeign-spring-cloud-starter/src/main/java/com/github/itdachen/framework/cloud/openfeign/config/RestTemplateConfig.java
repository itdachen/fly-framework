package com.github.itdachen.framework.cloud.openfeign.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;


/**
 * Description: 高性能 RestTemplate 封装
 * Created by 剑鸣秋朔 on 2023-06-12 19:26
 * Created with IntelliJ IDEA.
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory) {
        return new RestTemplate(requestFactory);
    }

    /***
     * RestTemplate 默认是以 SimpleClientHttpRequestFactory 实现
     * * 底层使⽤J2SE提供的⽅式，既java.net包提供的⽅式，创建底层的Http请求连接
     * * 主要createRequest ⽅法（ 断点调试），每次都会创建 ⼀个新的连接，
     * * 每次都创建连接会造成极⼤的资源浪费，⽽且若连接不能及时释放，会因为⽆法建⽴新的连接导致后⾯的请求阻塞
     * <p>
     * 修改成 HttpComponentsClientHttpRequestFactory 实现
     * * 底层使⽤HttpClient访问远程的Http服务
     * 解决问题:
     * 1、客户端每次请求都要和服务端建⽴新的连接，即三次握⼿将会⾮常耗时
     * 2、通过http连接池可以减少连接建⽴与释放的时间，提升http请求的性能
     * 3、Spring的 restTemplate 是对 httpclient 进⾏了封装, ⽽ httpclient 是⽀持池化机制
     *
     * @author 剑鸣秋朔
     * @date 2023/6/12 19:26
     * @return org.springframework.http.client.ClientHttpRequestFactory
     */
    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
      //  return new HttpComponentsClientHttpRequestFactory(httpClient());
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);


        //设置连接池最大是500个连接
        connectionManager.setMaxTotal(500);
        //MaxPerRoute 是对 maxtotal 的细分，每个主机的并发最大是300，route是指域名
        connectionManager.setDefaultMaxPerRoute(300);

        /**
         * 只请求 github.com, 最大并发300
         *
         * 请求 github.com, 最大并发300
         * 请求 1024.com, 最大并发200
         *
         * //MaxtTotal=400 DefaultMaxPerRoute=200
         * //只连接到http://github.com 时，到这个主机的并发最多只有200；而不是400；
         * //而连接到http://github.com 和 http://open1024.com时，到每个主机的并发最多只有200；
         * // 即加起来是400（但不能超过400）；所以起作用的设置是DefaultMaxPerRoute。
         *
         */

        RequestConfig requestConfig = RequestConfig.custom()
                // 返回数据的超时时间 20 秒
              //  .setSocketTimeout(20000)
                // 连接上服务器的超时时间 10 秒
                .setConnectTimeout(10000, TimeUnit.SECONDS)
                // 从连接池中获取连接的超时时间
                .setConnectionRequestTimeout(1000, TimeUnit.SECONDS)
                .build();


        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();

        return closeableHttpClient;
    }

}
