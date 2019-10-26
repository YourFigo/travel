package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * 主页中的导航条，分类信息展示
 */
public interface CategoryService {

    public List<Category> findAll();

    /**
     * 根据 cid 查询 分类信息
     * @param cid
     * @return
     */
    public Category findByCid(String cid);
}
