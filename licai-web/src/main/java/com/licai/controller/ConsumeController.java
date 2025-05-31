package com.licai.controller;

import cn.hutool.core.util.StrUtil;
import com.licai.common.Constast;
import com.licai.common.Result;
import com.licai.common.TableResult;
import com.licai.service.ConsumeService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户消费接口
 */
@WebServlet("/system/consume")
public class ConsumeController extends BaseController {

    private ConsumeService consumeService = new ConsumeService();

    /**
     * 获取所有用户的消费金额
     * @param request
     * @param response
     * @return
     */
    public String findAllConsume(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List list = consumeService.findAllConsume();
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 删除用户所消费金额信息
     * @param request
     * @param response
     * @return
     */
    public String delConsume(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        if(StrUtil.isEmpty(id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        consumeService.delConsume(id);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 最近一周消费数据 - 曲线图
     * @param request
     * @param response
     * @return
     */
    public String findConsumeStatistics1(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        if(StrUtil.isEmpty(u_id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        List<Map> list = consumeService.findConsumeStatistics1(u_id);
        getJson(Result.succ(list), response);
        return null;
    }

    /**
     * 最近一周消费来源 - 饼图
     * @param request
     * @param response
     * @return
     */
    public String findConsumeStatistics2(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        if(StrUtil.isEmpty(u_id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        List<Map> list = consumeService.findConsumeStatistics2(u_id);
        getJson(Result.succ(list), response);
        return null;
    }

}
