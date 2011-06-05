package cn.edu.ujn.dao;

import cn.edu.ujn.bean.AuthBean;
import java.util.ArrayList;
import cn.edu.ujn.bean.GroupBean;
import cn.edu.ujn.bean.UserBean;
public interface AuthDAO {
	public AuthBean execute(UserBean user, GroupBean group);
	public ArrayList execute(UserBean user);
}
