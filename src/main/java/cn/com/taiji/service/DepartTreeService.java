package cn.com.taiji.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.taiji.domain.Department;
import cn.com.taiji.dto.DepartTreeDto;
import cn.com.taiji.dto.DepartmentDto;

@Service
public class DepartTreeService {

	@Autowired
	DepartmentService departmentService;
	@Autowired
	ObjectMapper objectMapper;
	
	public String getDepartTree(String id) throws IllegalAccessException, InvocationTargetException, JsonProcessingException {
		DepartmentDto dd = departmentService.findOneDepart(id);
		//根部门
		Department depart = new Department();
		BeanUtils.copyProperties(depart, dd);
		DepartTreeDto dtd = new DepartTreeDto();
		//赋值
		dtd.setBackColor("#FFFFFF");
		dtd.setColor("#000000");
		dtd.setHref("getValue?departId="+depart.getId());
		dtd.setIcon("glyphicon glyphicon-stop");
		dtd.setSelectedIcon("glyphicon glyphicon-ok");
		dtd.setSelectable(true);
		dtd.setText(depart.getDepartName());
//		Map<String ,Boolean> state = new HashMap();
//		state.put("checked", true);
//		state.put("disabled", true);
//		state.put("expanded", true);
//		state.put("selected", true);
//		dtd.setState(state);
		List<String> tags = new ArrayList();
		tags.add("available");
		dtd.setTags(tags);
		List<DepartTreeDto> nodes = new ArrayList();
		List<Department> dtdList = depart.getDepartments();
		for (Department department : dtdList) {
			if(!"-".equals(department.getDepartName())) {
				DepartTreeDto dtd2 = new DepartTreeDto();
				dtd2.setText(department.getDepartName());
				dtd2.setHref("getValue?departId="+department.getId());
				dtd2.setSelectedIcon("glyphicon glyphicon-ok");
				dtd2.setSelectable(true);
				nodes.add(dtd2);
			}
			

		}
		
		dtd.setNodes(nodes);
		String dtdStr = objectMapper.writeValueAsString(dtd);
		return dtdStr;
		
	}
	
}
