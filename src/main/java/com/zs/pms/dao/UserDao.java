package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserDao {
	//根据用户id获得权限列表
	public List<TPermission> queryByUid(int id);
	//按条件查询
	public List<TUser> queryByCon(QueryUser qu);
	//批量删除
	public void deleteByIds(int [] ids);
	//修改
	public void updateUser(TUser user);
	//新增
	public int insertUser(TUser user);
	//分页查询
	public List<TUser> queryByPage(QueryUser query);
	//获得总条数
	public int queryCount(QueryUser query);
	//删除
	public void deleteById(int id);
	//根据主键读取
	public TUser queryById(int id);
}
