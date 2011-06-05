package cn.edu.ujn.bean;
/**
 * Title: Authorization
 * Description: 
 * Author: Caidongsheng
 */

public interface Authorization
{
  public int    getUid();
  public int    getLevel();
  public String getName();
  public String getInfo();
  public String getUrl();
  public void   setUrl(String url);
  public void   setGuid(String ticket);
  public void   setWS(String ws);
}