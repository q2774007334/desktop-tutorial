<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/19 0019
  Time: 10:20
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
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-sm12 layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">最新一周消费数据</div>
                <div class="layui-card-body" style="min-height: 280px;">
                    <div id="main1" class="layui-col-sm12" style="height: 300px;"></div>

                </div>
            </div>
        </div>
        <div class="layui-col-sm12 layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">最新一周消费来源</div>
                <div class="layui-card-body" style="min-height: 280px;">
                    <div id="main3" class="layui-col-sm12" style="height: 300px;"></div>

                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main1'));

    // 最近一周消费数据 - 曲线图 - 异步数据获取
    $.ajax({
        url: 'consume',
        type: 'post',
        data: {'method': 'findConsumeStatistics1'},
        dataType: 'json',
        success: main1,
        error: function () {
            alert('服务器异常');
        }
    });

    function main1(res) {
        var time_data = [];
        var value_data = [];
        $.each(res.data, function(i, v) {
            time_data.push(v.c_date);
            value_data.push(v.c_money);
        });

        // 指定图表的配置项和数据
        var option = {
            grid: {
                top: '5%',
                right: '1%',
                left: '1%',
                bottom: '10%',
                containLabel: true
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                type: 'category',
                data: time_data
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name:'消费额度',
                data: value_data,
                type: 'line',
                smooth: true
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }

    // 基于准备好的dom，初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('main3'));

    // 最近一周消费来源 - 饼图 - 异步数据获取
    $.ajax({
        url: 'consume',
        type: 'post',
        data: {'method': 'findConsumeStatistics2'},
        dataType: 'json',
        success: main2,
        error: function () {
            alert('服务器异常');
        }
    });

    function main2(res) {
        var type_data = [];
        var value_data = [];

        $.each(res.data, function(i, v){
            type_data.push(v.c_type);
            value_data.push({
               value: v.c_money,
               name: v.c_type
            });
        });

        // 指定图表的配置项和数据
        var option = {
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: type_data
            },
            series : [
                {
                    name: '消费来源',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data: value_data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart2.setOption(option);
    }
</script>
</body>
</html>
