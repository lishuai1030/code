package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
	//测试方法
	public void hello();
	//根据用户id获得权限列表
	public List<TPermission> queryByUid(int id);
	//根据原有权限整理菜单
	public List<TPermission> getMenu(List<TPermission> pers);
	//按条件查询
	public List<TUser> queryByCon(QueryUser qu);
	//批量删除
	public void deleteByIds(int [] ids);
	//修改
	public void updateUser(TUser user);
	//新增
	public int insertUser(TUser user) throws AppException;
	//分页查询
	public List<TUser> queryByPage(int page,QueryUser query);
	//计算总页数
	public int queryCount(QueryUser query);
	//删除
	public void deleteById(int id);
}
