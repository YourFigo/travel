package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * Seller 的持久化类
 */
public interface SellerDao {
    /**
     * 根据id查询 商家信息
     * @param id
     * @return
     */
    public Seller findById(int id);
}
