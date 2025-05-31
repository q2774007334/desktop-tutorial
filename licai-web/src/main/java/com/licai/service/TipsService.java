package com.licai.service;

import cn.hutool.core.lang.Snowflake;
import com.licai.common.TableResult;
import com.licai.dao.TipsDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipsService {

    private TipsDao tipsDao = new TipsDao();

    // 添加公告信息的业务逻辑
    public void addTips(String tips_title, String tips_content, String tips_date) throws SQLException {
        Snowflake snowflake = new Snowflake(2,3);
        String id = snowflake.nextIdStr();
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("tips_title", tips_title);
        map.put("tips_content", tips_content);
        map.put("tips_date", tips_date);
        tipsDao.addTips(map);
    }

    // 获取公告信息的业务逻辑
    public TableResult getTipsList(String tips_title, String page, String limit) throws SQLException {
        List<Map> list = tipsDao.getTipsList(tips_title, page, limit);
        Map<String, Long> map = tipsDao.getTipsListCount(tips_title);
        return new TableResult(0, "ok", map.get("num").intValue(), list);
    }

    // 编辑公告信息的业务逻辑
    public void editTips(String id, String tips_title, String tips_content, String tips_date) throws SQLException {
        tipsDao.editTips(id, tips_title, tips_content, tips_date);
    }

    // 删除公告信息的业务逻辑
    public void delTips(String id) throws SQLException {
        tipsDao.delTips(id);
    }
}
