package cn.edu.ujn.bean;

public class GuidBean
{
  private int status   = 0;
  private String key   = null;
  private String value = null;
  public int getStatus()
  {
    return this.status;
  }
  public GuidBean()
  {
    this.status = -1;
    this.key ="FAIL";
    this.value="login fail";
  }
  public GuidBean(int status)
  {
    if (status == 200)
    {
      this.status = 200;
      this.key = "OK";
      this.value = "SID is ok";
    }
    else{
      this.key = "FAIL";
      this.value = "not ok";
    }
  }
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public void setStatus(int status) {
	this.status = status;
}
}