# `travel`
* **黑马旅游网的例子**  
- __本人已成功完成，并成功运行__ :smile:
---
### `踩坑记录` :sob:
1. _从中央仓库下载指定的包_  
   mvn dependency:get -DremoteRepositories=url -DgroupId=groupId -DartifactId=artifactId -Dversion=version

2. _注解中不加/引发的错误 @WebServlet("regeistUserServlet")_  
    Failed to execute goal org.apache.tomcat.maven:tomcat7-maven-plugin:2.1:run (default-cli)

3. _不加 ?useUnicode=true&characterEncoding=utf-8 汉字写入mysql会显示 ?_  
    url=jdbc:mysql:///travel?useUnicode=true&characterEncoding=utf-8

4. _运行前先打开redis的server_

5. _为了注册后能注册激活，注册时请输入正确邮箱，并且将cn\itcast\travel\util\MailUtils.java中的USER和PASSWORD填写为正确的qq邮箱地址和登录授权码_
---
### `配套讲解` :stuck_out_tongue_winking_eye:
* 知乎文章：https://zhuanlan.zhihu.com/p/88646197
---
### `目录树`
```
├─.idea  
│  ├─artifacts  
│  └─libraries  
├─src  
│  └─main  
│      ├─java  
│      │  └─cn  
│      │      └─itcast  
│      │          └─travel  
│      │              ├─dao  
│      │              │  └─impl  
│      │              ├─domain  
│      │              ├─service  
│      │              │  └─impl  
│      │              ├─util  
│      │              └─web  
│      │                  ├─filter  
│      │                  └─servlet  
│      ├─resources  
│      └─webapp  
│          ├─css  
│          ├─error  
│          ├─fonts  
│          ├─images  
│          ├─img  
│          │  └─product  
│          │      ├─size2  
│          │      ├─size4  
│          │      └─small  
│          ├─js  
│          └─WEB-INF  
└─target  
    ├─apache-tomcat-maven-plugin  
    ├─classes  
    │  └─cn  
    │      └─itcast  
    │          └─travel  
    │              ├─dao  
    │              │  └─impl  
    │              ├─domain  
    │              ├─service  
    │              │  └─impl  
    │              ├─util  
    │              └─web  
    │                  ├─filter  
    │                  └─servlet  
    ├─generated-sources  
    │  └─annotations  
    ├─maven-status  
    │  └─maven-compiler-plugin  
    │      └─compile  
    │          └─default-compile  
    └─tomcat
        ├─conf  
        ├─logs  
        ├─webapps  
        └─work  
            └─Tomcat  
                └─localhost  
                    └─travel  
```