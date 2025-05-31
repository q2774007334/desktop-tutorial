package com.licai.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.licai.common.Constast;
import com.licai.common.Result;
import com.licai.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends BaseController {
    private UserService userService = new UserService();

    /**
     * 登录系统
     * @param request
     * @param response
     * @return
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        // 获取登录数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        if(StrUtil.isEmpty(username) || StrUtil.isEmpty(password) || StrUtil.isEmpty(captcha)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        // 进行验证码判断
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        if(!StrUtil.equalsIgnoreCase(captcha, code)) {
            getJson(Result.fail("验证码错误"), response);
            return null;
        }

        // 数据库校验
        Map<String, Object> map = userService.getUserNameAndPassowrd(username, SecureUtil.md5(password));
        if(ObjectUtil.isNull(map)) {
            getJson(Result.fail("登录失败,请检查账号和密码是否正确"), response);
            return null;
        }
        session.setAttribute("map", map);
        getJson(Result.succ("登录成功"), response);
        return null;
    }

    /**
     * 登录退出
     * @param request
     * @param response
     * @return
     */
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return PROJECT_REDIRECT + "/index.jsp";
    }

}
