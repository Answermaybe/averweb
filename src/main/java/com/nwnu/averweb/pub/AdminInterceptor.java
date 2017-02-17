package com.nwnu.averweb.pub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor {

	public AdminInterceptor() {
		// TODO Auto-generated constructor stub
	}

	//全部完成后处理
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		//System.out.print("调用**********afterCompletion*********");
	}
   //拦截后处理
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		//System.out.print("调用**********postHandle*********");
	}
	//拦截前处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// TODO Auto-generated method stub
		//获取请求的URL  
       // String url = request.getRequestURI();  
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制  
        /*if(url.indexOf("login.action")>=0){  
            return true;  
        } */ 
        //获取Session  
		System.out.print("调用**********preHandle***************");
       Object sessionObject = request.getSession().getAttribute("loginedUser");       
        if(sessionObject != null){  
        //	System.out.print("获取登录用户*****"+((SysUser)sessionObject).getEmail()+"**********");
            return true;  
        }  
        //不符合条件的，跳转到登录界面 
        response.sendRedirect("/Home/login.do");  
        return false;  
	}

}
