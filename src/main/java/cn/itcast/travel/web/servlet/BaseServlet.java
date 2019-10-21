package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 抽取出来的一个基础servlet，可供子类继承，根据不同的访问路径，访问不同的方法
 */
public class BaseServlet extends HttpServlet {

    /**
     * 这个方法可以将请求的路径和方法名对应起来
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

    /**
     * 直接将传入的对象序列化为json，并且写回客户端
     * @param obj
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        //将info对象序列化为json，使用jackson
        ObjectMapper mapper = new ObjectMapper();
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 将传入的对象序列化为json，返回
     * @param obj
     * @return
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    /**
     * 校验验证码
     * @param request
     * @param response
     * @throws IOException
     */
    public ResultInfo checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        // 服务端生成的验证码
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        ResultInfo info = new ResultInfo();
        //比较 不区分大小写
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");

            //将数据写回客户端
            writeValue(info,response);
        }else {
            info.setFlag(true);
        }
        return info;
    }

}
