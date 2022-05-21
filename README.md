<div align="center"><h1 align="center">vboot-java</h1></div>
<div align="center"><h3 align="center">一个开箱即用的快速开发平台JAVA版</h3></div>

# 🍿 相关地址
* 体验地址1：[http://zsvg.gitee.io/vue](http://zsvg.gitee.io/vue) （基于Element-Plus）
* 体验地址2：[http://zsvg.gitee.io/vben](http://zsvg.gitee.io/vben) （基于Ant-Design-Vue）
* 文档地址 ：[http://zsvg.gitee.io/vnds](http://zsvg.gitee.io/vnds) （JAVA的暂无）
* QQ交流群：[795417789](https://jq.qq.com/?_wv=1027&k=yoKKIlIG) （欢迎大家一起交流）

### 🍟 概述
* 基于JAVA实现的快速开发平台。模块化插件式开发，前后端分离，开箱即用。
* 后台基于SpringBoot框架，数据库访问同时集成了Jpa与MyBatis-Plus，即可自动生成数据库表结构，又可灵活编写sql。
* 前端基于vue-next-admin/vben框架，引入了bpmn.js工作流、VForm可视化表单。
* 默认前端（Element-Plus）项目地址：[https://gitee.com/zsvg/vboot-vue](https://gitee.com/zsvg/vboot-vue)
* Vben前端（Ant-Design-Vue）项目地址：[https://gitee.com/zsvg/vboot-vben](https://gitee.com/zsvg/vboot-vben)
* .NET6实现的同功能项目地址[https://gitee.com/zsvg/vboot-net](https://gitee.com/zsvg/vboot-net) 两个项目会同步开发

### 🍄 快速启动
* 准备工作：1.配置一个名为vboot-java的mysql数据库，默认账号root,密码123456（可改） 2.开启redis 可不设置密码
* 启动后台：打开项目，找到application启动类启动运行
* 启动前端方式1：前端已将Build后的代码放在JAVA项目里，会随着后台启动而启动，可直接访问 `http://localhost:5000/vue.html` 预览
* 启动前端方式2：下载配套的前端UI[https://gitee.com/zsvg/vboot-vue](https://gitee.com/zsvg/vboot-vue)使用npm构建，访问 `http://localhost:8888` 预览

### 🏀 分层说明
```
├─vboot-application             ->业务应用层，在此写您具体业务代码
├─vboot-core                    ->框架核心层
├─vboot-extend-bi               ->BI（报表与数据抽取），以单独一个模块方式维护
注：建议将自己的业务代码写在【vboot-application】层里面，可随框架升级减少冲突。
```

### 🍖 详细功能
1. 主控面板：控制台页面，可进行工作台，分析页，统计等功能的展示。
2. 部门管理：部门维护，支持多层级结构的树形结构。
3. 用户管理：用户维护，可设置用户部门，岗位，群组，职务，角色，数据权限等。
4. 岗位管理：岗位维护，岗位可作为用户的一个标签，岗位也可与权限等其他功能挂钩。
5. 群组管理：群组维护，群组可设置部门，用户，岗位，用于更广泛的权限设置。
6. 菜单管理：菜单目录，菜单，和按钮的维护是权限控制的基本单位。
7. 角色管理：角色绑定菜单后，可限制相关角色的人员登录系统的功能范围。
8. 字典管理：系统内各种枚举类型的维护。
9. 访问日志：用户的登录和退出日志的查看和管理。
10. 操作日志：用户的操作业务的日志的查看和管理。
11. 定时任务：定时任务的维护，通过cron表达式控制任务的执行频率。
12. 流程引擎：流程图展示，自解析bpmn.js，支持驳回、转办、废弃，跳转等功能
13. 消息机制：待办待阅功能，联通钉钉与企业微信接口

### ⚡ 近期计划
- [ ] 完善文档
- [ ] 完善工作流
- [ ] 代码生成器

### 💐 特别鸣谢
- 👉 eladmin：[https://gitee.com/elunez/eladmin](https://gitee.com/elunez/eladmin)
- 👉 ruoyi：[https://gitee.com/y_project/RuoYi-Vue](https://gitee.com/y_project/RuoYi-Vue)
- 👉 Vben-Admin：[https://vvbin.cn/doc-next](https://vvbin.cn/doc-next)
- 👉 vue-next-admin：[https://gitee.com/lyt-top/vue-next-admin](https://gitee.com/lyt-top/vue-next-admin)
- 👉 vxe-table：[https://gitee.com/xuliangzhan_admin/vxe-table](https://gitee.com/xuliangzhan_admin/vxe-table)
- 👉 VForm：[https://www.vform666.com](https://www.vform666.com/)

```
如果对您有帮助，点击右上角⭐Star⭐关注 ，感谢支持开源！
```