package com.licai.controller;

import com.licai.common.Result;
import com.licai.common.TableResult;
import com.licai.service.TipsService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/system/tips")
public class TipsController extends BaseController{

    private TipsService tipsService = new TipsService();

    // 添加公告信息
    public String addTips(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String tips_title = request.getParameter("tips_title");
        String tips_content = request.getParameter("tips_content");
        String tips_date = request.getParameter("tips_date");
        tipsService.addTips(tips_title, tips_content, tips_date);
        getJson(Result.succ("新增成功"), response);
        return null;
    }

    // 查询公告信息
    public String getTipsList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String tips_title = request.getParameter("tips_title");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        TableResult tableResult = tipsService.getTipsList(tips_title, page, limit);
        getJson(tableResult, response);
        return null;
    }

    // 编辑公告信息
    public String editTips(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        String tips_title = request.getParameter("tips_title");
        String tips_content = request.getParameter("tips_content");
        String tips_date = request.getParameter("tips_date");
        tipsService.editTips(id, tips_title, tips_content, tips_date);
        getJson(Result.succ("编辑成功"), response);
        return null;
    }

    // 删除公告信息
    public String delTips(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        tipsService.delTips(id);
        getJson(Result.succ("删除成功"), response);
        return null;
    }

}
