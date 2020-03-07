# 代码生成器

#### 项目介绍

初始化项目，迭代开发，根据表进行代码生成增删改查功能，方便，快捷进行功能开发。


#### 技术

1. jdk8
2. gradle
3. velocity模块工具

#### 使用教程

1. 将demo.properties的配置文件修改为generator.properties
2. 修改配置文件中的内容
   - generator.jdbc.driver 数据库驱动
   - generator.jdbc.url 数据库连接
   - generator.jdbc.username 用户名
   - generator.jdbc.password 密码
   - databse 数据库名字
   - table_prefix 表明前缀
   - module 项目模块名
   - outPath 输出路径
   - isBuildGradle 是否构建gradle文件
   - isNewFile  是否覆盖源文件
3. 启动com.generator.Generator.class 中main方法即可


#### 参与贡献

1. guozhenhua


#### 博客网

1. 华乐博客 [alfredhua.com](https://alfredhua.com)
