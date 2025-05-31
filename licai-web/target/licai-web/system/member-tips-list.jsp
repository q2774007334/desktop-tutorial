<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/8 0008
  Time: 8:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="../css/font.css">
    <link rel="stylesheet" href="../css/xadmin.css">
    <script src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="javascript:;">首页</a>
            <a href="javascript:;">公告管理</a>
            <a>
              <cite>公告查询</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">

                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="tips_title"  placeholder="请输入公告标题" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>

                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form" id="table-demo" lay-filter="table-demo"></table>
                </div>
                <script type="text/html" id="barDemo">
                    <a class="layui-btn layui-btn-xs" lay-event="edit">查看详情</a>
                </script>
            </div>
        </div>
    </div>
</div>

<%-- 弹出层 --%>
<div style="display: none; padding: 20px;" id="newsInfoLayer">

</div>

</body>
<script>
    layui.use(['laydate','form', 'table'], function(){
        var laydate = layui.laydate;
        var  form = layui.form;
        var table = layui.table;

        table.render({
            elem: "#table-demo",
            url: './tips?method=getTipsList',
            id: 'table-search',
            page: true,
            cols:[
                [
                    {field: 'id', title: 'ID', width:100, fixed: 'left'}
                    ,{field: 'tips_title', title: '公告标题', width:120}
                    ,{field: 'tips_content', title: '公告内容', width:180}
                    ,{field: 'tips_date', title: '公告时间', width: 120}
                    ,{title: '操作', toolbar: "#barDemo", width: 180, fixed: 'right'}
                ]
            ]
        });

        // 表格操作项(编辑 + 删除)
        // @author-子墨
        table.on("tool(table-demo)", function(obj){
            var data = obj.data;
            if(obj.event === "edit") {
                $("#newsInfoLayer").empty();
                $("#newsInfoLayer").append(data.tips_content);
                layer.open({
                    title: '公告详情',
                    type: 1,
                    skin: 'layui-layer-rim',
                    area: ['420px', '240px'],
                    content: $("#newsInfoLayer"),
                    maxmin: true,
                    minStack: false, // 最小化不堆叠在左下角
                    id: 'page1'
                })
            }
        });

        // 表单对用户数据搜索
        // @author - 子墨
        form.on('submit(search)', function(data){
            // table-search 对应上方定义table.render({id: 'table-search'})
            table.reload("table-search", {
                url: './tips?method=getTipsList',
                method: 'post',
                where: {
                    tips_title: data.field.tips_title
                },
                page: {
                    curr: 1
                }
            });
            return false;
        });
    });

</script>
</html>
