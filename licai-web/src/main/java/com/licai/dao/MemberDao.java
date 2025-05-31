package com.licai.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 会员管理数据层操作
 */
public class MemberDao extends BaseDao {

    /**
     * 读取会员个人信息
     * @param id
     * @return
     * @throws SQLException
     */
    public List<Map> memberInfo(String id) throws SQLException {
        String sql = "select id, u_name, u_img, u_tel, u_email, u_desc, u_stop from licai_user where id = ? limit 1";
        Object[] params = {id};
        return findAll(sql, params);
    }

    /**
     * 展示家庭成员列表 - 一个会员账号下记录的家庭成员信息
     * @param id
     * @return
     */
    public List<Map> familyInfo(String id) throws SQLException {
        String sql = "select * from licai_family where u_id = ?";
        Object[] params = {id};
        return findAll(sql, params);
    }

    /**
     * 编辑家庭成员
     * @param id
     * @param f_name
     */
    public void editFamily(String id, String f_name) throws SQLException {
        String sql = "update licai_family set f_name = ? where id = ?";
        Object[] params = {f_name, id};
        updateAll(sql, params);
    }

    /**
     * 删除家庭成员
     * @param id
     */
    public void delFamily(String id) throws SQLException {
        String sql = "delete from licai_family where id = ?";
        Object[] params = {id};
        updateAll(sql, params);
    }

    /**
     * 新增家庭成员
     * @param id
     * @param f_name
     * @param u_id
     */
    public void addFamily(String id, String f_name, String u_id) throws SQLException {
        String sql = "insert into licai_family (id, f_name, u_id) values (?,?,?)";
        Object[] params = {id, f_name, u_id};
        updateAll(sql, params);
    }

    /**
     * 家庭收入管理 - 每个家庭成员收入数据展示
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyIncomeList(String u_id) throws SQLException {
        String sql = "SELECT a.id, a.f_name, a.f_income, a.f_type_money, DATE_FORMAT(f_date, '%Y-%m-%d') f_date, a.u_id, b.f_name u_name FROM licai_family_income a, (SELECT f_name, id, u_id FROM licai_family WHERE u_id = ?) b WHERE a.u_id = b.id and a.f_type_money = 1";
        Object[] params = {u_id};
        return findAll(sql, params);
    }

    /**
     * 异步读取家庭成员到下拉框中
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyToSelect(String u_id) throws SQLException {
        String sql = "select id, f_name, u_id from licai_family where u_id = ?";
        Object[] params = {u_id};
        return findAll(sql, params);
    }

    /**
     * 添加家庭成员收入信息
     * @param nextIdStr
     * @param f_name
     * @param f_income
     * @param f_date
     * @param id
     */
    public void addMemberFamilyIncome(String nextIdStr, String f_name, String f_income, String f_date, String id) throws SQLException {
        String sql = "insert into licai_family_income (id, f_name, f_income, f_type_money, f_date, u_id) values (?,?,?,?,?,?)";
        Object[] params = {nextIdStr, f_name, f_income, 1, f_date, id};
        updateAll(sql, params);
    }

    /**
     * 编辑家庭成员收入信息
     * @param id
     * @param f_name
     * @param f_income
     * @param f_date
     */
    public void editMemberFamilyIncome(String id, String f_name, String f_income, String f_date) throws SQLException {
        String sql = "update licai_family_income set f_name = ?, f_income = ?, f_date = ? where id = ?";
        Object[] params = {f_name, f_income, f_date, id};
        updateAll(sql, params);
    }

    /**
     * 删除家庭成员收入信息
     * @param id
     */
    public void delMemberFamilyIncome(String id) throws SQLException {
        String sql = "delete from licai_family_income where id = ?";
        Object[] params = {id};
        updateAll(sql, params);
    }

