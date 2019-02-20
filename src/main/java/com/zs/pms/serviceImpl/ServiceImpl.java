package com.zs.pms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.Constants;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryUser;
@Service
@Transactional
public class ServiceImpl  implements UserService{
	@Autowired
	UserDao dao;
	
	
	@Override
	public void hello() {
		// TODO Auto-generated method stub
		System.out.println("hello sping");
	}

	@Override
	public List<TPermission> queryByUid(int id) {
		// TODO Auto-generated method stub
		return dao.queryByUid(id);
	}

	@Override
	public List<TPermission> getMenu(List<TPermission> pers) {
		//创建新容器
		List<TPermission> list=new ArrayList<>();
		//遍历权限列表
		for(TPermission per:pers) {
			//一级菜单
			if (per.getLev()==1) {
				//加载一级菜单下的二级菜单
				for(TPermission per2:pers) {
					//二级权限的上级id等于一级权限的id
					if (per2.getPid()==per.getId()) {
						//添加子权限
						per.addChlid(per2);
					}
				}
				//加到新的集合中
				list.add(per);
			}
		}
		return list;
	}

	@Override
	public List<TUser> queryByCon(QueryUser qu) {
		// TODO Auto-generated method stub
		return dao.queryByCon(qu);
	}

	@Override
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		dao.deleteByIds(ids);
	}

	@Override
	public void updateUser(TUser user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}

	@Override
	//改方法只要有异常就回滚事务
	@Transactional(rollbackFor=Exception.class)
	public int insertUser(TUser user) throws AppException{
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1001,"登录名不能为admin");
		}
		MD5 md5=new MD5();
		user.setPassword(md5.getMD5ofStr(user.getPassword()));
		dao.insertUser(user);
		return user.getId();
		
	}

	@Override
	public List<TUser> queryByPage(int page, QueryUser query) {
		//通过当前页面计算起始数和截止数
		//起始数从一开始
		int start=(page-1)*Constants.PAGECONT+1;
		//截止
		int end=page*Constants.PAGECONT;
		
		query.setStart(start);
		query.setEnd(end);
		return dao.queryByPage(query);
	}
	
	
	//计算总条数
	@Override
	public int queryCount(QueryUser query) {
		// TODO Auto-generated method stub
		//同过总条数计算获得zong页数
		int cont=dao.queryCount(query);
		//能整除
		if (cont%Constants.PAGECONT==0) {
			return cont/Constants.PAGECONT;
		}else {
			return cont/Constants.PAGECONT+1;
		}
		
		
	}
	//删除
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	
	

}
