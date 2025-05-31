package com.licai.dao;

import cn.hutool.core.util.StrUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TipsDao extends BaseDao {

    // 添加公告信息
    public void addTips(Map<String, String> map) throws SQLException {
        String sql = "insert into licai_tips values (?,?,?,?)";
        Object[] params = {map.get("id"), map.get("tips_title"), map.get("tips_content"), map.get("tips_date")};
        updateAll(sql, params);
    }

    // 分页查询公告信息
    public List<Map> getTipsList(String tips_title, String page, String limit) throws SQLException {
        int iLimit = Integer.parseInt(limit);
        int iStart = (Integer.parseInt(page) - 1) * iLimit;
        String sql = "select id, tips_title, tips_content, date_format(tips_date, '%Y-%m-%d') tips_date from licai_tips";
        if(!StrUtil.isBlank(tips_title)) {
            sql = sql + " where tips_title like concat('%',?,'%') limit ?, ?";
            Object[] params = {tips_title, iStart, iLimit};
            return findAll(sql, params);
        }
        sql = sql + " limit ?, ?";
        Object[] params = {iStart, iLimit};
        return findAll(sql, params);
    }

    // 分页查询公告信息总记录数
    public Map<String, Long> getTipsListCount(String tips_title) throws SQLException {
        String sql = "select count(id) num from licai_tips";
        if(!StrUtil.isBlank(tips_title)) {
            sql = sql + " where tips_title like '%"+ tips_title +"%'";
            return find(Map.class, sql);
        }
        return find(Map.class, sql);
    }

    public void editTips(String id, String tips_title, String tips_content, String tips_date) throws SQLException {
        String sql = "update licai_tips set tips_title =?, tips_content = ?, tips_date = ? where id = ?";
        Object[] params = {tips_title, tips_content, tips_date, id};
        updateAll(sql, params);
    }

    public void delTips(String id) throws SQLException {
        String sql = "delete from licai_tips where id = ?";
        Object[] params = {id};
        updateAll(sql, params);
    }
}
