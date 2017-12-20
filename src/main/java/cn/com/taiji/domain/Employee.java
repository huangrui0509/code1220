package cn.com.taiji.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String category;

	private String city;

	@Column(name="depart_desc")
	private String departDesc;

	@Column(name="emp_name")
	private String empName;

	@Column(name="emp_number")
	private String empNumber;

	private String gender;

	@Column(name="job_level")
	private String jobLevel;

	@Column(name="job_name")
	private String jobName;

	@Column(name="job_sequence")
	private String jobSequence;

	@Column(name="login_name")
	private String loginName;

	private String password;

	@Column(name="secondary_depart")
	private String secondaryDepart;
	
	private String status;

	//bi-directional many-to-many association to Department
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
		name="emp_depart"
		, joinColumns={
			@JoinColumn(name="emp_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="depart_id")
			}
		)
	private List<Department> departments;

	public Employee() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDepartDesc() {
		return this.departDesc;
	}

	public void setDepartDesc(String departDesc) {
		this.departDesc = departDesc;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNumber() {
		return this.empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJobLevel() {
		return this.jobLevel;
	}

	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobSequence() {
		return this.jobSequence;
	}

	public void setJobSequence(String jobSequence) {
		this.jobSequence = jobSequence;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecondaryDepart() {
		return this.secondaryDepart;
	}

	public void setSecondaryDepart(String secondaryDepart) {
		this.secondaryDepart = secondaryDepart;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", category=" + category + ", city=" + city + ", departDesc=" + departDesc
				+ ", empName=" + empName + ", empNumber=" + empNumber + ", gender=" + gender + ", jobLevel=" + jobLevel
				+ ", jobName=" + jobName + ", jobSequence=" + jobSequence + ", loginName=" + loginName + ", password="
				+ password + ", secondaryDepart=" + secondaryDepart + ", status=" + status + ", departments="
				+ departments + "]";
	}
	
	

}