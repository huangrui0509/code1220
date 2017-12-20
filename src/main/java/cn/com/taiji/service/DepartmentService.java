package cn.com.taiji.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.domain.Department;
import cn.com.taiji.domain.Employee;
import cn.com.taiji.dto.DepartmentDto;
import cn.com.taiji.dto.EmployeeDto;
import cn.com.taiji.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository  departmentRepository;
	
	/**
	 * @Description: 通过id查询department对象信息
	 * @param id
	 * @return DepartmentDto  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月18日
	 */
	public DepartmentDto findOneDepart(String id) {
		Department findOne = departmentRepository.findOne(id);
		DepartmentDto dd = new DepartmentDto();
		BeanUtils.copyProperties(findOne, dd);
		return dd;
	}
	
	
	/**
	 * @Description: 查询所有department信息
	 * @return List<DepartmentDto>  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月18日
	 */
	public List<DepartmentDto> findAllDepart(){
		List<Department> allDepart = departmentRepository.findAll();
		List<DepartmentDto> allDepartDto = new ArrayList();
		for (Department depart : allDepart) {
			DepartmentDto dd = new DepartmentDto();
			BeanUtils.copyProperties(dd, allDepart);
			allDepartDto.add(dd);
		}
		return allDepartDto;
	}
	
	/**
	 * @Description: 新增和修改department信息
	 * @param dd void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月18日
	 */
	public void insertDepart(DepartmentDto dd) {
		if(null==dd.getId()) {
			dd.setId(UUID.randomUUID().toString().replace("-", ""));
		}
		Department depart = new Department();
		BeanUtils.copyProperties(dd, depart);
		//System.out.println(emp.getId()+"----------");
		departmentRepository.saveAndFlush(depart);
	}
	
	/**
	 * @Description: 物理删除department信息
	 * @param dd void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月18日
	 */
	public void delete(DepartmentDto dd) {
		
		Department depart = new Department();
		BeanUtils.copyProperties(dd, depart);
		departmentRepository.delete(depart);
	
	}
}
