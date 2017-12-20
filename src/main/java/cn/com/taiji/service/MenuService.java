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

import cn.com.taiji.domain.Menu;
import cn.com.taiji.domain.Role;
import cn.com.taiji.dto.MenuDto;
import cn.com.taiji.repository.MenuRepository;

/**        
 * 类名称：MenuService   
 * 类描述：   
 * 创建人：lenovo   
 * 创建时间：2017年12月8日 下午7:41:28 
 * @version      
 */ 
@Service
public class MenuService {

	@Autowired
	MenuRepository  menuRepository;

	/**
	 * @Description: 新增和修改menu对象，返回void
	 * @param menuDto
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月11日
	 */
	public void saveAndFlushMenu(MenuDto menuDto) throws IllegalAccessException, InvocationTargetException {
		Menu menu = new Menu();
		BeanUtils.copyProperties(menu, menuDto);
		if(menu.getId()==null) {
			menu.setId(UUID.randomUUID().toString().replace("-", ""));
		}
		System.out.println(menu);
		menuRepository.saveAndFlush(menu);
	}
	/**
	 * @Description: 物理删除menu数据
	 * @param menuDto
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月11日
	 */
	public void deleteMenu(MenuDto menuDto) throws IllegalAccessException, InvocationTargetException {
		Menu menu = new Menu();
		BeanUtils.copyProperties(menu, menuDto);
		menuRepository.delete(menu);
	}

	public Map getPage(Map searchParameters) throws IllegalAccessException, InvocationTargetException {

		Map map = new HashMap();
		int page = 0;
		int pageSize = 10;
		Page<Menu> pageContent;
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
			Specification<Menu> spec = new Specification<Menu>() {
				@Override
				public Predicate toPredicate(Root<Menu> root,
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
						if ("menuName".equalsIgnoreCase(field)) {
							if (value.length() > 0)
								pl.add(cb.like(root.<String> get(field), value + "%"));
						} 
						if ("levels".equalsIgnoreCase(field)) {
							if (value.length() > 0)
								pl.add(cb.like(root.get(field), value));
						}
						
					}
					return cb.and(pl.toArray(new Predicate[0]));
				}
			};
			pageContent = menuRepository.findAll(spec, pageable);


		} else {
			pageContent = menuRepository.findAll(pageable);
		}
		map.put("total", pageContent.getTotalElements());
		map.put("menus", accountPage2Dto(pageContent));

		return map;
	}


	/**
	 * @Description: 简要进行方法说明，并对基础数据类型的参数和返回值加以说明
	 * @param pageContent
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException 
	 * @throws
	 * @author lenovo
	 * @date 2017年12月11日
	 */
	private List<MenuDto> accountPage2Dto(Page<Menu> pageContent) throws IllegalAccessException, InvocationTargetException  {
		List<Menu> menus = pageContent.getContent();
		List<MenuDto> menuDtos =new  ArrayList();
		for (Menu menu : menus) {
			MenuDto md = new MenuDto();
			BeanUtils.copyProperties(md, menu);
			menuDtos.add(md);
		}
		return menuDtos;
	}

	public MenuDto menuToDto(Menu menu) throws IllegalAccessException, InvocationTargetException {
		MenuDto md = new MenuDto();
		BeanUtils.copyProperties(md, menu);
		return md;
	}
	
	
	public MenuDto getOneMenu(String id) throws IllegalAccessException, InvocationTargetException {
		Menu findOne = menuRepository.findOne(id);
		MenuDto md = new MenuDto();
		BeanUtils.copyProperties(md, findOne);
		return md;
		
	}
	
	public void deleteMenu(String id) {
		Menu findOne = menuRepository.findOne(id);
		menuRepository.delete(findOne);
	}



}
