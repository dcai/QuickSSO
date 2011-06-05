package cn.edu.ujn.bean;

public class MapBean
{
  private String usr   = null;
  private String pwd   = null;
  private int    status = 0;
  public MapBean()
  {
    this.status = 404;
  }
  public MapBean(String usr, String pwd)
  {
    this.usr    = usr;
    this.pwd    = pwd;
    this.status = 200;
  }
  public String getUsr(){
    return this.usr;
  }
  public String getPwd()
  {
    return this.pwd;
  }
  public int getStatus()
  {
    return status;
  }
}