package com.licai.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.licai.common.Constast;
import com.licai.common.Result;
import com.licai.common.TableResult;
import com.licai.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户数据接口 - 针对管理员操作
 */
@WebServlet("/system/user")
public class UserController extends BaseController {

    private UserService userService = new UserService();

    /**
     * 获取管理员列表数据
     * @param request
     * @param response
     * @return
     */
    public String findAllAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        // 分页参数
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        // 获取数据总数
        Map<String, Long> size = userService.findAllAdminCount();
        System.out.println(size.get("num").getClass());
        // 获取管理员数据
        List<Map> list = userService.findAllAdmin(page, limit);
        // 进行数据封装, 组成表格数据
        TableResult<List> tableResult = new TableResult<List>(0, "ok", size.get("num").intValue(), list);
        // 输出表格数据
        getJson(tableResult, response);

        return null;
    }

    /**
     * 禁用管理员账号
     * @param request
     * @param response
     * @return
     */
    public String stopAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        String use = request.getParameter("use");
        if(StrUtil.isEmpty(id) || StrUtil.isEmpty(use)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        userService.stopAdmin(id, use);
        getJson(Result.succ(Constast.UPDATE_SUCCESS), response);
        return null;
    }

    /**
     * 删除管理员账号
     * @param request
     * @param response
     * @return
     */
    public String delAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        if(StrUtil.isEmpty(id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        userService.delAdmin(id);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 编辑管理员信息
     * @param request
     * @param response
     * @return
     */
    public String editAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String img = request.getParameter("u_img");
        if(StrUtil.isEmpty(id) || StrUtil.isEmpty(pass) || StrUtil.isEmpty(repass) || StrUtil.isEmpty(email) || StrUtil.isEmpty(tel)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        if(!StrUtil.equals(pass, repass)) {
            getJson(Result.fail(Constast.PASSWORD_NOT_EQUALS), response);
            return null;
        }
        userService.editAdmin(id, SecureUtil.md5(pass), email, tel, img);
        getJson(Result.succ(Constast.UPDATE_SUCCESS), response);
        return null;
    }

    /**
     * 添加管理员信息
     * @param request
     * @param response
     * @return
     */
    public String addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Snowflake snowflake = new Snowflake(2,3);
        String id = snowflake.nextIdStr();
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String img = request.getParameter("img");
        String desc = request.getParameter("desc");
        if(StrUtil.isEmpty(username) || StrUtil.isEmpty(id) || StrUtil.isEmpty(pass) || StrUtil.isEmpty(repass) || StrUtil.isEmpty(email) || StrUtil.isEmpty(tel) || StrUtil.isEmpty(img) || StrUtil.isEmpty(desc)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        if(!StrUtil.equals(pass, repass)) {
            getJson(Result.fail(Constast.PASSWORD_NOT_EQUALS), response);
            return null;
        }
        userService.addAdmin(id, username , SecureUtil.md5(pass), email, tel, img, desc);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 搜索管理员
     * @param request
     * @param response
     * @return
     */
    public String searchAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String username = request.getParameter("username");
        List<Map> list = userService.searchAdmin(username);
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 获取会员列表数据
     * @param request
     * @param response
     * @return
     */
    public String findAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List<Map> list = userService.findAllUser();
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 搜索会员信息
     * @param request
     * @param response
     * @return
     */
    public String searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String username = request.getParameter("username");
        List<Map> list = userService.searchUser(username);
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 添加会员信息
     * @param request
     * @param response
     * @return
     */
    public String addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Snowflake snowflake = new Snowflake(2,3);
        String id = snowflake.nextIdStr();
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String img = request.getParameter("img");
        String desc = request.getParameter("desc");
        if(StrUtil.isEmpty(username) || StrUtil.isEmpty(id) || StrUtil.isEmpty(pass) || StrUtil.isEmpty(repass) || StrUtil.isEmpty(email) || StrUtil.isEmpty(tel) || StrUtil.isEmpty(img) || StrUtil.isEmpty(desc)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        if(!StrUtil.equals(pass, repass)) {
            getJson(Result.fail(Constast.PASSWORD_NOT_EQUALS), response);
            return null;
        }
        userService.addUser(id, username , SecureUtil.md5(pass), email, tel, img, desc);
        getJson(Result.succ("ok"), response);
        return null;
    }

}
