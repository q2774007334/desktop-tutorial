package com.licai.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户消费数据操作
 */
public class ConsumeDao extends BaseDao{

    /**
     * 获取用户消费数据
     * @return
     */
    public List findAllConsume() throws SQLException {
        String sql = "SELECT a.id, b.f_name, c_type, c_money, DATE_FORMAT(c_date, '%Y-%m-%d') f_date, a.u_id, c.f_income FROM licai_consume a, \n" +
                "(SELECT id, f_name FROM licai_family) b, (SELECT f_income, u_id, f_name FROM licai_family_income WHERE f_type_money = 0) c\n" +
                "WHERE b.id = a.u_id AND c.u_id = a.u_id AND c.f_name = a.c_type";
        return findAll(sql);
    }

    /**
     * 删除用户所消费金额信息
     * @return
     */
    public void delConsume(String id) throws SQLException {
        String sql = "delete from licai_consume where id = ?";
        Object[] params = {id};
        updateAll(sql, params);
    }

    /**
     * 最近一周消费数据 - 曲线图
     * @param u_id
     * @return
     */
    public List<Map> findConsumeStatistics1(String u_id) throws SQLException {
        String sql = "SELECT SUM(CASE WHEN DATE_FORMAT(DATE(c_date),'%Y-%m-%d') = DATE_SUB(CURDATE(), INTERVAL 7 DAY) THEN c_money ELSE 0 END) AS c_money, DATE_SUB(CURDATE(), INTERVAL 7 DAY) AS c_date FROM licai_consume WHERE u_id IN (SELECT id FROM licai_family WHERE u_id = ?)\n" +
                "UNION ALL\n" +
                "SELECT SUM(CASE WHEN DATE_FORMAT(DATE(c_date),'%Y-%m-%d') = DATE_SUB(CURDATE(), INTERVAL 6 DAY) THEN c_money ELSE 0 END) AS c_money, DATE_SUB(CURDATE(), INTERVAL 6 DAY) AS c_date FROM licai_consume WHERE u_id IN (SELECT id FROM licai_family WHERE u_id = ?)\n" +
                "UNION ALL\n" +
                "SELECT SUM(CASE WHEN DATE_FORMAT(DATE(c_date),'%Y-%m-%d') = DATE_SUB(CURDATE(), INTERVAL 5 DAY) THEN c_money ELSE 0 END) AS c_money, DATE_SUB(CURDATE(), INTERVAL 5 DAY) AS c_date FROM licai_consume WHERE u_id IN (SELECT id FROM licai_family WHERE u_id = ?)\n" +
                "UNION ALL\n" +
                "SELECT SUM(CASE WHEN DATE_FORMAT(DATE(c_date),'%Y-%m-%d') = DATE_SUB(CURDATE(), INTERVAL 4 DAY) THEN c_money ELSE 0 END) AS c_money, DATE_SUB(CURDATE(), INTERVAL 4 DAY) AS c_date FROM licai_consume WHERE u_id IN (SELECT id FROM licai_family WHERE u_id = ?)\n" +
                "UNION ALL\n" +
                "SELECT SUM(CASE WHEN DATE_FORMAT(DATE(c_date),'%Y-%m-%d') = DATE_SUB(CURDATE(), INTERVAL 3 DAY) THEN c_money ELSE 0 END) AS c_money, DATE_SUB(CURDATE(), INTERVAL 3 DAY) AS c_date FROM licai_consume WHERE u_id IN (SELECT id FROM licai_family WHERE u_id = ?)\n" +
                "UNION ALL\n" +
                "SELECT SUM(CASE WHEN DATE_FORMAT(DATE(c_date),'%Y-%m-%d') = DATE_SUB(CURDATE(), INTERVAL 2 DAY) THEN c_money ELSE 0 END) AS c_money, DATE_SUB(CURDATE(), INTERVAL 2 DAY) AS c_date FROM licai_consume WHERE u_id IN (SELECT id FROM licai_family WHERE u_id = ?)\n" +
                "UNION ALL\n" +
                "SELECT SUM(CASE WHEN DATE_FORMAT(DATE(c_date),'%Y-%m-%d') = DATE_SUB(CURDATE(), INTERVAL 1 DAY) THEN c_money ELSE 0 END) AS c_money, DATE_SUB(CURDATE(), INTERVAL 1 DAY) AS c_date FROM licai_consume WHERE u_id IN (SELECT id FROM licai_family WHERE u_id = ?)";
        Object[] params = {u_id, u_id, u_id, u_id, u_id, u_id, u_id};
        return findAll(sql, params);
    }

    /**
     * 最近一周消费来源 - 饼图
     * @param u_id
     * @return
     */
    public List<Map> findConsumeStatistics2(String u_id) throws SQLException {
        String sql = "SELECT c_type, SUM(c_money) c_money FROM licai_consume WHERE DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(c_date) AND u_id IN (SELECT id FROM licai_family WHERE u_id = ?) GROUP BY c_type ";
        Object[] params = {u_id};
        return findAll(sql, params);
    }
}
