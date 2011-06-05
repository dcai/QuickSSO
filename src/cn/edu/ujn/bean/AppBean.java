package cn.edu.ujn.bean;
import org.apache.log4j.Logger;
public class AppBean
{
  static Logger log = Logger.getLogger(AppBean.class);
  private int app_id   = 0;
  private String uri   = null;
  private String ws = null;
  private String name = null;
  public AppBean()
  {
    this.app_id = 0;
  }
  public AppBean(int app_id, String uri, String ws, String name)
  {
    this.app_id = app_id;
    this.uri = uri;
    this.ws  = ws;
    this.name = name;
  }
  public String getName(){
	return this.name;
  }
  public String getUri(){
    return this.uri;
  }
  public String getWS()
  {
    return this.ws;
  }
  public int getId()
  {
    return app_id;
  }
}