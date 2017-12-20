package cn.com.taiji.dto;

import java.io.Serializable;
import javax.persistence.*;

import cn.com.taiji.domain.Role;

import java.util.List;





public class MenuDto implements Serializable {
	private static final long serialVersionUID = 1L;


	private String id;

	private String describes;

	private String levels;

	private String menuName;


	private String parentId;

	private String url;

	private List<Role> roles;

	public MenuDto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}
	
	

}