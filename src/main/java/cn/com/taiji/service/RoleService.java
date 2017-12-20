package cn.com.taiji.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.com.taiji.domain.Role;
import cn.com.taiji.domain.User;
import cn.com.taiji.dto.RoleDto;
import cn.com.taiji.dto.UserDto;
import cn.com.taiji.repository.RoleRepository;

/**        
 * 类名称：RoleService   
 * 类描述：   
 * 创建人：lenovo   
 * 创建时间：2017年12月8日 下午7:41:24 
 * @version      
 */ 
@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	
	/**
	 * @Description: 实现新增和修改Role对象，返回类型Role
	 * @param roleDto
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException Role  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月9日
	 */
	public Role saveAndFlushRole(RoleDto roleDto) throws IllegalAccessException, InvocationTargetException {
		Role role = new Role();
		if(null==roleDto.getId()) {
			roleDto.setId(UUID.randomUUID().toString().replace("-", ""));
		}
		BeanUtils.copyProperties(role,roleDto);
		Role newRole = roleRepository.saveAndFlush(role);
		
		return newRole;
	}
	
	
	/**
	 * @Description: 获取单个role对象信息
	 * @param id
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException RoleDto  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月17日
	 */
	public RoleDto getOneRole(String id) throws IllegalAccessException, InvocationTargetException {
		Role role = roleRepository.findOne(id);
		RoleDto rd = new RoleDto();
		BeanUtils.copyProperties(rd, role);
		return rd;
	}
	
	/**
	 * @Description: 获取所有的Role对象数据以list返回
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException List<RoleDto>  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月9日
	 */
	public List<RoleDto> findAllRole() throws IllegalAccessException, InvocationTargetException{
		List<Role> roleList = roleRepository.findAll();
		List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
		for (Role role : roleList) {
			RoleDto rd = new RoleDto();
			BeanUtils.copyProperties(rd, role);
			roleDtoList.add(rd);
		}
		return roleDtoList;
	} 
	
	
	/**
	 * @Description: 获取一个role的有多少个user，返回list<UserDto>集合
	 * @param roleDto
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException List<UserDto>  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月9日
	 */
	public List<UserDto> getUserList(RoleDto roleDto) throws IllegalAccessException, InvocationTargetException{
		Role role = new Role();
		BeanUtils.copyProperties(role, roleDto);
		System.out.println("role="+role.getId());
		List<User> users = role.getUsers();
		System.out.println("user="+users.size());
		ArrayList<UserDto> userList = new ArrayList<UserDto>();
		for (User user : users) {
			UserDto ud = new UserDto();
			BeanUtils.copyProperties(ud, user);
			userList.add(ud);
		}
		return userList;
	} 
	
	public Map getPage(Map searchParameters) throws IllegalAccessException, InvocationTargetException {
		
		Map map = new HashMap();
		int page = 0;
		int pageSize = 10;
		Page<Role> pageContent;
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
			Specification<Role> spec = new Specification<Role>() {
				@Override
				public Predicate toPredicate(Root<Role> root,
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
						if ("roleName".equalsIgnoreCase(field)) {
							if (value.length() > 0)
								pl.add(cb.like(root.<String> get(field), value + "%"));
						} 
						if ("roleDesc".equalsIgnoreCase(field)) {
							if (value.length() > 0)
								pl.add(cb.like(root.get(field), value));
						}
						
					}
					return cb.and(pl.toArray(new Predicate[0]));
				}
			};
			pageContent = roleRepository.findAll(spec, pageable);
			
			
		} else {
			pageContent = roleRepository.findAll(pageable);
		}
		map.put("total", pageContent.getTotalElements());
		map.put("roles", accountPage2Dto(pageContent));
		
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
	private List<RoleDto> accountPage2Dto(Page<Role> pageContent) throws IllegalAccessException, InvocationTargetException {
		List<Role> roles = pageContent.getContent();
		List<RoleDto> roleDtos =new  ArrayList();
		for (Role role : roles) {
			RoleDto rd = new RoleDto();
			BeanUtils.copyProperties(rd, role);
			roleDtos.add(rd);
		}
		return roleDtos;
	}
	
	
	/**
	 * @Description: 删除role对象 信息
	 * @param id void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月17日
	 */
	public void deleteRole(String id) {
		Role findOne = roleRepository.findOne(id);
		roleRepository.delete(findOne);
	}
	
	
}
