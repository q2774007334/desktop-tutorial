package com.licai.service;

import cn.hutool.core.util.StrUtil;
import com.licai.dao.UserDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户操作业务功能类
 */
public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 获取数据总数
     * @return
     */
    public Map<String, Long> findAllAdminCount() throws SQLException {
        return userDao.findAdminCount();
    }

    /**
     * 登录校验
     * @param username
     * @param password
     * @return
     */
    public Map<String,Object> getUserNameAndPassowrd(String username, String password) throws SQLException {
        return userDao.getUserNameAndPassowrd(username, password);
    }

    /**
     * 获取管理员列表数据
     * @return
     */
    public List<Map> findAllAdmin() throws SQLException {
        return userDao.findAllAdmin();
    }

    /**
     * 分页查询 - 获取管理员列表数据
     * @param page
     * @param limit
     * @return
     */
    public List<Map> findAllAdmin(String page, String limit) throws SQLException {
        if(StrUtil.isEmpty(page) || StrUtil.isEmpty(limit)) {
            return findAllAdmin();
        }
        return userDao.findAllAdmin(page, limit);
    }

    /**
     * 禁用管理员账号
     * @param id
     * @param use
     * @throws SQLException
     */
    public void stopAdmin(String id, String use) throws SQLException {
        userDao.stopAdmin(id, use);
    }

    /**
     * 删除管理员账号
     * @param id
     */
    public void delAdmin(String id) throws SQLException {
        userDao.delAdmin(id);
    }

    /**
     * 编辑管理员信息
     * @param id
     * @param pass
     * @param email
     * @param tel
     */
    public void editAdmin(String id, String pass, String email, String tel, String img) throws SQLException {
        userDao.editAdmin(id, pass, email, tel, img);
    }

    /**
     * 添加管理员信息
      * @param id
     * @param username
     * @param md5
     * @param email
     * @param tel
     * @param img
     * @param desc
     * @throws SQLException
     */
    public void addAdmin(String id, String username, String md5, String email, String tel, String img, String desc) throws SQLException {
        userDao.addAdmin(id, username, md5, email, tel, img, desc);
    }

    /**
     * 搜索管理员信息
     * @param username
     * @return
     */
    public List<Map> searchAdmin(String username) throws SQLException {
        return userDao.searchAdmin(username);
    }

    /**
     * 获取会员列表数据
     * @return
     */
    public List<Map> findAllUser() throws SQLException {
        return userDao.findAllUser();
    }

    /**
     * 搜索会员信息
     * @param username
     * @return
     */
    public List<Map> searchUser(String username) throws SQLException {
        return userDao.searchUser(username);
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
        userDao.addUser(id, username, md5, email, tel, img, desc);
    }
}
