package cn.com.taiji.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String describes;

	private String levels;

	@Column(name="menu_name")
	private String menuName;

	@Column(name="parent_id")
	private String parentId;

	private String url;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="menus",fetch=FetchType.LAZY)
	private List<Role> roles;

	public Menu() {
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

	@Override
	public String toString() {
		return "Menu [id=" + id + ", describes=" + describes + ", levels=" + levels + ", menuName=" + menuName
				+ ", parentId=" + parentId + ", url=" + url + ", roles=" + roles + "]";
	}

	

}