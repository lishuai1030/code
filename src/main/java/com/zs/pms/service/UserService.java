package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
	//���Է���
	public void hello();
	//�����û�id���Ȩ���б�
	public List<TPermission> queryByUid(int id);
	//����ԭ��Ȩ������˵�
	public List<TPermission> getMenu(List<TPermission> pers);
	//��������ѯ
	public List<TUser> queryByCon(QueryUser qu);
	//����ɾ��
	public void deleteByIds(int [] ids);
	//�޸�
	public void updateUser(TUser user);
	//����
	public int insertUser(TUser user) throws AppException;
	//��ҳ��ѯ
	public List<TUser> queryByPage(int page,QueryUser query);
	//������ҳ��
	public int queryCount(QueryUser query);
	//ɾ��
	public void deleteById(int id);
}
