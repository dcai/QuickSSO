package cn.edu.ujn.dao;
import cn.edu.ujn.bean.UserBean;
import java.util.ArrayList;
public interface UserDAO
{
  public UserBean execute(int uid);
  public int      getTotal();
  public ArrayList getUserList(int offset);
  public int      getMaxLine();
  public void     delete(int uid)throws Exception;
  public void     editUser(UserBean usr);
  //public ArrayList 
}