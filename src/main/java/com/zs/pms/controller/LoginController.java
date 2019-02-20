package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.zs.pms.dao.UserDao;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryLogin;
import com.zs.pms.vo.QueryUser;

@Controller // 是一个控制器
public class LoginController {
	@Autowired
	UserService us;
	
	@RequestMapping("/tologin.do")
	// 去登陆页面
	public String toLogin() {

		return "login";
	}

	@RequestMapping("/top.do")
	// 去顶部页面
	public String top() {
		return "top";

	}

	

	@RequestMapping("/right.do")
	// 去左侧页面
	public String right() {
		return "right";

	}

	@RequestMapping("/login.do")
	// 去左侧页面
	public String login(QueryLogin login, HttpSession session, ModelMap model) {
		// 1.验证验证码
		String ocode = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 生成的验证码和页面的验证码不等
		if (!ocode.equals(login.getChkcode())) {
			model.addAttribute("MSG", "验证码输入有误，请重新输入");
			return "login";
		}
		//2.验证账号密码
		//装载数据
		QueryUser query=new QueryUser();
		query.setLoginname(login.getUsername());//登录名
		//MD5加密
		MD5 md5=new MD5();
		query.setPassword(md5.getMD5ofStr(login.getPassword()));//密码
		query.setIsenabled(1);//设置可用
		//返回登录的用户
		List<TUser> users=us.queryByCon(query);
		//登录失败
		if(users==null||users.size()!=1) {
			model.addAttribute("MSG", "用户名或密码输入错误，请重新输入");
			return "login";
		}
		//登录成功  装session 
		session.setAttribute("CUSER",users.get(0));
		return "main";

	}
	@RequestMapping("/left.do")
	public String left(HttpSession session,ModelMap model) {
		//获得当前登录用户
		TUser cu=(TUser)session.getAttribute("CUSER");
		//获得该用户权限列表
		List<TPermission> list1=us.queryByUid(cu.getId());
		//返回菜单
		model.addAttribute("MENU", us.getMenu(list1));
		
		return "left";
		
	}
}
