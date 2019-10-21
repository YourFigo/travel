package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * 主页中的导航条，分类信息展示
 */
public interface CategoryDao {
    /**
     * 查询所有
     * @return
     */
    public List<Category> findAll();
}
