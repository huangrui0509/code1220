package cn.com.taiji.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.taiji.dto.UserDto;
import cn.com.taiji.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	UserService  userService;
	@Autowired
	ObjectMapper objectMapper;
	
	@GetMapping("getpage")
	public String getPage() {
		return "userList";
	}
	
	@RequestMapping(value="getUserList",method=RequestMethod.POST)
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
			page = userService.getPage(searchParameters);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	
	}
	
	@RequestMapping("addUser")
	public String toAddUser(Model model) {
		UserDto ud = new UserDto();
		model.addAttribute("ud", ud);
		return "addUser";
	}
	
	
	@RequestMapping("updateUser")
	public String updateUser(String id,Model model) {
		System.out.println("id="+id);
		UserDto ud = null;
		try {
			ud = userService.getOneUser(id);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("ud", ud);
		System.out.println(ud);
		return "addUser";
	}
	
	
	@RequestMapping("addUserByForm")
	@ResponseBody
	public String addUserByForm(String data) {
		
		System.out.println(data);
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			Map map = mapper.readValue(data, Map.class);
			UserDto ud = new UserDto();
			if(map.get("id")!=null && !("").equals(map.get("id"))) {
				ud.setId(map.get("id").toString());
			}
			ud.setUserNumber(map.get("userNumber").toString());
			ud.setUserName(map.get("userName").toString());
			ud.setAge(Integer.parseInt(map.get("age").toString()));
			ud.setGender(map.get("gender").toString());
			userService.saveAndFlushUser(ud);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "1122";

	}
	//删除
	@RequestMapping("deleteUser")
	public String deleteUser(String id,Model model) {
		
		userService.removeUser(id);
		model.addAttribute("result", "用户删除成功！");
		return "deleteUser";
		
	}
	
}
