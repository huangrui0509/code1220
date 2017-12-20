package cn.com.taiji.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.taiji.dto.MenuDto;
import cn.com.taiji.dto.UserDto;
import cn.com.taiji.service.MenuService;


@Controller
@RequestMapping("/menu")
public class MenuCntroller {

	private static final Logger log = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	MenuService  menuService;
	@Autowired
	ObjectMapper objectMapper;
	
	/**
	 * @Description: 获取menu的map集合
	 * @param models
	 * @return Map  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月15日
	 */
	@RequestMapping(value="getMenuList",method=RequestMethod.POST)
	@ResponseBody
	public Map getUser(String models) {
		
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
			page = menuService.getPage(searchParameters);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	
	}
	

	/**
	 * @Description: 跳转新增menu的addMenu.html页面
	 * @param model
	 * @return String  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	@RequestMapping("addMenu")
	public String toAddMenu(Model model) {
		MenuDto md = new MenuDto();
		model.addAttribute("md", md);
		return "addMenu";
	}
	
	
	/**
	 * @Description: 实现菜单信息的修改
	 * @param id
	 * @param model
	 * @return String  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	@RequestMapping("updateMenu")
	public String updateMenu(String id,Model model) {
		System.out.println("id="+id);
		MenuDto md = null;
		try {
			md = menuService.getOneMenu(id);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("md", md);
		System.out.println(md);
		return "addMenu";
	}
	
	@RequestMapping("addMenuByForm")
	@ResponseBody
	public String addMenuByForm(String data) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			Map map = mapper.readValue(data, Map.class);
			MenuDto md = new MenuDto();
			if(map.get("id")!=null && !("").equals(map.get("id"))) {
				md.setId(map.get("id").toString());
			}
			md.setMenuName(map.get("menuName").toString());
			md.setDescribes(map.get("describes").toString());
			md.setUrl(map.get("url").toString());
			md.setLevels(map.get("levels").toString());
			md.setParentId(map.get("parentId").toString());
			menuService.saveAndFlushMenu(md);
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
	
	
	@RequestMapping("deleteMenu")
	public String deleteRole(String id,Model model) {
		menuService.deleteMenu(id);
		model.addAttribute("result", "菜单删除成功！");
		return "deleteRole";
	}
}
