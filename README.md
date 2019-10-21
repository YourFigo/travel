# travel
* **黑马旅游网的例子**  
- __本人已成功完成，并成功运行__
## `踩坑记录`
1. _从中央仓库下载指定的包_  
   mvn dependency:get -DremoteRepositories=url -DgroupId=groupId -DartifactId=artifactId -Dversion=version

2. _注解中不加/引发的错误 @WebServlet("regeistUserServlet")_  
    Failed to execute goal org.apache.tomcat.maven:tomcat7-maven-plugin:2.1:run (default-cli)

3. _不加 ?useUnicode=true&characterEncoding=utf-8 汉字写入mysql会显示 ?_  
    url=jdbc:mysql:///travel?useUnicode=true&characterEncoding=utf-8

4. _运行前先打开redis的server_