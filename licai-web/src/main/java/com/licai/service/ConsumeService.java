package com.licai.service;

import com.licai.dao.ConsumeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户消费业务逻辑层
 */
public class ConsumeService {
    private ConsumeDao consumeDao = new ConsumeDao();

    /**
     * 获取所有用户消费信息
     * @return
     */
    public List findAllConsume() throws SQLException {
        return consumeDao.findAllConsume();
    }

    /**
     * 删除用户所消费金额信息
     * @return
     */
    public void delConsume(String id) throws SQLException {
        consumeDao.delConsume(id);
    }

    /**
     * 最近一周消费数据 - 曲线图
     * @param u_id
     * @return
     */
    public List<Map> findConsumeStatistics1(String u_id) throws SQLException {
        return consumeDao.findConsumeStatistics1(u_id);
    }

    /**
     * 最近一周消费来源 - 饼图
     * @param u_id
     * @return
     */
    public List<Map> findConsumeStatistics2(String u_id) throws SQLException {
        return consumeDao.findConsumeStatistics2(u_id);
    }
}
