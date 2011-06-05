package cn.edu.ujn.dao;

import java.util.ArrayList;

import cn.edu.ujn.bean.AuthBean;
import cn.edu.ujn.bean.GroupBean;
import cn.edu.ujn.bean.UserBean;
public class GroupProxy implements GroupDAO {
	private GroupDAO gdao;
	private UserBean user;
	private GroupBean group;
	private AuthBean authBean;
	private AuthDAO authdao;
	public GroupProxy(GroupDAO gdao, UserBean user, GroupBean group)
	{
		this.gdao = gdao;
		this.user = user;
		this.group = group;
		authdao = DAOFactory.getDAOFactory().getAuthDAO();
		authBean = authdao.execute(this.user, this.group);
	}

	public GroupBean execute(int group_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList getUsers(int group_id) {
		// TODO Auto-generated method stub
		ArrayList result = null;
		if(canRead()){
			result = this.gdao.getUsers(group_id);
			return result;
		}else{
			return null;
		}
	}

	public ArrayList listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeUser(int group_id, int user_id) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(canEdit()){
			result = this.gdao.removeUser(this.group.getGroupId(), this.user.getUid());
		}
		return result;
	}
	public boolean addUser(int group_id, int user_id){
		boolean result = false;
		if(canEdit()){
			result = this.gdao.addUser(group_id, user_id);
			return result;
		}else{
			return false;
		}
	}
	public boolean update(GroupBean gp) {
		// TODO Auto-generated method stub
		boolean result = false; 
		if(canEdit()){
			result = this.gdao.update(gp);
			return result;
		}else{
		
			return false;
		}
	}
	public boolean canRead(){
		//int gid = this.gdao.get
		return authBean.canView();
	}
	public boolean canEdit(){
		return authBean.canEdit();
	}
	public boolean canAdd(){
		return authBean.canAdd();
	}
	public boolean canDel(){
		return authBean.canDel();
	}
}
