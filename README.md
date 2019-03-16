# 一时打卡 后端部分

# 框架

Spring Boot 2.1.2


 - `bean` 实体类
 - `repository` 数据库操作
 - `service` 服务层
 - `controller` 对外发布接口

# api文档

文档基于swagger生成

# 部署

## 一期

### 方案

通过 `maven` 打包为 jar 包形式，基于 `screen` 运行在后台，基于 `Nginx` 进行反向代理。 

### 部署方式

1. `cd lc-group-backend`
2. 打包：`mvn clean package` ![](https://ws2.sinaimg.cn/large/006tKfTcly1g14mfmb1d4j30zp0qhtf9.jpg)
3. 将 jar 包通过 sftp 等方式上传到服务器（lc.hellogod.cn） `/root/jar/ ` 目录下
4. `ssh root@lc.hellogod.cn` 密码我会发在 lc-group-dev 群里
5.  进入 lc-backend 会话：`screen -d -r lc-backend` ，执行 `Ctrl + C` 停止之前的进程，执行 `java -jar XXX.jar` （将XXX.jar 替换为你的最新 jar 包） 




# License

[GNU General Public License v3.0
](https://github.com/lc-group-dev/lc-group-backend/blob/master/LICENSE)

