package cn.edu.ujn.dao;
import cn.edu.ujn.bean.GuidBean;

public interface GuidDAO
{
  public GuidBean execute(String sid);
  public void delete();
}