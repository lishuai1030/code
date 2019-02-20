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
		//����������
		List<TPermission> list=new ArrayList<>();
		//����Ȩ���б�
		for(TPermission per:pers) {
			//һ���˵�
			if (per.getLev()==1) {
				//����һ���˵��µĶ����˵�
				for(TPermission per2:pers) {
					//����Ȩ�޵��ϼ�id����һ��Ȩ�޵�id
					if (per2.getPid()==per.getId()) {
						//�����Ȩ��
						per.addChlid(per2);
					}
				}
				//�ӵ��µļ�����
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
	//�ķ���ֻҪ���쳣�ͻع�����
	@Transactional(rollbackFor=Exception.class)
	public int insertUser(TUser user) throws AppException{
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1001,"��¼������Ϊadmin");
		}
		MD5 md5=new MD5();
		user.setPassword(md5.getMD5ofStr(user.getPassword()));
		dao.insertUser(user);
		return user.getId();
		
	}

	@Override
	public List<TUser> queryByPage(int page, QueryUser query) {
		//ͨ����ǰҳ�������ʼ���ͽ�ֹ��
		//��ʼ����һ��ʼ
		int start=(page-1)*Constants.PAGECONT+1;
		//��ֹ
		int end=page*Constants.PAGECONT;
		
		query.setStart(start);
		query.setEnd(end);
		return dao.queryByPage(query);
	}
	
	
	//����������
	@Override
	public int queryCount(QueryUser query) {
		// TODO Auto-generated method stub
		//ͬ��������������zongҳ��
		int cont=dao.queryCount(query);
		//������
		if (cont%Constants.PAGECONT==0) {
			return cont/Constants.PAGECONT;
		}else {
			return cont/Constants.PAGECONT+1;
		}
		
		
	}
	//ɾ��
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	
	

}
