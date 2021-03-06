package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 主页中的导航条，分类信息展示
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 由于这个数据基本不变，为了减少访问数据库的开销，使用redis缓存
     * @return
     */
    @Override
    public List<Category> findAll() {
        //1.从redis中查询
        //1.1获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2可使用sortedset排序查询
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        //这样可以获取到分数(cid)，上面 zrange 只能获取到 cname
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        //2.判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0) {
            System.out.println("分类信息 从数据库查询....");
            //3.如果为空,需要从数据库查询,在将数据存入redis
            //3.1 从数据库查询
            cs = categoryDao.findAll();
            //3.2 将集合数据存储到redis中的 category的key
            for (int i = 0; i < cs.size(); i++) {
                //让 sortedset 按id号排序
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        } else {
            System.out.println("分类信息 从redis中查询.....");
            //4.如果不为空,将从redis取出的set<String>数据存入list<Category>
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }

    /**
     * 根据 cid 查询 分类信息
     * @param cid
     * @return
     */
    @Override
    public Category findByCid(String cid){
        // 查询 分类 列表
        List<Category> cs = this.findAll();
        // 用于存放 查询出 某个分类
        Category category = null;
        if (cid != null){
            for (int i = 0; i < cs.size(); i++) {
                Category c = cs.get(i);
                if (Integer.parseInt(cid) == c.getCid()){
                    category = c;
                    break;
                }
            }
        }
        return category;
    }

}
