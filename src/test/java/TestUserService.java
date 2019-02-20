import java.util.Date;
import java.util.List;

import org.apache.jasper.xmlparser.UCSReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class TestUserService {
	@Autowired
	UserService us;
	
	public void testHello() {
		us.hello();
	}
	
	public void testlogin() {
		List<TPermission> list1=us.queryByUid(1);
		for(TPermission per:list1) {
			System.out.println(per.getPname());
		}
		System.out.println("---------整理后的---------");
		for(TPermission per1:us.getMenu(list1)) {
			System.out.println(per1.getPname());
			for(TPermission per2:per1.getChildren()) {
				System.out.println("----"+per2.getPname());
			}
		}
	}

	public void testQuery() {
		QueryUser query=new QueryUser();
		
		for(TUser user:us.queryByPage(2, query)) {
			System.out.println(user.getId()+"--"+user.getLoginname());
		}
		System.out.println("共"+us.queryCount(query)+"页");
	}
	
	public void testDelete() {
		int [] ids= {3,4};
		us.deleteByIds(ids);
	}
	
	public void testUpdate() {
		TUser user=new TUser();
		user.setId(2);
		user.setUpdator(1);
		user.setRealname("李帅");
		us.updateUser(user);
	}
	@Test
	public void testInsert() {
		TUser user=new TUser();
		user.setLoginname("test434qwq11");
		user.setPassword("qwe12345");
		user.setRealname("李帅");
		user.setSex("女");
		TDep dep=new TDep();
		dep.setId(5);
		user.setDept(dep);
		user.setEmail("12312@qw");
		user.setIsenabled(1);
		user.setCreator(1);
		user.setBirthday(new Date());
		user.setPic("qwe");
		try {
			us.insertUser(user);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
