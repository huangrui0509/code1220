package cn.com.taiji.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.taiji.domain.Employee;
import cn.com.taiji.dto.DepartmentDto;
import cn.com.taiji.service.DepartTreeService;
import cn.com.taiji.service.DepartmentService;

@Controller
@RequestMapping("/tree")
public class DepartTreeController {

	@Autowired
	DepartTreeService departTreeService;
	
	@Autowired
	DepartmentService departmentService;
	@Autowired
	ObjectMapper objectMapper;
	
	@RequestMapping(value="/showTree",method=RequestMethod.POST)
	@ResponseBody
	public String showTree() {
		List<String> list  = new ArrayList();
		try {
			
			String departTree = departTreeService.getDepartTree("b2707388d1cf42ffa2ee6dd7eb1c1dbd");
			list.add(departTree);
				
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list);
		return list.toString();
	}
	
	/**
	 * @Description: 获取前台返回的id，返回json格式的employee对象数据
	 * @param departId
	 * @return
	 * @throws JsonProcessingException String  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	@RequestMapping(value="/getValue")
	@ResponseBody
	public String getValue(String departId) throws JsonProcessingException {
		DepartmentDto depart = departmentService.findOneDepart(departId);
		System.out.println(depart.getDepartName());
		List<Employee> employees = depart.getEmployees();
		System.out.println(employees);
		String empsStr = objectMapper.writeValueAsString(employees);
		return empsStr;
	}
	
}
