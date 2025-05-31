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
                    <span class="x-red">*</span>账号</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_username" name="username" required="" lay-verify="nikename"
                           autocomplete="off" class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>将会成为您唯一的登入名
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">
                    <span class="x-red">*</span>邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_email" name="email" required="" lay-verify="email" autocomplete="off"
                           class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_tel" class="layui-form-label">
                    <span class="x-red">*</span>电话</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_tel" name="tel" required="" lay-verify="tel" autocomplete="off"
                           class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_img" class="layui-form-label">
                    <span class="x-red">*</span>头像</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_img" name="img" required="" lay-verify="img" autocomplete="off"
                           class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>输入头像地址
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_desc" class="layui-form-label">
                    <span class="x-red">*</span>描述</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_desc" name="desc" required="" lay-verify="desc" autocomplete="off"
                           class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>输入简短描述
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">
                    <span class="x-red">*</span>密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="L_pass" name="pass" required="" lay-verify="pass" autocomplete="off"
                           class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">
                    <span class="x-red">*</span>确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                           autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="add" lay-submit="">添加</button>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    layui.use(['form','layer'], function() {
        var form = layui.form;
        var layer = layui.layer;

        //自定义验证规则
        form.verify({
            nikename: function (value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            },
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });

        form.on('submit(add)', function (data) {
            $.ajax({
                url: './user',
                type: 'post',
                data: {'method':'addUser', 'username':data.field.username, 'pass':data.field.pass, 'repass':data.field.repass, 'email':data.field.email, 'tel':data.field.tel, 'img':data.field.img, 'desc':data.field.desc},
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