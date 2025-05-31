package com.licai.dao;

import cn.hutool.core.util.StrUtil;
import com.licai.common.Constast;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户数据层操作
 */
public class UserDao extends BaseDao {

    /**
     * 登录校验
     * @param username
     * @param password
     * @return
     */
    public Map<String, Object> getUserNameAndPassowrd(String username, String password) throws SQLException {
        String sql = "SELECT id,u_name,u_isadmin FROM licai_user WHERE u_name = ? AND u_password = ? AND u_stop = 1";
        Object[] params = {username, password};
        return find(Map.class, sql, params);
    }

    /**
     * 读取管理列表数据
     * @return
     * @throws SQLException
     */
    public List<Map> findAllAdmin() throws SQLException {
        String sql = "select id, u_name, u_img, u_tel, u_email, u_desc, u_stop from licai_user where u_isadmin = 1";
        return findAll(sql);
    }

    /**
     * 分页查询 - 读取管理列表数据
     * @return
     * @throws SQLException
     */
    public List<Map> findAllAdmin(String page, String limit) throws SQLException {
        int iLimit = Integer.parseInt(limit);
        int iStart = (Integer.parseInt(page) - 1) * iLimit;
        String sql = "select id, u_name, u_img, u_tel, u_email, u_desc, u_stop from licai_user where u_isadmin = 1 limit ?, ?";
        Object[] params = {iStart, iLimit};
        return findAll(sql, params);
    }

    /**
     * 禁用管理员账号
     * @param id
     * @param use
     * @throws SQLException
     */
    public void stopAdmin(String id, String use) throws SQLException {
        String sql = "update licai_user set u_stop = ? where id = ?";
        Object[] params = {use, id};
        updateAll(sql, params);
    }

    /**
     * 删除管理员账号
     * @param id
     */
    public void delAdmin(String id) throws SQLException {
        String sql = "delete from licai_user where id = ?";
        Object[] params = {id};
        updateAll(sql, params);
    }

    /**
     * 编辑管理员信息
     * @param id
     * @param pass
     * @param email
     * @param tel
     */
    public void editAdmin(String id, String pass, String email, String tel, String img) throws SQLException {
        String sql = "update licai_user set u_password = ?, u_tel = ?, u_email = ?, u_img = ? where id = ?";
        Object[] params = {pass, tel, email, img, id};
        updateAll(sql, params);
    }

    /**
     * 添加管理员信息
     * @param id
     * @param md5
     * @param email
     * @param tel
     * @param img
     * @param desc
     * @param username
     */
    public void addAdmin(String id, String username, String md5, String email, String tel, String img, String desc) throws SQLException {
        String sql = "select count(id) from licai_user where u_name = ?";
        Object[] params = {username};
        Integer integer = find(Integer.class, sql, params);
        if(integer > 0) {
            throw new RuntimeException(Constast.USERNAME_IS_EXIST);
        }
        sql = "insert into licai_user (id, u_name, u_password, u_tel, u_img, u_stop, u_isadmin, u_email, u_desc) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params2 = {id, username, md5, tel, img, 1, 1, email, desc};
        updateAll(sql, params2);
    }

    /**
     * 搜索管理员信息
     * @param username
     * @return
     */
    public List<Map> searchAdmin(String username) throws SQLException {
        StringBuffer stringBuffer = new StringBuffer("select id, u_name, u_img, u_tel, u_email, u_desc, u_stop from licai_user where u_isadmin = 1");
        if(!StrUtil.isEmpty(username)) {
            stringBuffer.append(" and u_name like concat('%',?,'%')");
            Object[] params = {username};
            return findAll(stringBuffer.toString(), params);
        } else {
            return findAll(stringBuffer.toString());
        }
    }

    /**
     * 获取会员列表数据
     * @return
     */
    public List<Map> findAllUser() throws SQLException {
        String sql = "select id, u_name, u_img, u_tel, u_email, u_desc, u_stop from licai_user where u_isadmin = 0";
        return findAll(sql);
    }

    /**
     * 搜索管理员信息
     * @param username
     * @return
     */
    public List<Map> searchUser(String username) throws SQLException {
        StringBuffer stringBuffer = new StringBuffer("select id, u_name, u_img, u_tel, u_email, u_desc, u_stop from licai_user where u_isadmin = 0");
        if(!StrUtil.isEmpty(username)) {
            stringBuffer.append(" and u_name like concat('%',?,'%')");
            Object[] params = {username};
            return findAll(stringBuffer.toString(), params);
        } else {
            return findAll(stringBuffer.toString());
        }
    }

    /**
     * 获取记录总数
     * @return
     * @throws SQLException
     */
    public Map<String, Long> findAdminCount() throws SQLException {
        String sql = "select count(id) num from licai_user where u_isadmin = 1";
        return find(Map.class, sql);
    }

    /**
     * 添加会员信息
     * @param id
     * @param username
     * @param md5
     * @param email
     * @param tel
     * @param img
     * @param desc
     */
    public void addUser(String id, String username, String md5, String email, String tel, String img, String desc) throws SQLException {
        String sql = "select count(id) from licai_user where u_name = ?";
        Object[] params = {username};
        Integer integer = find(Integer.class, sql, params);
        if(integer > 0) {
            throw new RuntimeException(Constast.USERNAME_IS_EXIST);
        }
        sql = "insert into licai_user (id, u_name, u_password, u_tel, u_img, u_stop, u_isadmin, u_email, u_desc) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params2 = {id, username, md5, tel, img, 1, 0, email, desc};
        updateAll(sql, params2);
    }
}
