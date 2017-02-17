package com.nwnu.averweb.controller;

import com.nwnu.averweb.model.SysUser;
import com.nwnu.averweb.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "Home")
public class HomeController {

	@Autowired
	private SysUserService sysuserService;

	@RequestMapping(value = "main")
	public String main(HttpSession session) {
		return "Home/main";
	}

	@RequestMapping(value = "login")
	public String login() {
		return "Home/login";
	}

	@RequestMapping(value = "logout")
	public String logout(HttpSession session) throws Exception {
		// 清除Session
		session.invalidate();		
		System.out.print("访问了logout");
		return "redirect:login.do";
	}

	/***
	 * 
	 * 
	 * @param sysprivilege
	 * @return
	 */
	@RequestMapping(value = "loginIn", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginIn(SysUser sysuser, HttpSession httpsession) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (sysuser.getEmail() == null || sysuser.getEmail().equals("")) {
			result.put("IsSuccess", false);
			result.put("Message", "用户名不能为空");
			return result;
		}
		if (sysuser.getPwd() == null || sysuser.getPwd().equals("")) {
			result.put("IsSuccess", false);
			result.put("Message", "密码不能为空");
			return result;
		}
		SysUser loginUser = sysuserService.selectOne(sysuser);
		//System.out.print("登录参数" + sysuser.getEmail() + "**************"
		//		+ sysuser.getPwd() + "******");
		if (loginUser != null) {
			System.out.print(loginUser.getEmail() + "**************");
			httpsession.setAttribute("loginedUser", loginUser);
			// return "redirect:main.do";
			result.put("IsSuccess", true);
			result.put("Message", "登录成功");
		} else {
		//	System.out.print("没查到**************");
			result.put("IsSuccess", false);
			result.put("Message", "用户名或密码错误");

		}
		return result;

	}
}
