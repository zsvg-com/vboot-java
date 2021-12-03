<div align="center"><h1 align="center">vboot-net</h1></div>
<div align="center"><h3 align="center">一个开箱即用的快速开发平台.JAVA版</h3></div>

### 🍟 概述

* 基于JAVA实现的快速开发平台。模块化插件式开发，前后端分离，开箱即用。
* 后台基于SpringBoot框架，数据库访问使用Jpa，自动生成数据库表结构。
* 前端基于Vben-Admin vxe-table框架。
* 核心模块包括：部门、用户、岗位、群组、角色、菜单、字典、日志、文件管理、定时任务等功能。
* QQ交流群：[795417789] 暂时就几个人哦，哈哈
* .NET6实现的同功能项目地址[https://gitee.com/zsvg/vboot-net](https://gitee.com/zsvg/vboot-net) 两个项目会同步开发
```
如果对您有帮助，点击右上角⭐Star⭐关注 ，感谢支持开源！
```

### 🎁 前后端一体化

将后台提供的Swagger接口直接生成对应前端的API文件，前端再也不需要手撸一个个的对应后后的API定义了。后台接口更新后，只需要重新生成一遍覆盖即可。

### 🥞 更新日志

更新日志 [点击查看](https://gitee.com/zsvg/vboot-java/CHANGELOG.md)
          
### 🍄 快速启动
* 准备工作：1.配置一个名为vboot-java的mysql数据库，默认账号root,密码123456（可改） 2.开启redis 可不设置密码
* 启动后台：打开项目，找到application启动类启动运行
* 启动前端方式1：前端vbenAdmin已将Build后的代码放在JAVA项目里，会随着后台启动而启动，可直接访问 `http://localhost:5000/vben.html` 预览
* 启动前端方式2：下载配套的前端UI[https://gitee.com/zsvg/vboot-vben](https://gitee.com/zsvg/vboot-vben)使用yarn构建，访问 `http://localhost:3100` 预览

### 🏀 分层说明
```
├─vboot-application             ->业务应用层，在此写您具体业务代码
├─vboot-core                    ->框架核心层
├─vboot-extend-bi               ->BI（报表与数据抽取），近期在开发中，以单独一个模块方式维护
├─vboot-extend-wf               ->WF（工作流），近期在开发中，以单独一个模块方式维护
注：源码直接开发建议自己的业务代码直接写在【vboot-application】层里面，可随框架升级减少冲突。
```

### 📖 帮助文档

👉后台文档：
* SpringBoot后台框架文档 [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)

👉前端文档：
* VbenAdmin前端业务文档 [https://vvbin.cn/next/](https://vvbin.cn/next/)


### 🍖 详细功能

1. 主控面板、控制台页面，可进行工作台，分析页，统计等功能的展示。
2. 部门管理、部门维护，支持多层级结构的树形结构。
3. 用户管理、用户维护，可设置用户部门，岗位，群组，职务，角色，数据权限等。
4. 岗位管理、岗位维护，岗位可作为用户的一个标签，岗位也可与权限等其他功能挂钩。
5. 群组管理、群组维护，群组可设置部门，用户，岗位，用于更广泛的权限设置。
6. 菜单管理、菜单目录，菜单，和按钮的维护是权限控制的基本单位。
7. 角色管理、角色绑定菜单后，可限制相关角色的人员登录系统的功能范围。
8. 字典管理、系统内各种枚举类型的维护。
9. 访问日志、用户的登录和退出日志的查看和管理。
10. 操作日志、用户的操作业务的日志的查看和管理。
11. 定时任务、定时任务的维护，通过cron表达式控制任务的执行频率。

### ⚡ 近期计划

- [ ] 文档编写
- [ ] 在线用户
- [ ] 文件存储 minio
- [ ] 集成工作流


### 🍻 贡献代码

`vboot-java` 遵循 `MIT` 开源协议，欢迎大家提交 `PR` 或 `Issue`。


### 💐 特别鸣谢
- 👉 eladmin：[https://gitee.com/elunez/eladmin](https://gitee.com/elunez/eladmin)
- 👉 ruoyi：[https://gitee.com/y_project/RuoYi-Vue](https://gitee.com/y_project/RuoYi-Vue)
- 👉 vben-admin：[https://vvbin.cn/doc-next/](https://vvbin.cn/doc-next/)
- 👉 vxe-table：[https://gitee.com/xuliangzhan_admin/vxe-table](https://gitee.com/xuliangzhan_admin/vxe-table)

如果对您有帮助，您可以点右上角 💘Star💘支持一下，这样我们才有持续下去的动力，谢谢！！！