    /**
     * 家庭消费管理 - 每个家庭成员消费上线设置的数据
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyConsumeList(String u_id) throws SQLException {
        String sql = "SELECT a.id, a.f_name, a.f_income, a.f_type_money, DATE_FORMAT(f_date, '%Y-%m-%d') f_date, a.u_id, b.f_name u_name FROM licai_family_income a, (SELECT f_name, id, u_id FROM licai_family WHERE u_id = ?) b WHERE a.u_id = b.id and a.f_type_money = 0";
        Object[] params = {u_id};
        return findAll(sql, params);
    }

    /**
     * 设置消费上限信息
     * @param nextIdStr
     * @param f_name
     * @param f_income
     * @param f_date
     * @param id
     */
    public void addMemberFamilyConsume(String nextIdStr, String f_name, String f_income, String f_date, String id) throws SQLException {
        String sql = "insert into licai_family_income (id, f_name, f_income, f_type_money, f_date, u_id) values (?,?,?,?,?,?)";
        Object[] params = {nextIdStr, f_name, f_income, 0, f_date, id};
        updateAll(sql, params);
    }

    /**
     * 获取成员消费列表
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyPersonalConsumeList(String u_id) throws SQLException {
        String sql = "SELECT a.id, b.f_name, c_type, c_money, DATE_FORMAT(c_date, '%Y-%m-%d') f_date, a.u_id, c.f_income FROM licai_consume a, \n" +
                "(SELECT id, f_name FROM licai_family WHERE u_id = ?) b, (SELECT f_income, u_id, f_name FROM licai_family_income WHERE f_type_money = 0) c\n" +
                "WHERE b.id = a.u_id AND c.u_id = a.u_id AND c.f_name = a.c_type;";
        Object[] params = {u_id};
        return findAll(sql, params);
    }

    /**
     * 通过家庭成员获取对应的消费类型
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyPersonalConsumeByType(String u_id) throws SQLException {
        String sql = "SELECT f_name FROM licai_family_income WHERE u_id = ? AND f_type_money = 0 GROUP BY f_name";
        Object[] params = {u_id};
        System.out.println("u_id = " + u_id);
        return findAll(sql, params);
    }

    /**
     * 通过选择消费类型获取对应的消费限额
     * @param u_id
     * @param f_name
     * @param f_date
     * @return
     */
    public Map memberFamilyPersonalConsumeByIncome(String u_id, String f_name, String f_date) throws SQLException {
        String sql = "SELECT SUM(f_income) f_income FROM licai_family_income WHERE u_id = ? AND f_name = ? AND f_type_money = 0 AND DATE_FORMAT(f_date, '%Y%m' ) = DATE_FORMAT(? , '%Y%m');";
        Object[] params = {u_id, f_name, f_date};
        return find(Map.class, sql, params);
    }

    /**
     * 添加成员消费
     * @param id
     * @param u_id
     * @param f_name
     * @param f_income
     * @param f_date
     */
    public void addMemberFamilyPersonalConsume(String id, String u_id, String f_name, String f_income, String f_date) throws SQLException {
        String sql = "insert into licai_consume (id, c_type, c_money, c_date, u_id) values (?,?,?,?,?)";
        Object[] params = {id, f_name, f_income, f_date, u_id};
        updateAll(sql, params);
    }

    /**
     * 编辑成员消费
     * @param id
     * @param c_money
     * @param f_date
     */
    public void editMemberFamilyPersonalConsume(String id, String c_money, String f_date) throws SQLException {
        String sql = "update licai_consume set c_money = ?, c_date = ? where id = ?";
        Object[] params = {c_money, f_date, id};
        updateAll(sql, params);
    }

    /**
     * 删除家庭成员消费数据
     * @param id
     */
    public void delMemberFamilyPersonalConsume(String id) throws SQLException {
        String sql = "delete from licai_consume where id = ?";
        Object[] params = {id};
        updateAll(sql, params);
    }

