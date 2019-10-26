package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 主页中的导航条，分类信息展示
 * 用户相关的servlet，继承自BaseServlet
 * BaseServlet中的service方法可以将请求路径和具体方法名对应起来
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 查询所有
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询所有
        List<Category> cs = categoryService.findAll();
        //2.序列化json返回
        writeValue(cs, response);
    }

    /**
     * 根据 cid 查询 分类信息
     * 其实没必要怎样写，因为Route中可以带Category信息
     * @param request
     * @param response
     * @throws IOException
     */
    @Deprecated
    public void findByCid(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 从请求中获取 cid
        String cid = request.getParameter("cid");

        // 用于存放 查询出 某个分类
        Category category = categoryService.findByCid(cid);
        writeValue(category, response);
    }

}
