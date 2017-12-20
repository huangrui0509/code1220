package cn.com.taiji.dto;

import java.io.Serializable;
import javax.persistence.*;

import cn.com.taiji.domain.Employee;

import java.util.List;



public class DepartmentDto implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String id;

	
	private String departDesc;

	
	private String departName;

			
	private String departNumber;

	
	private DepartmentDto departmentDto;

	private List<DepartmentDto> departmentDtos;

	private List<Employee> employees;

	public DepartmentDto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartDesc() {
		return this.departDesc;
	}

	public void setDepartDesc(String departDesc) {
		this.departDesc = departDesc;
	}

	public String getDepartName() {
		return this.departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDepartNumber() {
		return this.departNumber;
	}

	public void setDepartNumber(String departNumber) {
		this.departNumber = departNumber;
	}

	public DepartmentDto getDepartment() {
		return this.departmentDto;
	}

	public void setDepartment(DepartmentDto departmentDto) {
		this.departmentDto = departmentDto;
	}

	public List<DepartmentDto> getDepartments() {
		return this.departmentDtos;
	}

	public void setDepartments(List<DepartmentDto> departmentDtos) {
		this.departmentDtos = departmentDtos;
	}

	public DepartmentDto addDepartment(DepartmentDto departmentDto) {
		getDepartments().add(departmentDto);
		departmentDto.setDepartment(this);

		return departmentDto;
	}

	public DepartmentDto removeDepartment(DepartmentDto departmentDto) {
		getDepartments().remove(departmentDto);
		departmentDto.setDepartment(null);

		return departmentDto;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}