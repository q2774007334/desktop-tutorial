package com.licai.service;

import com.licai.dao.MemberDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 会员信息管理的业务功能
 */
public class MemberService {

    private MemberDao memberDao = new MemberDao();

    /**
     * 读取会员个人信息
     * @param id
     * @return
     */
    public List<Map> memberInfo(String id) throws SQLException {
        return memberDao.memberInfo(id);
    }

    /**
     * 展示家庭成员列表 - 一个会员账号下记录的家庭成员信息
     * @param id
     * @return
     */
    public List<Map> familyInfo(String id) throws SQLException {
        return memberDao.familyInfo(id);
    }

    /**
     * 编辑家庭成员
     * @param id
     * @param f_name
     */
    public void editFamily(String id, String f_name) throws SQLException {
        memberDao.editFamily(id, f_name);
    }

    /**
     * 删除家庭成员
     * @param id
     */
    public void delFamily(String id) throws SQLException {
        memberDao.delFamily(id);
    }

    /**
     * 新增家庭成员
     * @param id
     * @param f_name
     * @param u_id
     */
    public void addFamily(String id, String f_name, String u_id) throws SQLException {
        memberDao.addFamily(id, f_name, u_id);
    }

    /**
     * 家庭收入管理 - 每个家庭成员收入数据展示
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyIncomeList(String u_id) throws SQLException {
       return memberDao.memberFamilyIncomeList(u_id);
    }

    /**
     * 异步读取家庭成员到下拉框中
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyToSelect(String u_id) throws SQLException {
        return memberDao.memberFamilyToSelect(u_id);
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
        memberDao.addMemberFamilyIncome(nextIdStr, f_name, f_income, f_date, id);
    }

    /**
     * 编辑家庭成员收入信息
     * @param id
     * @param f_name
     * @param f_income
     * @param f_date
     */
    public void editMemberFamilyIncome(String id, String f_name, String f_income, String f_date) throws SQLException {
        memberDao.editMemberFamilyIncome(id, f_name, f_income, f_date);
    }

    /**
     * 删除家庭成员收入信息
     * @param id
     */
    public void delMemberFamilyIncome(String id) throws SQLException {
        memberDao.delMemberFamilyIncome(id);
    }

    /**
     * 家庭消费管理 - 每个家庭成员消费上线设置的数据
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyConsumeList(String u_id) throws SQLException {
        return memberDao.memberFamilyConsumeList(u_id);
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
        memberDao.addMemberFamilyConsume(nextIdStr, f_name, f_income, f_date, id);
    }

    /**
     * 获取成员消费列表
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyPersonalConsumeList(String u_id) throws SQLException {
        return memberDao.memberFamilyPersonalConsumeList(u_id);
    }

    /**
     * 通过家庭成员获取对应的消费类型
     * @param u_id
     * @return
     */
    public List<Map> memberFamilyPersonalConsumeByType(String u_id) throws SQLException {
        return memberDao.memberFamilyPersonalConsumeByType(u_id);
    }

    /**
     * 通过选择消费类型获取对应的消费限额
     * @param u_id
     * @param f_name
     * @param f_date
     * @return
     */
    public Map memberFamilyPersonalConsumeByIncome(String u_id, String f_name, String f_date) throws SQLException {
        return memberDao.memberFamilyPersonalConsumeByIncome(u_id, f_name, f_date);
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
        memberDao.addMemberFamilyPersonalConsume(id, u_id, f_name, f_income, f_date);
    }

    /**
     * 编辑成员消费
     * @param id
     * @param c_money
     * @param f_date
     */
    public void editMemberFamilyPersonalConsume(String id, String c_money, String f_date) throws SQLException {
        memberDao.editMemberFamilyPersonalConsume(id, c_money, f_date);
    }

    /**
     * 删除家庭成员消费数据
     * @param id
     */
    public void delMemberFamilyPersonalConsume(String id) throws SQLException {
        memberDao.delMemberFamilyPersonalConsume(id);
    }

    /**
     * 消费汇总统计
     * @param u_id
     * @param type
     * @return
     */
    public List<Map> memberFamilyPersonalConsumeDailyList(String u_id, String type) throws SQLException {
        return memberDao.memberFamilyPersonalConsumeDailyList(u_id, type);
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
        return memberDao.memberFamilyPersonalConsumeListSearch(u_id, type, start, end);
    }
}
