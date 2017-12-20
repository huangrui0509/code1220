package cn.com.taiji.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





/**        
 * 类名称：LoginController   
 * 类描述：   
 * 创建人：lenovo   
 * 创建时间：2017年12月20日 下午1:46:45 
 * @version      
 */ 
@Controller
public class LoginController {

	
	/**
	 * @Description: 跳转登录页面
	 * @return String  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String toLogin() {
		
		return "login";
	}
	
	
	/**
	 * @Description: 用户登录成功后实现页面的自动跳转
	 * @param username
	 * @param password
	 * @return String  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	//@RequestMapping("/")
	@RequestMapping("/home")
	public String login(String username,String password) {
		System.out.println(username+"--"+password);
		//return "userList";
		//return "roleShow";
		return "menuShow";
		//return "departtree";
	}
	
}
