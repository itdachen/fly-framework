<div align="center">

<br/>
<br/>

  <h1 align="center">
    FLY-FRAMEWORK
  </h1>
  <h4 align="center">
   <a href="https://gitee.com/itdachen/fly-next-platform">fly-next</a> 和 
   <a href="https://gitee.com/itdachen/fly-cloud">fly-cloud</a>  通用依赖, 避免多项目下重复造轮子
  </h4>
</div>



<p align="center">
    <a href="#">
        <img src="https://img.shields.io/badge/JDK-17+-green.svg" alt="Pear Admin Layui Version">
    </a>
    <a href="#">
        <img src="https://img.shields.io/badge/SpringBoot-2.7.10-green.svg" alt="Pear Admin Layui Version">
    </a>
    <a href="#">
        <img src="https://img.shields.io/badge/Spring Cloud-2021.0.5-green.svg" alt="Jquery Version">
    </a>
    <a href="#">
        <img src="https://img.shields.io/badge/Spring Cloud Alibaba-2021.0.4.0-green.svg" alt="Layui Version">
    </a>
</p>

<br>

### 🌱 核心依赖

| 依赖                   | 版本         |
|----------------------|------------|
| Nacos                | 2.1.1      |
| Spring Boot          | 2.6.6      |
| Spring Cloud         | 2021.0.5   |
| Spring Cloud Alibaba | 2021.0.4.0 |
| Mybatis              | 3.5.11     |
| pagehelper           | 5.3.2      |
| fastjson             | 2.0.21     |
| hutool               | 5.8.15     |

### 🛠️ 组件模块

| 模块                               | 介绍                       |
|----------------------------------|--------------------------|
| fly-dependencies                 | 项目依赖全局管理 pom             |
| fly-autoconfigure-processor      | 项目配置模块(所有模块配置文件统一管理)     |
| fly-cloud-gateway-dynamic-routes | Gateway Nacos 动态网关       |
| fly-context                      | 当前上下文模块                  |
| fly-core                         | 项目核心模块                   |
| fly-hardware                     | 系统参数调用封装（CUP,内存,JVM信息等）  |
| fly-datasource                   | 数据库加密模块                  |
| fly-oss                          | 文件上传模块                   |
| fly-jwt                          | JSON Web Token (JWT)封装实现 |
| fly-webmvc                       | 通用 MVC 封装模块              |
| fly-tools                        | 工具类模块                    |
| fly-rbac                         | RBAC 鉴权模块                |
| fly-assets                       | 前端资源模块                   |
| fly-oplog                        | 基于 AOP 日志记录模块            |
| fly-security                     | SpringSecurity 安全认证模块    |
| fly-body-advice                  | 统一返回数据格式模块               |
| fly-weixin                       | 微信开发模块                   |
| fly-rate-limiter                 | 自定义限流模块                  |
| fly-sensitive                    | 数据脱敏模块                   |
| fly-thread-pool                  | 自定义线程池模块                 |
| fly-cloud-jwt-core               | jwt 认证 token 认证核心模块      |
| fly-cloud-jwt-crypto             | jwt 认证 token 加密模块        |
| fly-cloud-jwt-parse              | jwt 认证 token 解析模块        |
| api-sign-boot-starter            | 接口签名模块                   |

### 💒 代码仓库

* [码云](https://gitee.com/itdachen/fly-framework)
* [Github](https://github.com/itdachen/fly-framework)

### 🚧 安装方式

进入 fly-dependencies 目录

```lua
cd fly-dependencies
mvn clean install
```

退回根目录(fly-framework)

```lua
cd ..
mvn clean install
```


