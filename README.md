# “捎一下”桌面应用

帮助学生取快递&外卖的软件平台，可以在上面发布和接收订单以及修改完善个人信息。

### 功能介绍

- 登录：用户名、密码登录。
- 注册：基本信息包括用户名、密码、省/市/区、详细地址、学校、手机号。
- 查看/修改个人信息：修改密码及基本信息。
- 下单：提交订单信息包括物品类型、物品描述、日期时间、地点以及订单有效时间。
- 接单：提交接单信息包括用户id、订单id、日期时间等。
- 查看历史：查看与“我”有关的下单和接单信息。

### 文件说明
- src：项目源文件
  - com.etc.jump
    - Main 主函数，包含首页面加载程式
    - dao
      - CityDao 城市DAO接口
      - MonadsDao 订单DAO接口
      - ShaoMonadsDao 捎单DAO接口
      - UsersDao 用户DAO接口
    - daoimpl DAO接口的实现
    - biz 业务流接口
    - bizimpl 业务流接口实现
    - entity 实例类
    - util
      - FilePath 存储.fxml的相对路径
      - JDBCUtil JDBC操作的自定义封装，包括数据库连接地址、用户名和密码设置
    - ui
      - fxml 页面框架，内含部分初始化样式
        - index 主页面框架
        - indexaccordion 主页面中加载显示订单详细信息的自定义UI组件
        - login 登录页面框架
        - logo 首页框架
        - mymonads 历史记录页面框架
        - order 下单页面框架
        - register 注册页面框架
        - sidebar 通过“汉堡包”控件调整拉出的右边栏组件
        - userinfo 用户信息页面框架
      - contro 所对应的页面控制部分，主要包含基本业务逻辑判断以及交互动画等等
      - css 页面样式，包括颜色背景等等
    
- lib：关联jar包
  - fontawesomefx-8.1 非常漂亮且简便的图标包
  - jfoenix-8.0.1 一款开源Java GUI库，实现Material Design设计风格
  - mysql-connector-java-5.0.5-bin MySQL的Java驱动库
- sql：数据库文件
  - shaohelp 
- img：展示截图

### 本地创建

这是一款很简单的Java桌面应用，想要运行此程序你只需要做到以下几步：

1. Clone此项目到本地
2. 使用（Eclipse/IntelliJ/NetBeans/..）新建一个本地Java项目
3. 配置JDK，至少应该是jdk1.8.0._60以上的版本。你可以通过`java -version`查看你的java版本。
   > 注意'_'后面的数字，此代码最后编译的版本号是'_171'。
4. 将lib文件下的包导入到本地项目中。如果你已经对它们有所了解，想要自行下载，那么你应该注意它们的版本应该对应。
5. 将sql文件下`shaohelp.sql`导入到你的MySQL数据库中，并注意修改`../src/com/etc/jump/util/JDBCUtil.java`中的数据库配置如下：
   ```java
    //连接的数据库地址
    public  static final String PATH="jdbc:mysql://172.0.0.1:3306/shaohelp";
    //用户名
    public  static final String NAME="root";
    //密码
    public static final String PWD="1234";
   ```
6. 编译并运行`../src/com/etc/jump/Main.java`，希望你能够享受她：）。

### 展示截图

**登录**

![登录](https://github.com/JohnBown/shaohelp/blob/master/img/login/login1.png?raw=true)

**注册**

![注册](https://github.com/JohnBown/shaohelp/blob/master/img/register/register4.png?raw=true)

**首页**

![首页](https://github.com/JohnBown/shaohelp/blob/master/img/index/index1.png?raw=true)

**下单**

![下单1](https://github.com/JohnBown/shaohelp/blob/master/img/order/order1.png?raw=true)

![下单2](https://github.com/JohnBown/shaohelp/blob/master/img/order/order2.png?raw=true)

![下单3](https://github.com/JohnBown/shaohelp/blob/master/img/order/order3.png?raw=true)

![下单4](https://github.com/JohnBown/shaohelp/blob/master/img/order/order4.png?raw=true)

**个人信息**

![个人信息](https://github.com/JohnBown/shaohelp/blob/master/img/userinfo/userinfo1.jpg?raw=true)

**历史记录**

![历史记录](https://github.com/JohnBown/shaohelp/blob/master/img/history/history1.png?raw=true)


----------------------

## 相关链接

- [JFoenix](http://www.jfoenix.com/)
- [jfoenixadmin/JFoenix: JFoeniX Material Design Library](https://github.com/jfoenixadmin/JFoenix)
- [jerady/fontawesomefx - Bitbucket](https://bitbucket.org/Jerady/fontawesomefx)
- [“捎一下”项目讨论记录——需求及产品拟定条案](https://github.com/JohnBown/Yuanchao-Filed/issues/18)
- [“捎一下”项目讨论记录——数据库设计](https://github.com/JohnBown/Yuanchao-Filed/issues/19)
- [“捎一下”项目讨论记录——页面原型图](https://github.com/JohnBown/Yuanchao-Filed/issues/20)

> 
> **最后鸣谢：**
> 指导：郑有义老师 |
> 团队成员：黄毅鹏（队长）、丘金英、王兴伟、王星杰 |
> 开源社区及所有热爱分享的 coder |
>
