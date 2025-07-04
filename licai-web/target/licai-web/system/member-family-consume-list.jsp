<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/3 0003
  Time: 17:51
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
            <a href="javascript:;">家庭消费</a>
            <a>
              <cite>消费上限设置</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <button class="layui-btn" onclick="xadmin.open('消费上限设置','./member-family-consume-add.jsp',600,500)"><i class="layui-icon"></i>添加</button>
                </div>
                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form" id="table-demo" lay-filter="table-demo"></table>
                </div>
                <script type="text/html" id="barDemo">
                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                </script>
                <script type="text/html" id="imgTpl">
                    <div style="width: 100%; height: 100%; display: flex; justify-content: center;"><img src="{{d.u_img}}" style="height: 30px; width: 30px; border-radius: 50%;"></div>
                </script>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    layui.use(['laydate','form', 'table'], function(){
        var laydate = layui.laydate;
        var  form = layui.form;
        var table = layui.table;

        table.render({
            elem: "#table-demo",
            url: './member?method=memberFamilyConsumeList&id=${sessionScope.map.id}',
            id: 'table-search',
            page: false,
            cols:[
                [
                    {field: 'id', title: 'ID', width:100, fixed: 'left'}
                    ,{field: 'f_name', title: '消费类型', width:100}
                    ,{field: 'f_income', title: '消费限额', width:100}
                    ,{field: 'u_name', title: '家庭成员', width: 100}
                    ,{field: 'f_date', title: '时间设置', width: 180}
                    ,{title: '操作', toolbar: "#barDemo", width: 277, fixed: 'right'}
                ]
            ]
        });

        // 表格操作项(编辑 + 删除)
        // @author-子墨
        table.on("tool(table-demo)", function(obj){
            var data = obj.data;
            if(obj.event === 'del') {
                layer.confirm('确认要删除吗？',function(index){
                    $.ajax({
                       url: "./member",
                       type: "post",
                       data: {"method":"delMemberFamilyIncome", "id":data.id},
                       dataType: "json",
                       success: function(res) {
                           if(res.code === 200) {
                               layer.msg('删除成功',{icon:1,time:500},function() {
                                    obj.del();
                               });
                           } else {
                               layer.msg("删除失败",{icon:1,time:500});
                           }
                       },
                       error: function() {
                           layer.msg("服务异常...");
                       }
                    });
                });
            } else if(obj.event === "edit") {
                var id = data.id;
                var f_name = data.f_name;
                var u_name = data.u_name;
                var f_date = data.f_date;
                var f_income = data.f_income;
                xadmin.open('编辑','member-family-consume-edit.jsp?id='+id+'&f_name='+f_name+'&u_name='+u_name+'&f_date='+f_date+'&f_income='+f_income,600,500);
            }
        });

        // 监听全选
        form.on('checkbox(checkall)', function(data){
            if(data.elem.checked){
                $('tbody input').prop('checked',true);
            }else{
                $('tbody input').prop('checked',false);
            }
            form.render('checkbox');
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });

    });

</script>
</html>
