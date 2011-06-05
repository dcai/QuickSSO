package cn.edu.ujn.bean;

public class GroupBean {
	private int group_id;
	private String group_name;
	private int group_type;
	private int group_moderator;
	private String group_desc;
	public GroupBean(){
		this.group_id = -1;
		this.group_type = -1;
		this.group_name = "";
		this.group_desc = "";
	}
	public GroupBean(int group_id, String group_name, int group_type, int group_moderator, String group_desc)
	{
		this.group_id = group_id;
		this.group_name = group_name;
		this.group_type = group_type;
		this.group_moderator = group_moderator;
		this.group_desc = group_desc;
	}
	public String getGroupDesc() {
		return group_desc;
	}
	public void setGroupDesc(String group_desc) {
		this.group_desc = group_desc;
	}
	public int getGroupId() {
		return group_id;
	}
	public void setGroupId(int group_id) {
		this.group_id = group_id;
	}
	public int getGroupModerator() {
		return group_moderator;
	}
	public void setGroupModerator(int group_moderator) {
		this.group_moderator = group_moderator;
	}
	public String getGroupName() {
		return group_name;
	}
	public void setGroupName(String group_name) {
		this.group_name = group_name;
	}
	public int getGroupType() {
		return group_type;
	}
	public void setGroupType(int group_type) {
		this.group_type = group_type;
	}
}
