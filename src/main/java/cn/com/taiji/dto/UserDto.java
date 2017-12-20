package cn.com.taiji.dto;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;




public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String id;

	private int age;

	private Date birthdate;

	private String createPerson;

	private Date createTime;

	private String department;

	private String education;

	private String email;

	private String gender;

	private byte[] headPortrait;

	private Date hiredate;

	private String password;

	private String phone;

	private String postLevel;

	private String postName;

	private String postSequence;

	private String removePerson;

	private String removeTime;

	private String secondaryDepart;

	private int status;

	private String updatePerson;

	private Date updateTime;

	private String userName;

	private String userNumber;

	private String userType;

	private String workCity;

	private List<RoleDto> roleDtos;

	public UserDto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getHeadPortrait() {
		return this.headPortrait;
	}

	public void setHeadPortrait(byte[] headPortrait) {
		this.headPortrait = headPortrait;
	}

	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostLevel() {
		return this.postLevel;
	}

	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}

	public String getPostName() {
		return this.postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostSequence() {
		return this.postSequence;
	}

	public void setPostSequence(String postSequence) {
		this.postSequence = postSequence;
	}

	public String getRemovePerson() {
		return this.removePerson;
	}

	public void setRemovePerson(String removePerson) {
		this.removePerson = removePerson;
	}

	public String getRemoveTime() {
		return this.removeTime;
	}

	public void setRemoveTime(String removeTime) {
		this.removeTime = removeTime;
	}

	public String getSecondaryDepart() {
		return this.secondaryDepart;
	}

	public void setSecondaryDepart(String secondaryDepart) {
		this.secondaryDepart = secondaryDepart;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUpdatePerson() {
		return this.updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNumber() {
		return this.userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getWorkCity() {
		return this.workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public List<RoleDto> getRoles() {
		return this.roleDtos;
	}

	public void setRoles(List<RoleDto> roleDtos) {
		this.roleDtos = roleDtos;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", age=" + age + ", birthdate=" + birthdate + ", createPerson=" + createPerson
				+ ", createTime=" + createTime + ", department=" + department + ", education=" + education + ", email="
				+ email + ", gender=" + gender + ", headPortrait=" + Arrays.toString(headPortrait) + ", hiredate="
				+ hiredate + ", password=" + password + ", phone=" + phone + ", postLevel=" + postLevel + ", postName="
				+ postName + ", postSequence=" + postSequence + ", removePerson=" + removePerson + ", removeTime="
				+ removeTime + ", secondaryDepart=" + secondaryDepart + ", status=" + status + ", updatePerson="
				+ updatePerson + ", updateTime=" + updateTime + ", userName=" + userName + ", userNumber=" + userNumber
				+ ", userType=" + userType + ", workCity=" + workCity + ", roleDtos=" + roleDtos + "]";
	}
	
	

}