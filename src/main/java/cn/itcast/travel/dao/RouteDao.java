package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * 点击分类导航条中每个分类按钮后的操作
 */
public interface RouteDao {

    /**
     * 根据cid和rname查询总记录数
     * @param cid
     * @param rname
     * @return
     */
    public int findTotalCount(int cid, String rname);

    /**
     * 根据cid，start,pageSize,rname查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据id查询 route信息
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
