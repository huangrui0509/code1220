package cn.com.taiji.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.taiji.Application;
import cn.com.taiji.dto.DepartmentDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class DepartmentServiceTest {

	@Autowired
	DepartmentService  departmentService;
	
	@Test
	public void findOneDepart() {
		DepartmentDto depart = departmentService.findOneDepart("1");
		System.out.println(depart);
	}
	
	
	@Test
	public void findAllDepart() {
		List<DepartmentDto> allDepart = departmentService.findAllDepart();
		System.out.println(allDepart);
	}
}
