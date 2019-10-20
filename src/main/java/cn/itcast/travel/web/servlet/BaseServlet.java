package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 抽取出来的一个基础servlet，可供子类继承，根据不同的访问路径，访问不同的方法
 */
public class BaseServlet extends HttpServlet {

    /**
     * 完成请求方法的分发，有了这个，具体功能的servlet就可以删掉了，比如 ActiveUserServlet、ExitServlet等，
     * 只保留UserServlet等
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径
        String uri = req.getRequestURI(); //   /travel/user/login
        System.out.println("请求uri:"+ uri);//  /travel/user/login
        //2.获取方法名称  login
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("方法名称："+ methodName);
        //3.获取方法对象Method
        //哪个子类继承BaseServlet，那么this就代表该子类
        //System.out.println(this); //UserServlet的对象cn.itcast.travel.web.servlet.UserServlet@4903d97e
        try {
            //获取方法
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //4.执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }



}