    /**
     * 消费汇总统计
     * @param u_id
     * @param type
     * @return
     */
    public List<Map> memberFamilyPersonalConsumeDailyList(String u_id, String type) throws SQLException {
        String sql = "";
        if(type.equals("daily")) {
            sql = "SELECT a.id, b.f_name, c_type, c_money, DATE_FORMAT(c_date, '%Y-%m-%d') f_date, a.u_id, c.f_income FROM licai_consume a, \n" +
                    "(SELECT id, f_name FROM licai_family WHERE u_id = ?) b, (SELECT f_income, u_id, f_name FROM licai_family_income WHERE f_type_money = 0) c\n" +
                    "WHERE b.id = a.u_id AND c.u_id = a.u_id AND c.f_name = a.c_type AND DATE_FORMAT(c_date, '%Y%m%d' ) = DATE_FORMAT(NOW(), '%Y%m%d');";
        } else if(type.equals("month")) {
            sql = "SELECT a.id, b.f_name, c_type, c_money, DATE_FORMAT(c_date, '%Y-%m-%d') f_date, a.u_id, c.f_income FROM licai_consume a, \n" +
                    "(SELECT id, f_name FROM licai_family WHERE u_id = ?) b, (SELECT f_income, u_id, f_name FROM licai_family_income WHERE f_type_money = 0) c\n" +
                    "WHERE b.id = a.u_id AND c.u_id = a.u_id AND c.f_name = a.c_type AND DATE_FORMAT(c_date, '%Y%m' ) = DATE_FORMAT(NOW(), '%Y%m');";
        } else if(type.equals("year")) {
            sql = "SELECT a.id, b.f_name, c_type, c_money, DATE_FORMAT(c_date, '%Y-%m-%d') f_date, a.u_id, c.f_income FROM licai_consume a, \n" +
                    "(SELECT id, f_name FROM licai_family WHERE u_id = ?) b, (SELECT f_income, u_id, f_name FROM licai_family_income WHERE f_type_money = 0) c\n" +
                    "WHERE b.id = a.u_id AND c.u_id = a.u_id AND c.f_name = a.c_type AND DATE_FORMAT(c_date, '%Y' ) = DATE_FORMAT(NOW(), '%Y');";
        }
        Object[] params = {u_id};
        return findAll(sql, params);
    }

    /**
     * 消费汇总统计 - 搜索功能
     * @param u_id
     * @param type
     * @param start
     * @param end
     * @return
     */
    public List<Map> memberFamilyPersonalConsumeListSearch(String u_id, String type, String start, String end) throws SQLException {
        String sql = "";
        if(type.equals("daily")) {
            sql = "SELECT a.id, b.f_name, c_type, c_money, DATE_FORMAT(c_date, '%Y-%m-%d') f_date, a.u_id, c.f_income FROM licai_consume a, \n" +
                    "(SELECT id, f_name FROM licai_family WHERE u_id = ?) b, (SELECT f_income, u_id, f_name FROM licai_family_income WHERE f_type_money = 0) c\n" +
                    "WHERE b.id = a.u_id AND c.u_id = a.u_id AND c.f_name = a.c_type AND DATE_FORMAT(c_date, '%Y%m%d' ) BETWEEN DATE_FORMAT(?, '%Y%m%d') and DATE_FORMAT(?, '%Y%m%d');";
        } else if(type.equals("month")) {
            sql = "SELECT a.id, b.f_name, c_type, c_money, DATE_FORMAT(c_date, '%Y-%m-%d') f_date, a.u_id, c.f_income FROM licai_consume a, \n" +
                    "(SELECT id, f_name FROM licai_family WHERE u_id = ?) b, (SELECT f_income, u_id, f_name FROM licai_family_income WHERE f_type_money = 0) c\n" +
                    "WHERE b.id = a.u_id AND c.u_id = a.u_id AND c.f_name = a.c_type AND DATE_FORMAT(c_date, '%Y%m' ) BETWEEN DATE_FORMAT(?, '%Y%m') and DATE_FORMAT(?, '%Y%m');";
        } else if(type.equals("year")) {
            sql = "SELECT a.id, b.f_name, c_type, c_money, DATE_FORMAT(c_date, '%Y-%m-%d') f_date, a.u_id, c.f_income FROM licai_consume a, \n" +
                    "(SELECT id, f_name FROM licai_family WHERE u_id = ?) b, (SELECT f_income, u_id, f_name FROM licai_family_income WHERE f_type_money = 0) c\n" +
                    "WHERE b.id = a.u_id AND c.u_id = a.u_id AND c.f_name = a.c_type AND DATE_FORMAT(c_date, '%Y' ) BETWEEN DATE_FORMAT(?, '%Y') and DATE_FORMAT(?, '%Y');";
        }
        Object[] params = {u_id, start, end};
        return findAll(sql, params);
    }
}
