package cn.com.taiji.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.taiji.Application;
import cn.com.taiji.dto.EmployeeDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;
	/*
	@Test
	public void findOneEmp() {
		EmployeeDto emp = employeeService.findOneEmp("1");
		System.out.println(emp);
	}
	*/
	/**
	 * @Description: 验证查询所有employee对象信息  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	@Test
	public void findAllEmp() {
		List<EmployeeDto> allEmp = employeeService.findAllEmp();
		System.out.println(allEmp);
	}
	
	/**
	 * @Description: 测试新增和修改employee对象信息
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	@Test
	public void insertEmp() {
		EmployeeDto ed = new EmployeeDto();
		employeeService.insertEmp(ed);
	}
	
	/**
	 * @Description: 测试将Excel表中数据导入数据库中
	 * @throws IOException void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	@Test
	public void printExcel() throws IOException {
		Map print = employeeService.print("C:/Users/lenovo/Desktop/实战2/通讯录.xlsx");
		employeeService.initDept(print);
		System.out.println(print);
	}
}
