package cn.com.taiji.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.taiji.Application;
import cn.com.taiji.domain.Role;
import cn.com.taiji.dto.RoleDto;
import cn.com.taiji.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class RoleServiceTest {

	@Autowired
	RoleService roleService;
	
	/**
	 * @Description: 测试RoleService的saveAndFlushRole方法  返回void
	 * @throws
	 * @author lenovo
	 * @date 2017年12月9日
	 */
	@Test
	public void saveAndFlushRole() {
		RoleDto rd = new RoleDto();
		rd.setId("1");
		try {
			Role role = roleService.saveAndFlushRole(rd);
			System.out.println(role.toString());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 测试RoleService的findAllRole方法  返回void
	 * @throws
	 * @author lenovo
	 * @date 2017年12月9日
	 */
	public void findAllRole() {
		try {
			List<RoleDto> findAllRole = roleService.findAllRole();
			System.out.println(findAllRole.get(0).toString());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 测试RoleService的getUserList方法  返回void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月10日
	 */
	@Test
	public void getUserList() {
		RoleDto roleDto = new RoleDto();
		roleDto.setId("1");
		try {
			List<UserDto> userList = roleService.getUserList(roleDto);
			System.out.println(userList.toString());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
