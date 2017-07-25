## 说明
1、创建DispatcherServlet.java
2、编译DispatcherServlet.java文件
   ```
    #:javac -classpath {CATALINA_HOME}/lib/servlet-api.jar -sourcepath src -d
      WEB_INF/classes src/mypack/DispatcherServlet.java
   ```
3、web-app打包成war文件
  ```
  #:jar cvf helloapp.war helloapp/*   //压缩命令
  #:jar xvf helloapp.war              //解压命令
  ```
