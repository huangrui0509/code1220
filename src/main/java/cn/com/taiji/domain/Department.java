package cn.com.taiji.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="depart_desc")
	private String departDesc;

	@Column(name="depart_name")
	private String departName;

	@Column(name="depart_number")
	private String departNumber;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Department department;

	//bi-directional many-to-one association to Department
	@JsonBackReference
	@OneToMany(mappedBy="department",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Department> departments;

	//bi-directional many-to-many association to Employee
	@JsonBackReference
	@ManyToMany(mappedBy="departments",fetch=FetchType.EAGER)
	private List<Employee> employees;

	public Department() {
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

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setDepartment(this);

		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setDepartment(null);

		return department;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}