package com.licai.controller;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 前端控制器，目的拦截请求，指派业务控制器去完成功能请求的处理
 * 类似SpringMVC框架中核心控制器的开发
 * 1. 前端控制器具备的功能
 * （1）拦截请求
 * （2）委托业务控制器去完成请求处理
 * （3）将后台的数据传递给视图，视图有数据用户就能在浏览器中看到数据
 * 2. 如何寻找显示数据页面
 * （1）子控制器返回一个页面的地址
 * 3. 如何将数据传递给页面
 * （1）Servlet中转发技术
 * （2）Ajax技术
 * @author caleb
 *
 */
abstract class BaseController extends HttpServlet {

    private static final long serialVersionUID = 174652088543895745L;

    public static String PROJECT_REDIRECT; //项目做重定向的根路径，避免重定向时候出现http404错误
    public static String PROJECT_FORWARD; //项目做转发的根路径，避免在转发的过程中找不到页面

    @Override
    public void init() throws ServletException { //Servlet初始化会被调用方法
        PROJECT_REDIRECT = "r:" + getServletContext().getContextPath();
        PROJECT_FORWARD = "f:";
    }

    /**
     * service包含doPost和doGet
     * 凡事POST和GET请求都会走这个方法
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); //解决所有子控制器的乱码问题

        String methodName = request.getParameter("method"); //方法名称，等会利用请求去指定方法名称
        Class clazz = this.getClass(); //子类的运行时类
        try {
            if (methodName == null) {
                return; //如果方法名称的参数不存在则终止程序
            }
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class); //子类的方法名称
            String path = (String) method.invoke(this, request, response); //运行子类的方法名称

            //得到子控制器返回的页面地址，需要完成下面如下几个响应页面的处理
            //1.Ajax
            //2.页面跳转（重定向），页面地址定义一个以 r:/html/default/edit.html 开头前缀
            //3.转发，页面地址定义一个以 f:/html/default/edit.html 开头前缀
            if (path == null) { //Ajax技术
                return;
            }

            if ("r:".equals(path.substring(0,2))) {
                response.sendRedirect(path.substring(2)); //重定向
            } else if ("f:".equals(path.substring(0, 2))) {
                request.getRequestDispatcher(path.substring(2)).forward(request, response); //转发，可以带数据给前端页面
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过ajax返回json数据
     * @param object
     * @param response
     * @throws IOException
     */
    protected void getJson(Object object, HttpServletResponse response) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String json = JSON.toJSONString(object);
        out.println(json);
        out.flush();
        out.close();
    }

}
