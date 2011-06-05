package cn.edu.ujn.bean;

public class UserBean
{
  private String name;
  private String fullname;
  private String no_value;
  private String pwd;
  private int    userlevel;
  private int    no_type;
  private int    uid;
  private int    group_id;
  public UserBean(int uid, int userlevel, String name, String fullname, int group_id, int no_type, String no_value )
  {
    this.uid = uid;
    this.userlevel = userlevel;
    this.name = name;
    this.pwd = "";
    this.fullname = fullname;
    this.group_id = group_id;
    this.no_type = no_type;
    this.no_value = no_value;
  }
  public UserBean()
  {
    this.uid = -1;
    this.userlevel = -1;
    this.name = "Guest";
    this.fullname ="";
    this.pwd = "";
    this.group_id = -1;
    this.no_type = -1;
    this.no_value = "";
  }
  public String getFullname()
  {
    return this.fullname;
  }

  
  public String getName() {
    return (this.name); 
  }

  public void setName(String name) {
    this.name = name; 
  }

  public void setFullname(String fullname) {
    this.fullname = fullname; 
  }

  public String getNoValue() {
    return (this.no_value); 
  }

  public void setNoValue(String no_value) {
    this.no_value = no_value; 
  }

  public int getUserlevel() {
    return (this.userlevel); 
  }

  public void setUserlevel(int userlevel) {
    this.userlevel = userlevel; 
  }

  public int getNoType() {
    return (this.no_type); 
  }

  public void setNoType(int no_type) {
    this.no_type = no_type; 
  }

  public int getUid() {
    return (this.uid); 
  }

  public void setUid(int uid) {
    this.uid = uid; 
  }

  public int getGroup() {
    return (this.group_id); 
  }

  public void setGroup(int dept_id) {
    this.group_id = dept_id; 
  }
  public String getPwd(){
	  return this.pwd;
  }
  public void setPwd(String str){
	  this.pwd = str;
  }
}