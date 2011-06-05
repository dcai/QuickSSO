package cn.edu.ujn.util;
/**
 * Title:        CookieWrap
 * Description:  设置Cookie
 * Copyright:    Copyright (c) 2007
 * @author:      caidongsheng
 * @version      1.0
 */
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.*;

public class CookieWrap
{
  private HttpServletRequest request = null;
  private HttpServletResponse response = null;
  private Cookie cookies[] = null;

  private CookieWrap(HttpServletRequest request, HttpServletResponse response)
  {
    this.request = request;
    this.response = response;
    cookies = this.request.getCookies();
  }

  public static CookieWrap getInstance(HttpServletRequest request, HttpServletResponse response)
  {
    return new CookieWrap(request, response);
  }

  public String getCookieValue(String name)
  {
    String value = null;
    if(cookies != null && cookies.length != 0 && name!= null && !name.equals(""))
    {
      for(int i = 0; i < cookies.length ; i++)
      {
        if(!cookies[i].getName().equals(name))
          continue;
        try{
          value = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
        }
        break;
      }
    }
    return value;
  }

  public void setCookie(String name, String value)
  {
    try
    {
      Cookie cookie = new Cookie(name,value);
      response.addCookie(cookie);
    }
    catch(Exception ex) { }
  }
  public void setCookie(String name, String value, int age)
  {
    try
    {
      Cookie cookie = new Cookie(URLEncoder.encode(name, "UTF-8"), URLEncoder.encode(value, "UTF-8"));
      cookie.setMaxAge(age);
      response.addCookie(cookie);
    }
    catch(UnsupportedEncodingException unsupportedencodingexception) { }
  }

  public void removeCookie(String name)
  {
    Cookie cookie = getCookie(name);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }

  public Cookie getCookie(String name)
  {
    Cookie cookie = null;
    if(cookies != null && cookies.length != 0 && name != null && !name.equals(""))
    {
      for(int i = 0; i < cookies.length; i++)
      {
        if(!cookies[i].getName().equals(name))
          continue;
        cookie = cookies[i];
        break;
      }
    }
    return cookie;
  }
}