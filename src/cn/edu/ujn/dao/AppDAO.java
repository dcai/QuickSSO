package cn.edu.ujn.dao;
import cn.edu.ujn.bean.AppBean;
import java.util.ArrayList;

public interface AppDAO
{
  public AppBean execute(int app_id);
  public ArrayList listAll();
}