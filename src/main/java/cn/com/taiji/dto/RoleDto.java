package cn.com.taiji.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


public class RoleDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String roleDesc;

	private String roleName;

	private List<MenuDto> menuDtos;

	private List<UserDto> userDtos;

	public RoleDto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<MenuDto> getMenus() {
		return this.menuDtos;
	}

	public void setMenus(List<MenuDto> menuDtos) {
		this.menuDtos = menuDtos;
	}

	public List<UserDto> getUsers() {
		return this.userDtos;
	}

	public void setUsers(List<UserDto> userDtos) {
		this.userDtos = userDtos;
	}

}