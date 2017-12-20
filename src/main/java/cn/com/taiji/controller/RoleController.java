package cn.com.taiji.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.taiji.dto.RoleDto;
import cn.com.taiji.dto.UserDto;
import cn.com.taiji.service.RoleService;
import cn.com.taiji.service.UserService;

@Controller
@RequestMapping("/role")
public class RoleController {

	private static final Logger log = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	RoleService  roleService;
	@Autowired
	ObjectMapper objectMapper;
	
	
	@RequestMapping(value="getRoleList",method=RequestMethod.POST)
	@ResponseBody
	public Map getUser(String models) {
		/*
		//int offset,int limit,String userName,String userNumber
		Map map = null ;
		try {
			map = userService.getPage(offset, limit, null, null);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
		*/
		//System.out.println(models);
		Map searchParameters = new HashMap();
		if (models != null && models.length() > 0) {
			try {
				searchParameters = objectMapper.readValue(models,
						new TypeReference<Map>() {
						});
			} catch (JsonParseException e) {
				log.error("JsonParseException{}:", e.getMessage());
			} catch (JsonMappingException e) {
				log.error("JsonMappingException{}:", e.getMessage());
			} catch (IOException e) {
				log.error("IOException{}:", e.getMessage());
			}
		}
		Map page = null;
		try {
			page = roleService.getPage(searchParameters);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	
	}
	
	/**
	 * @Description: 新增和修改role信息
	 * @return String  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月17日
	 */
	@RequestMapping("addRole")
	public String addRole(Model model) {
		RoleDto rd = new RoleDto();
		model.addAttribute("rd", rd);
		return "addRole";
		
	}
	
	@RequestMapping("updateRole")
	public String updateRole(String id,Model model) {
		RoleDto rd = null;
		try {
			rd = roleService.getOneRole(id);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("rd", rd);
		System.out.println(rd);
		return "addRole";
		
	}
	@RequestMapping("deleteRole")
	public String deleteRole(String id,Model model) {
		roleService.deleteRole(id);
		model.addAttribute("result", "用户删除成功！");
		return "deleteRole";
	}

	
	@RequestMapping("addRoleByForm")
	@ResponseBody
	public String addRoleByForm(String data) {
		ObjectMapper mapper = new ObjectMapper();
		
		Map map = null;
		try {
			map = mapper.readValue(data, Map.class);
			RoleDto md = new RoleDto();
			if(map.get("id")!=null && !("").equals(map.get("id"))) {
				md.setId(map.get("id").toString());
			}
			md.setRoleName(map.get("roleName").toString());
			md.setRoleDesc(map.get("roleDesc").toString());
			try {
				roleService.saveAndFlushRole(md);
				
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return "1";
		
	}
}
