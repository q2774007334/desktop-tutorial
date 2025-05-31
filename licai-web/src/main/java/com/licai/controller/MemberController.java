package com.licai.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import com.licai.common.Constast;
import com.licai.common.Result;
import com.licai.common.TableResult;
import com.licai.service.MemberService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/system/member")
public class MemberController extends BaseController {

    private MemberService memberService = new MemberService();

    /**
     * 展示个人信息列表
     * @param request
     * @param response
     * @return
     */
    public String memberInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Map user = (Map) request.getSession().getAttribute("map");
        String id = (String) user.get("id");
        if(StrUtil.isEmpty(id)) {
            throw new RuntimeException(id);
        }
        List<Map> list = memberService.memberInfo(id);
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 展示家庭成员列表 - 一个会员账号下记录的家庭成员信息
     * @param request
     * @param response
     * @return
     */
    public String familyInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Map user = (Map) request.getSession().getAttribute("map");
        String id = (String) user.get("id"); // 获取会员账号
        if(StrUtil.isEmpty(id)) {
            throw new RuntimeException(id);
        }
        List<Map> list = memberService.familyInfo(id);
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 编辑家庭成员
     * @param request
     * @param response
     * @return
     */
    public String editFamily(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id"); // 获取家庭成员的id
        String f_name = request.getParameter("f_name"); // 获取家庭成员的名称
        if(StrUtil.isEmpty(id) || StrUtil.isEmpty(f_name)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        memberService.editFamily(id, f_name);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 删除家庭成员
     * @param request
     * @param response
     * @return
     */
    public String delFamily(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id"); // 获取家庭成员的id
        if(StrUtil.isEmpty(id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        memberService.delFamily(id);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 新增家庭成员
     * @param request
     * @param response
     * @return
     */
    public String addFamily(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String f_name = request.getParameter("username");
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号
        if(StrUtil.isEmpty(f_name) || StrUtil.isEmpty(u_id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        Snowflake snowflake = new Snowflake(2, 3);
        String id = snowflake.nextIdStr();
        memberService.addFamily(id, f_name, u_id);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 家庭收入管理 - 每个家庭成员收入数据展示
     * @param request
     * @param response
     * @return
     */
    public String memberFamilyIncomeList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        if(StrUtil.isEmpty(u_id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        List<Map> list = memberService.memberFamilyIncomeList(u_id);
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 异步读取家庭成员到下拉框中
     * @param request
     * @param response
     * @return
     */
    public String memberFamilyToSelect(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        if(StrUtil.isEmpty(u_id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        List<Map> list = memberService.memberFamilyToSelect(u_id);
        getJson(Result.succ(list), response);
        return null;
    }

    /**
     * 添加家庭成员收入信息
     * @param request
     * @param response
     * @return
     */
    public String addMemberFamilyIncome(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        String id = request.getParameter("username"); // 获取家庭成员id
        String f_name = request.getParameter("f_name"); // 收入类型
        String f_income = request.getParameter("f_income"); // 收入金额
        String f_date = request.getParameter("f_date"); // 收入时间
        if(StrUtil.isEmpty(u_id) || StrUtil.isEmpty(id) || StrUtil.isEmpty(f_name) || StrUtil.isEmpty(f_income) || StrUtil.isEmpty(f_date)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        Snowflake snowflake = new Snowflake(2, 3);
        String nextIdStr = snowflake.nextIdStr();
        memberService.addMemberFamilyIncome(nextIdStr, f_name, f_income, f_date, id);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 编辑家庭成员收入信息
     * @param request
     * @param response
     * @return
     */
    public String editMemberFamilyIncome(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        String f_name = request.getParameter("f_name"); // 收入类型
        String f_income = request.getParameter("f_income"); // 收入金额
        String f_date = request.getParameter("f_date"); // 收入时间
        if(StrUtil.isEmpty(id) || StrUtil.isEmpty(f_name) || StrUtil.isEmpty(f_income) || StrUtil.isEmpty(f_date)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        memberService.editMemberFamilyIncome(id, f_name, f_income, f_date);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 删除家庭成员收入信息
     * @param request
     * @param response
     * @return
     */
    public String delMemberFamilyIncome(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        if(StrUtil.isEmpty(id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        memberService.delMemberFamilyIncome(id);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 家庭消费管理 - 每个家庭成员消费上线设置的数据
     * @param request
     * @param response
     * @return
     */
    public String memberFamilyConsumeList(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        if(StrUtil.isEmpty(u_id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        List<Map> list = memberService.memberFamilyConsumeList(u_id);
        TableResult<List<Map>> tableResult = new TableResult<>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 设置消费上限信息
     * @param request
     * @param response
     * @return
     */
    public String addMemberFamilyConsume(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        String id = request.getParameter("username"); // 获取家庭成员id
        String f_name = request.getParameter("f_name"); // 收入类型
        String f_income = request.getParameter("f_income"); // 收入金额
        String f_date = request.getParameter("f_date"); // 收入时间
        if(StrUtil.isEmpty(u_id) || StrUtil.isEmpty(id) || StrUtil.isEmpty(f_name) || StrUtil.isEmpty(f_income) || StrUtil.isEmpty(f_date)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        Snowflake snowflake = new Snowflake(2, 3);
        String nextIdStr = snowflake.nextIdStr();
        memberService.addMemberFamilyConsume(nextIdStr, f_name, f_income, f_date, id);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 获取成员消费列表
     * @param request
     * @param response
     * @return
     */
    public String memberFamilyPersonalConsumeList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        if(StrUtil.isEmpty(u_id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        List<Map> list = memberService.memberFamilyPersonalConsumeList(u_id);
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 通过家庭成员获取对应的消费类型
     * @param request
     * @param response
     * @return
     */
    public String memberFamilyPersonalConsumeByType(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String u_id = request.getParameter("u_id");
        if(StrUtil.isEmpty(u_id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        List<Map> list = memberService.memberFamilyPersonalConsumeByType(u_id);
        getJson(Result.succ(list), response);
        return null;
    }

    /**
     * 通过选择消费类型获取对应的消费限额
     * @return
     */
    public String memberFamilyPersonalConsumeByIncome(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String f_name = request.getParameter("f_name");
        String f_date = request.getParameter("f_date");
        String u_id = request.getParameter("u_id");
        if(StrUtil.isEmpty(f_name) || StrUtil.isEmpty(f_date) || StrUtil.isEmpty(u_id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        Map map = memberService.memberFamilyPersonalConsumeByIncome(u_id, f_name, f_date);
        getJson(Result.succ(map), response);
        return null;
    }

    /**
     * 添加成员消费
     * @param request
     * @param response
     * @return
     */
    public String addMemberFamilyPersonalConsume(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String u_id = request.getParameter("username"); // 家庭成员的id
        String f_name = request.getParameter("f_name");  // 消费类型
        String f_income = request.getParameter("f_income"); // 消费金额
        String f_date = request.getParameter("f_date"); // 消费时间
        if(StrUtil.isEmpty(u_id) || StrUtil.isEmpty(f_name) || StrUtil.isEmpty(f_income) || StrUtil.isEmpty(f_date)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        Snowflake snowflake = new Snowflake(2, 3);
        String id = snowflake.nextIdStr();
        memberService.addMemberFamilyPersonalConsume(id, u_id, f_name, f_income, f_date);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 编辑成员消费
     * @param request
     * @param response
     * @return
     */
    public String editMemberFamilyPersonalConsume(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        String c_money = request.getParameter("c_money");
        String f_date = request.getParameter("f_date");
        if(StrUtil.isEmpty(id) || StrUtil.isEmpty(c_money) || StrUtil.isEmpty(f_date)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        memberService.editMemberFamilyPersonalConsume(id, c_money, f_date);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 删除家庭成员消费数据
     * @param request
     * @param response
     * @return
     */
    public String delMemberFamilyPersonalConsume(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        if(StrUtil.isEmpty(id)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        memberService.delMemberFamilyPersonalConsume(id);
        getJson(Result.succ("ok"), response);
        return null;
    }

    /**
     * 消费汇总统计
     * @param request
     * @param response
     * @return
     */
    public String memberFamilyPersonalConsumeDailyList(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        String type = request.getParameter("type");
        if(StrUtil.isEmpty(u_id) || StrUtil.isEmpty(type)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        List<Map> list = memberService.memberFamilyPersonalConsumeDailyList(u_id, type);
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

    /**
     * 消费汇总统计 - 搜索功能
     * @param request
     * @param response
     * @return
     */
    public String memberFamilyPersonalConsumeListSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Map user = (Map) request.getSession().getAttribute("map");
        String u_id = (String) user.get("id"); // 获取会员账号, 一个会员账号可以管理多个家庭成员
        String type = request.getParameter("type");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        if(StrUtil.isEmpty(u_id) || StrUtil.isEmpty(type) || StrUtil.isEmpty(start) || StrUtil.isEmpty(end)) {
            throw new RuntimeException(Constast.PARAMETER_NULL);
        }
        List<Map> list = memberService.memberFamilyPersonalConsumeListSearch(u_id, type, start, end);
        TableResult<List> tableResult = new TableResult<List>(0, "ok", list.size(), list);
        getJson(tableResult, response);
        return null;
    }

}
