package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * 点击分类导航条中每个分类按钮后的操作
 */
public interface RouteDao {

    /**
     * 根据cid查询总记录数
     */
    public int findTotalCount(int cid, String rname);

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     */
    public List<Route> findByPage(int cid, int start, int pageSize, String rname);

}
