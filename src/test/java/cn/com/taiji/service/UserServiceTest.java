package cn.com.taiji.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.taiji.Application;
import cn.com.taiji.domain.User;
import cn.com.taiji.dto.RoleDto;
import cn.com.taiji.dto.UserDto;

/**        
 * 类名称：UserServiceTest   
 * 类描述：   
 * 创建人：huangrui   
 * 创建时间：2017年12月9日 下午1:04:34 
 * @version      
 */ 
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	
	/**
	 * @Description: 测试userservice的saveAndFlush方法 void  
	 * @throws
	 * @author huangrui
	 * @date 2017年12月9日
	 */
	@Test
	public void saveAndFlushUser() {
		UserDto ud = new UserDto();
		//ud.setId("3456");
		User user;
		try {
			userService.saveAndFlushUser(ud);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @Description: 测试userservice的findAllUser方法 返回void 
	 * @throws
	 * @author lenovo
	 * @date 2017年12月9日
	 */
	@Test
	public void findAllUser() {
		try {
			List<UserDto> findAllUser = userService.findAllUser();
			System.out.println(findAllUser.get(0).toString());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @Description: 测试userService的setRolesToUser方法 返回值为void
	 * @throws
	 * @author huangrui
	 * @date 2017年12月9日
	 */
	@Test
	public void setRolesToUser() {
		UserDto ud = new UserDto();
		ud.setId("3456");
		List<RoleDto> rdList = new ArrayList<RoleDto>();
		RoleDto rd = new RoleDto();
		rd.setId("1");
		rd.setRoleName("USER");
		rdList.add(rd);
		try {
			User user = userService.setRolesToUser(ud, rdList);
			System.out.println(user.getRoles());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getPage() {
		
	}
	
	
}
