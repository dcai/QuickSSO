package cn.edu.ujn.dao;

import java.util.ArrayList;
import cn.edu.ujn.bean.GroupBean;

public interface GroupDAO {
	  public GroupBean execute(int group_id);
	  public ArrayList listAll();
	  public ArrayList getUsers(int group_id);
	  public boolean   removeUser(int group_id, int user_id);
	  public boolean   update(GroupBean gp);
	  public boolean   addUser(int group_id,int user_id);
}
