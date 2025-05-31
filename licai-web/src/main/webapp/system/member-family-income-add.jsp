<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/4 0004
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="../css/font.css">
    <link rel="stylesheet" href="../css/xadmin.css">
    <script type="text/javascript" src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]--></head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <input type="hidden" id="id" name="id">
            <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">
                    <span class="x-red">*</span>家庭成员</label>
                <div class="layui-input-inline layui-show-xs-block">
                    <select name="username" lay-verify="required" id="L_username">

                    </select>
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">
                    <span class="x-red">*</span>收入时间</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="text"  autocomplete="off" name="f_date" id="start" lay-verify="required">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">
                    <span class="x-red">*</span>收入类型</label>
                <div class="layui-input-inline">
                    <select name="f_name" lay-verify="required" id="L_type_income">
                        <option>工资收入</option>
                        <option>理财收入</option>
                        <option>其他</option>
                    </select>
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">
                    <span class="x-red">*</span>收入金额</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_income" name="f_income" lay-verify="required"
                           autocomplete="off" class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_username" class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="add" lay-submit="">添加</button>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    layui.use(['form','layer', 'laydate'], function() {
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        form.on('submit(add)', function (data) {
            console.log(data.field);
            $.ajax({
                url: './member',
                type: 'post',
                data: {'method':'addMemberFamilyIncome', 'username':data.field.username, "f_name": data.field.f_name, 'f_income': data.field.f_income, "f_date": data.field.f_date},
                dataType: 'json',
                success: function(res) {
                    if(res.code === 200) {
                        layer.msg("添加成功", {icon: 6}, function() {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            // 关闭当前frame
                            parent.layer.close(index);
                            // 父页面刷新
                            window.parent.location.reload();
                        })
                    } else {
                        layer.msg(res.msg, {icon: 6}, function() {
                        })
                    }
                },
                error: function() {
                    layer.msg("服务器异常...");
                }
            });
            return false;
        });
    });

    $(function() {
        init();
    });

    function init() {
        $.ajax({
            url: './member',
            type: 'post',
            data: {"method": 'memberFamilyToSelect'},
            dataType: 'json',
            success: function(res) {
                if(res.code === 200) {
                    $.each(res.data, function(i, v) {
                        var str = '<option value="'+ v.id +'">'+v.f_name+'</option>';
                        $("#L_username").append(str);
                    });
                }
            },
            async: false
        });

        layui.use('form', function() {
            var form = layui.form;
            form.render();
        });
    }

</script>
</html>