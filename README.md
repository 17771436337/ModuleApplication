# ModuleApplication

#### 介绍
Android 使用java组件化项目，添加各种组件，进行一个项目的完整编程

#### 软件架构
代码模块化的不断添加信息

#### 模块说明
###### 基础类
 ######  1.  common
 基础依赖包，主要是做基础集合处理
 ######  2.  common_arouter
 路由器包，主要是做界面跳转，云路由连接等等，依赖动态代码“com.alibaba:arouter-api”，具体方法请参考代码已经对应的文档
 ######  3.  common_database
 数据库基础包，集合数据内容，以及数据处理等相关操作，目前依赖于“org.greenrobot:greendao:3.2.2”
 ######  4.  common_http
 网络请求数据包
 ######  5.  common_utils
 工具包
 ######  6.  common_view
 自定义视图
 ######  7.  common_mvp
  实现项目对应的mvp框架
 ######  8.  common_basis
 封装Activity，Fragment，adapter等，封装adapter，进行简化处理

 ## 项目包
 ######  1.  app
 空项目，主要是做项目模块化处理
 ######  2.  accountmanagement
 账号管理app，目前实现数据存储，备份，加密，保存，修改侧滑删除，遗留侧滑与单击有时候冲突，并且没办法实现上拉加载下拉刷新了
 ######  3.  ftp
 ftp的相关Android代码，目前只实现ftp服务器的相关demo代码，能够做到局域网通过ftp访问手机文件
 ######  4.  game_2048
 游戏2048
 ######  5.  game_teris
 游戏俄罗斯方块
 ######  6.  home
 首页，目前打算做一个侧边栏，还没有对应的方案，应该会被废弃
 ######  7.  love
 表白app，主要实现一些动画效果，目前有星云球和雪花
 ######  8.  mqtt
 mqtt的基本使用
 ######  9.  user
 用户管理模块，目前没有相关代码，可能废弃
 ###### 10.  web
网页请求访问，目前已集成x5内核，提高访问速度


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技
