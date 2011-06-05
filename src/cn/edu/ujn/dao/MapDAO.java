package cn.edu.ujn.dao;

import cn.edu.ujn.bean.MapBean;

public interface MapDAO
{
  public MapBean execute(int app_id, int uid)throws Exception;
  public int     edit(int app_id, int uid, String username, String password);
  public int     delete(int app_id, int uid);
}