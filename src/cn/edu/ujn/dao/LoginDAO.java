package cn.edu.ujn.dao;
import cn.edu.ujn.bean.Authorization;
import cn.edu.ujn.exception.*;
public interface LoginDAO
{
  public Authorization execute(String usr, String pwd)throws UnauthorizedException;
}