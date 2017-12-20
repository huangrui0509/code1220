package cn.com.taiji.dto;

import java.util.List;
import java.util.Map;

public class DepartTreeDto {

	private String text;
	private List<DepartTreeDto> nodes;
	private String icon;
	private String selectedIcon;
	private String color;
	private String backColor;
	private String href;
	private Boolean selectable;
	private Map<String,Boolean> state;
	private List<String> tags;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<DepartTreeDto> getNodes() {
		return nodes;
	}
	public void setNodes(List<DepartTreeDto> nodes) {
		this.nodes = nodes;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSelectedIcon() {
		return selectedIcon;
	}
	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBackColor() {
		return backColor;
	}
	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Boolean getSelectable() {
		return selectable;
	}
	public void setSelectable(Boolean selectable) {
		this.selectable = selectable;
	}
	public Map<String, Boolean> getState() {
		return state;
	}
	public void setState(Map<String, Boolean> state) {
		this.state = state;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	
	
	
}
