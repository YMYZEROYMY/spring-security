# spring-security

简单的电影购票系统

功能：
  前端：按类型查找、按关键字查找、登陆注册、登陆后可以购票及查看购票记录
  后端：操作数据库对电影增删查改

运行环境：window7（ubuntu亦可），idea，Maven,Chrome浏览器

技术点：mysql,springBoot,springMVC,hibernate,springSecurity,Ajax,bootStrap,Jquery

配置过程：
  1.导入源程序:
      直接源程序打开；
      
      git导入：
        创建本地git（git init）;
        
        添加远程仓库（git remote add origin https://github.com/YMYZEROYMY/spring-security.git）;
        
        获取程序（git pull origin hot）;
        
    idea中打开，mvn自动加载依赖包
    
  2.导入数据库：
      找到源程序根目录下的数据库备份，名称：spring_security_data.txt;
      
      进入mysql，创建数据库spring_security（create database spring_security;），进入数据库（use spring_security）;
      
      导入数据（source 备份路径/spring_security_data.txt;）;
      
  3.更改源程序中resources的数据库用户名及密码；
  
  4.运行程序。
