package cn.com.taiji.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


public class EmployeeDto implements Serializable {
	private static final long serialVersionUID = 1L;


	private String id;

	private String category;

	private String city;


	private String departDesc;


	private String empName;


	private String empNumber;

	private String gender;


	private String jobLevel;


	private String jobName;


	private String jobSequence;


	private String loginName;

	private String password;


	private String secondaryDepart;
	
	private String status;

	public EmployeeDto() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	

}