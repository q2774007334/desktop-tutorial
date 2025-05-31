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
            <a href="javascript:;">管理员</a>
            <a>
              <cite>管理员列表</cite></a>
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
                            <input type="text" name="username"  placeholder="请输入用户账号" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-header">
                    <button class="layui-btn" onclick="xadmin.open('添加用户','./admin-add.jsp',600,500)"><i class="layui-icon"></i>添加</button>
                </div>
                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form" id="table-demo" lay-filter="table-demo"></table>
                </div>
                <script type="text/html" id="barDemo">
                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    {{# if(d.u_stop == 1){ }}
                    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="stop" data-id="{{d.id}}" data-use="{{d.u_stop}}" onclick="stopUser(this)">已启用</a>
                    {{# }else{  }}
                    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="stop" data-id="{{d.id}}" data-use="{{d.u_stop}}" onclick="stopUser(this)">已禁用</a>
                    {{# } }}
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
            url: './user?method=findAllAdmin',
            id: 'table-search',
            page: true,
            cols:[
                [
                    {field: 'id', title: 'ID', width:100, fixed: 'left'}
                    ,{field: 'u_img', title: '用户头像', width:100, templet: '#imgTpl'}
                    ,{field: 'u_name', title: '用户账号', width:100}
                    ,{field: 'u_tel', title: '用户电话', width:100}
                    ,{field: 'u_email', title: '用户邮箱', width:100}
                    ,{field: 'u_desc', title: '用户描述', width: 177}
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
                       url: "./user",
                       type: "post",
                       data: {"method":"delAdmin", "id":data.id},
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
                var u_name = data.u_name;
                var u_email = data.u_email;
                var u_tel = data.u_tel;
                var u_img = data.u_img
                xadmin.open('编辑','admin-edit.jsp?id='+id+'&u_name='+u_name+'&u_email='+u_email+"&u_tel="+u_tel+"&u_img="+u_img,600,400);
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

        // 表单对用户数据搜索
        // @author - 子墨
        form.on('submit(search)', function(data){
            // table-search 对应上方定义table.render({id: 'table-search'})
            table.reload("table-search", {
                url: './user?method=searchAdmin',
                method: 'post',
                where: {
                    username: data.field.username
                },
                page: {
                    curr: 1
                }
            });
            return false;
        });
    });

    // 禁用与启用用户
    // @author-子墨
    function stopUser(obj) {
        var value = $(obj).data("use");
        var id = $(obj).data("id");
        value = value == 0 ? 1: 0;
        $.ajax({
            url: "./user",
            type: "post",
            data: {"id":id, "use":value, "method":"stopAdmin"},
            dataType: "json",
            success: function(res) {
                if(res.code === 200) {
                    layui.layer.msg("更新成功", {time:500}, function() {
                        switch (value) {
                            case 1:
                                $(obj).html("已启用");
                                $(obj).data("use", value);
                                break;
                            case 0:
                                $(obj).html("已禁用");
                                $(obj).data("use", value);
                                break;
                        }
                    });
                }
            },
            error: function() {
                layui.layer.msg("更新失败");
            }
        });
    }
</script>
</html>
