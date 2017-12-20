package cn.com.taiji.service;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.taiji.Application;
import cn.com.taiji.dto.MenuDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class MenuServiceTest {

	@Autowired
	MenuService menuService;
	
	@Test
	public void saveAndFlushMenu() {
		MenuDto menuDto = new MenuDto();
		menuDto.setId("123");
		menuDto.setMenuName("ss");
		menuDto.setUrl("/rooo");
		menuDto.setDescribes("ddd");
		menuDto.setLevels("2");
		
		
		try {
			menuService.saveAndFlushMenu(menuDto);
			System.out.println("saveAndFlushMenu()");
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteMenu() {
		MenuDto menuDto = new MenuDto();
		menuDto.setId("123");
		try {
			menuService.deleteMenu(menuDto);
			System.out.println("删除成功");
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
