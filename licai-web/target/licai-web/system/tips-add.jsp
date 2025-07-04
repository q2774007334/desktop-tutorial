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
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class="x-red">*</span>公告标题</label>
                <div class="layui-input-inline">
                    <input type="text" name="tips_title" lay-verify="required" autocomplete="off"
                           class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class="x-red">*</span>公告时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="tips_date" id="tips_date" lay-verify="required" autocomplete="off"
                           class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class="x-red">*</span>公告内容</label>
                <div class="layui-input-block">
                    <textarea class="layui-textarea" name="tips_content" lay-verify="required"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
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

        laydate.render({
            elem: '#tips_date'
        })

        form.on('submit(add)', function (data) {
            $.ajax({
                url: './tips',
                type: 'post',
                data: {'method':'addTips', 'tips_title':data.field.tips_title, 'tips_content':data.field.tips_content, 'tips_date':data.field.tips_date},
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

</script>
</html>
