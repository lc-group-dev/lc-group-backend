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

### 部署方式

通过 `maven` 打包为 jar 包形式，基于 `nohup` 运行在后台，基于 `Nginx` 进行反向代理。 

### 部署方式

1. 进入项目 `cd lc-group-backend`
2. 基于maven生成 jar 包：`mvn clean package` 
3. 将 jar 包通过 sftp 等方式上传到服务器（lc.hellogod.cn） `/root/jar/ ` 目录下，默认命名：leetcode-group-1.0-SNAPSHOT.jar
4. 连接服务器： `ssh root@lc.hellogod.cn` ，密码在 lc-group-dev 微信群公告
5. 执行 `./run_lc.sh` 部署 jar 包



# License

[GNU General Public License v3.0
](https://github.com/lc-group-dev/lc-group-backend/blob/master/LICENSE)

