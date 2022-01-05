# idea下运行项目详细步骤

## 1.环境配置

**IntelliJ IDEA Ultimate**  2021（idea旗舰版）：开发工具。学生可以申请免费的旗舰版许可证，也可以使用破解版的旗舰版。请自行百度。

**mysql8**：数据库

**tomcat8**：servlet容器

**maven3.6**：管理项目

**Navicat**： 数据库可视化客户端。可以使用破解版，请自行百度。

## 2.安装环境

### 2.1安装MySQL8

参考教程：[https://www.cnblogs.com/tangyb/p/8971658.html](https://www.cnblogs.com/tangyb/p/8971658.html)

### 2.2下载并配置Tomcat8

参考教程：[https://blog.csdn.net/u014577061/article/details/101059237](https://blog.csdn.net/u014577061/article/details/101059237)

### 2.3下载配置maven

**2.3.1参考配置：** [https://www.cnblogs.com/yu-si/articles/14586626.html](https://www.cnblogs.com/yu-si/articles/14586626.html)

**2.3.2idea配置maven** [https://blog.csdn.net/qq_42057154/article/details/106114515](https://blog.csdn.net/qq_42057154/article/details/106114515)

### 2.4Navicat的安装

参考： [https://www.jb51.net/article/199496.htm](https://www.jb51.net/article/199496.htm)

## 3.运行项目

### 3.1克隆项目到本地

```bash
git clone  https://gitee.com/ljf2402901363/javaWeb-bookManagementSystem.git 
```



### 3.2使用idea导入maven项目



### 3.3创建数据库并导入sql文件

在Navicat中导入项目的SQL文件步骤： 新建一个数据库javaweb-->点击数据库javaweb的表，然后鼠标右键-->运行SQL文件-->找到你下载的sql文件的文件路径---->开始-->刷新表，多刷新几次然后就可以了看到导入的表了。

### 3.4修改数据库连接配置

将该项目源码下载后修改src/main/resources/druid.properties中的数据库连接配置文件。这个应该挺简单的，不是难事。

如果导入失败，可以直接使用本项目中的“项目数据库代码.txt”复制里面的建表sql语句进行建表然后自己插入数据即可。

### 3.5给项目配置一个Tomcat然后启动

### 3.6说明

用户类型在实例类 Account.java里有哦，和数据库中account这个表的Type对应。游客，普通用户，管理员是不同的。
