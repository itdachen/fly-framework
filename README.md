### 📚 简介

个人项目开发框架独立项目, 后续开发都基于本项目, 避免重复造轮子

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

| 模块               | 介绍                        |
|------------------|---------------------------|
| fly-dependencies | 项目依赖全局管理 pom              |
| fly-context      | 当前上下文模块                   |
| fly-core         | 项目核心模块                    |
| fly-hardware     | 系统参数调用封装（CUP,内存,JVM信息等）   |
| fly-datasource   | 数据库加密模块                   |
| fly-file         | 文件上传模块                    |
| fly-jwt          | JSON Web Token (JWT)封装实现  |
| fly-webmvc       | 通用 MVC 封装模块               |
| fly-runner       | SpringBoot 启动 runner 日志模块 |
| fly-spring       | 关于 Spring 部分组件工具封装模块      |
| fly-tools        | 工具类模块                     |
| fly-rbac         | RBAC 鉴权模块                 |
| fly-assets       | 前端资源模块                    |
| fly-log          | 基于 AOP 日志记录模块             |
| fly-security     | SpringSecurity 安全认证模块     |
| fly-body-advice  | 统一返回数据格式模块                |
| fly-weixin       | 微信开发模块                    |


### 💒 代码仓库
* [码云](https://gitee.com/itdachen/fly-framework)
* [Github](https://github.com/itdachen/fly-framework)


### 🚧 安装方式

进入 fly-dependencies 目录

```lua
mvn clean
mvn install
```

退回根目录(fly-framework)

```lua
mvn clean
mvn install
```


