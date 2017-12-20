package cn.com.taiji.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.com.taiji.domain.Role;
import cn.com.taiji.domain.User;
import cn.com.taiji.dto.RoleDto;
import cn.com.taiji.dto.UserDto;
import cn.com.taiji.repository.UserRepository;




/**        
 * 类名称：UserService   
 * 类描述：   
 * 创建人：huangrui   
 * 创建时间：2017年12月9日 下午1:14:04 
 * @version      
 */ 
@Service
@SuppressWarnings(value="all")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	
	/**
	 * @Description: 新增和修改user对象
	 * @param userDto
	 * @return User  
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws
	 * @author huangrui
	 * @date 2017年12月8日
	 */
	
	public void saveAndFlushUser(UserDto userDto) throws IllegalAccessException, InvocationTargetException {
		
		Calendar now = Calendar.getInstance();
		UserDetails userDetails = null;
		if(SecurityContextHolder.getContext()!=null) {
			userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
		}
		User user = new User();
		BeanUtils.copyProperties(user,userDto);
		
		List<User> userList = userRepository.findAll();
		if(user.getId()==null) {
			user.setId(UUID.randomUUID().toString().replace("-", ""));
			user.setCreateTime(now.getTime());
			user.setCreatePerson(userDetails.getUsername());
			user.setStatus(1);
		}else {
			for (User user2 : userList) {
				if((user2.getId()).equals(user.getId())) {
					user.setUpdateTime(now.getTime());
					user.setUpdatePerson(userDetails.getUsername());
				}else {
					user.setCreateTime(now.getTime());
					user.setCreatePerson(userDetails.getUsername());
				}
			}
		}
		
		
		userRepository.saveAndFlush(user);
		
	}
	
	


	/**
	 * @Description: 从数据库获取User对象数据的List集合，返回List<UserDto>
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException List<UserDto>  
	 * @throws
	 * @author huangrui
	 * @date 2017年12月9日
	 */
	public List<UserDto> findAllUser() throws IllegalAccessException, InvocationTargetException {
		List<User> userList = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		for (User user : userList) {
			UserDto ud = new UserDto();
			BeanUtils.copyProperties(ud, user);
			userDtoList.add(ud);
		}
		return userDtoList;
		
	}
	
	
	/**
	 * @Description: 逻辑删除user，修改status为0
	 * @param userDto
	 * @return
	 * @throws
	 * @author hunagrui
	 * @date 2017年12月9日
	 */
	public void removeUser(String id) {
		
		User user = userRepository.findOne(id);
		user.setStatus(0);
		userRepository.saveAndFlush(user);
		System.out.println(id+"--------------"+user.getId()+user.getStatus());
	}
	
	/**
	 * @Description: 给user对象添加role（角色）返回值user对象
	 * @param ud
	 * @param roleDtoList
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException User  
	 * @throws
	 * @author huangrui
	 * @date 2017年12月9日
	 */
	public User setRolesToUser(UserDto ud,List<RoleDto> roleDtoList) throws IllegalAccessException, InvocationTargetException {
		User user = new User();
		BeanUtils.copyProperties(user,ud);
		List<Role> roleList = new ArrayList<Role>();
		for (RoleDto rd : roleDtoList) {
			Role role = new Role();
			BeanUtils.copyProperties(role,rd);
			roleList.add(role);
		}
		user.setRoles(roleList);
		return user;
		
	}
	
	/**
	 * @Description: 用户列表查询，返回Map
	 * @param page
	 * @param pageSize
	 * @param orderMaps
	 * @param filters
	 * @return Map  
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws
	 * @author lenovo
	 * @date 2017年12月11日
	 */
	public Map getPage(Map searchParameters) throws IllegalAccessException, InvocationTargetException {
		
		Map map = new HashMap();
		int page = 0;
		int pageSize = 10;
		Page<User> pageContent;
		if (searchParameters != null && searchParameters.size() > 0
				&& searchParameters.get("page") != null) {
			page = Integer.parseInt(searchParameters.get("page").toString()) - 1;
		}
		if (searchParameters != null && searchParameters.size() > 0
				&& searchParameters.get("pageSize") != null) {
			pageSize = Integer.parseInt(searchParameters.get("pageSize")
					.toString());
		}
		if (pageSize < 1)
			pageSize = 1;
		if (pageSize > 100)
			pageSize = 100;
		List<Map> orderMaps = (List<Map>) searchParameters.get("sort");
		List<Order> orders = new ArrayList<Order>();
		if (orderMaps != null) {
			for (Map m : orderMaps) {
				if (m.get("field") == null)
					continue;
				String field = m.get("field").toString();
				if (!StringUtils.isEmpty(field)) {
					String dir = m.get("dir").toString();
					if ("DESC".equalsIgnoreCase(dir)) {
						orders.add(new Order(Direction.DESC, field));
					} else {
						orders.add(new Order(Direction.ASC, field));
					}
				}
			}
		}
		PageRequest pageable;
		if (orders.size() > 0) {
			pageable = new PageRequest(page, pageSize, new Sort(orders));
		} else {
			Sort s = new Sort(Direction.ASC, "id");
			pageable = new PageRequest(page, pageSize, s);
		}
		
		Map filter = (Map)searchParameters.get("filter");
		if (filter != null) {
			final List<Map> filters = (List<Map>) filter.get("filters");
			Specification<User> spec = new Specification<User>() {
				@Override
				public Predicate toPredicate(Root<User> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> pl = new ArrayList<Predicate>();
					for (Map key : filters) {
						
						String field = key.get("field").toString().trim();
						String value = key.get("value").toString().trim();
						/*
						if ("enabled".equalsIgnoreCase(field)) {
							if ("true".equalsIgnoreCase(value)) {
								pl.add(cb.equal(root.get(field), true));
							} else if ("false".equalsIgnoreCase(value)) {
								pl.add(cb.equal(root.get(field), false));
							}
						} 
						
						*/
						if ("userName".equalsIgnoreCase(field)) {
							if (value.length() > 0)
								pl.add(cb.like(root.<String> get(field), value + "%"));
						} 
						if ("userNumber".equalsIgnoreCase(field)) {
							if (value.length() > 0)
								pl.add(cb.like(root.get(field), value));
						}
						pl.add(cb.equal(root.get("status"), 1));
					}
					return cb.and(pl.toArray(new Predicate[0]));
				}
			};
			pageContent = userRepository.findAll(spec, pageable);
			
			
		} else {
			pageContent = userRepository.findAll(pageable);
		}
		map.put("total", pageContent.getTotalElements());
		map.put("users", accountPage2Dto(pageContent));
		
		return map;
	}


	/**
	 * @Description: 简要进行方法说明，并对基础数据类型的参数和返回值加以说明
	 * @param pageContent
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException List<UserDto>  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月11日
	 */
	private List<UserDto> accountPage2Dto(Page<User> pageContent) throws IllegalAccessException, InvocationTargetException {
		List<User> users = pageContent.getContent();
		List<UserDto> userDtos =new  ArrayList();
		for (User user : users) {
			UserDto ud = new UserDto();
			BeanUtils.copyProperties(ud, user);
			userDtos.add(ud);
		}
		return userDtos;
	}
	
	public UserDto userToDto(User user) throws IllegalAccessException, InvocationTargetException {
		UserDto ud = new UserDto();
		BeanUtils.copyProperties(ud, user);
		return ud;
	}
	
	
	
	/**
	 * @Description:通过id获取userDto对象
	 * @param id
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException UserDto  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月13日
	 */
	public UserDto getOneUser(String id) throws IllegalAccessException, InvocationTargetException {
		User findOne = userRepository.findOne(id);
		UserDto ud = new UserDto();
		BeanUtils.copyProperties(ud, findOne);
		return ud;
	}
	
	
	
	
}
