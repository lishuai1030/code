package com.zs.pms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.DepService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@Controller
public class UsreController {
	@Autowired
	UserService us;
	@Autowired
	DepService ds;
	@RequestMapping("/user/list.do")
	public String list(String page,QueryUser query,ModelMap map) {
		if(page==null) {
			page="1";
		}
		//���ط�ҳ����
		map.addAttribute("LIST", us.queryByPage(Integer.parseInt(page),query));
		//������ҳ��
		map.addAttribute("PAGECONT",us.queryCount(query));
		//��ǰҳ��
		map.addAttribute("PAGE", page);
		//����
		map.addAttribute("QUERY",query);
		return "user/list";
	}
	@RequestMapping("/user/toadd.do")
	public String toAdd(ModelMap map) {
		//����һ������
		map.addAttribute("DLIST", ds.queryByPid(0));
		return "user/add";
	}
	@RequestMapping("/user/add.do")
	public String add(HttpSession session,ModelMap map,TUser user) {
		TUser cu=(TUser)session.getAttribute("CUSER");
		try {
			//װ������
			user.setIsenabled(1);
			user.setCreator(cu.getId());
			user.setPic("");
			us.insertUser(user);
			//��תurl
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("MSG", e.getErrMsg());
			//���÷������ݲ���
			return this.toAdd(map);
		}
		
	}
	@RequestMapping("/user/delete.do")
	public String delete(int id) {
		us.deleteById(id);
		//��תurl
		return "redirect:list.do";
		
	}
	@RequestMapping("/user/deletes.do")
	public String deletes(int [] ids) {
		us.deleteByIds(ids);
		//��תurl
		return "redirect:list.do";
	}
}
