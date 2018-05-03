https://start.spring.io/   在这里去创建各服务模块
PRING INITIALIZR是spring boot在构建项目时候非常有效,
虽然说Maven以及Spring boot提供的starter使用起来非常简单,
 但是由于组件和关联部分众多,有这样一个可视化的配置构建管理工具仍然很不错.
  在这篇文章中我们将会在IntelliJ IDEA中利用SPRING INITIALIZR来创建一个Hello World的例子程序
  , 不包括Maven下载的速度的话, 1分钟应该够了.

  使用它，可以很容易地创建基于Maven或Gradle的项目模板，并通过界面上的多选框定义项目所有所需的依赖。
  这个在线工具实际上是一个SpringBoot应用程序，可以在本地自己搭建一个这样的工具。
  这么做有很多优点：可以避免因为无法访问spring.io站点就无法新建SpringBoot项目的尴尬；
  可以限制依赖的数量；可以强制使用Gradle作为项目构建工具；可以设置默认的项目名、默认的包名等等。

这是一个简单的用户微服务就写好了 功能： 通过id查询用户信息