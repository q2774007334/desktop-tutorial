package com.licai.dao;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.PageResult;
import cn.hutool.db.handler.BeanHandler;
import cn.hutool.db.handler.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseDao {

    private static Db db = null;

    public BaseDao() {
        if(db == null) {
            db = Db.use();
        }
    }

    /**
     * 返回指定数据结构列表
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> List<T> findAll(Class<T> clazz, String sql, Object... params) throws SQLException {
        List<Entity> list = db.query(sql, params);
        List<T> queryList = new ArrayList<>();
        for(Entity entity : list) {
            T data = BeanUtil.toBean(entity, clazz);
            queryList.add(data);
        }
        return queryList;
    }

    /**
     * 返回Map结构数据列表
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map> findAll(String sql, Object... params) throws SQLException {
        List<Map> list = db.query(sql, new BeanListHandler<>(Map.class), params);
        return list;
    }

    /**
     * 返回一条数据结果
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> T find(Class<T> clazz, String sql, Object... params) throws SQLException {
        T t = db.query(sql, new BeanHandler<>(clazz), params);
        return t;
    }

    /**
     * 封装增, 删, 改操作
     * @param sql
     * @param params
     * @throws SQLException
     */
    public void  updateAll(String sql, Object... params) throws SQLException {
        db.execute(sql, params);
    }

    /**
     * 单表分页查询
     * @param tableName
     * @param page
     * @param numPerPage
     * @return
     * @throws SQLException
     */
    public List findOneTablePage(String tableName, int page, int numPerPage) throws SQLException {
        PageResult<Entity> list = db.page(Entity.create(tableName), page-1, numPerPage);
        return list;
    }
}
