package cn.edu.ujn.bean;
import java.io.*;

public class DbAuthorization implements Serializable, Authorization {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int    uid = -1;
	private int    userLevel = -1;
	private String username = null;
  private String info = null;
  private String url= null;
  private String ws = null;
  private String ticket = null;

	public DbAuthorization(String info) {
		this.uid = -1;
		this.username = "Guest";
    this.info = info;
	}
  public DbAuthorization() {
		this.uid = -1;
		this.username = "Guest";
	}
  public DbAuthorization(int userId, int userLevel, String username, String url, String info)
  {
    this.uid = userId;
    this.username = username;
    this.userLevel = userLevel;
    this.url = url;
    this.info = info;
  }
  public int getLevel(){
    return this.userLevel;
  }
	public int getUid() {
		return uid;
	}
	public String getName() {
		return username;
	}
  public String getUrl(){
    return this.url;
  }
  public void setUrl(String url)
  {
    this.url = url;
  }
  public void setGuid(String ticket){
    this.ticket = ticket;
  }
  public String getGuid()
  {
	  return this.ticket;
  }
  public void setWS(String ws)
  {
    this.ws = ws;
  }
  public String getWs(){
	  return this.ws;
  }
  public String getInfo()
  {
    return info;
  }
}
