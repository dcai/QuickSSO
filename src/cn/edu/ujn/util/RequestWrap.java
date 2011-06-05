package cn.edu.ujn.util;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

public class RequestWrap
{
  private boolean multipleForm;
  private String encoding;
  private HttpServletRequest request;
  private Map sigleParams;
  private Map multiParams;

  private RequestWrap(HttpServletRequest request, String encoding)
  {
    multipleForm = false;
    this.encoding = null;
    this.request = null;
    sigleParams = new HashMap();
    multiParams = new HashMap();
    this.request = request;
    this.encoding = encoding;
    if(this.request != null && encoding != null && !encoding.equals(""))
    {
      try
      {
        this.request.setCharacterEncoding(this.encoding);
      }
      catch(UnsupportedEncodingException e)
      {
        e.printStackTrace();
      }
    }
  }
  public static RequestWrap getInstance(HttpServletRequest request)
  {
    return new RequestWrap(request, null);
  }

  public static RequestWrap getInstance(HttpServletRequest request, String encoding)
  {
    return new RequestWrap(request, encoding);
  }

  public String getString(String paramName)
  {
    String temp = null;
    if(multipleForm)
      temp = (String)sigleParams.get(paramName);
    else
      temp = request.getParameter(paramName);
    if(temp != null && !temp.equals(""))
        {
            if(temp.indexOf("&#") != -1)
                return getAsciiBasedUnicode(temp);
            else
                return temp;
        } else
        {
            return null;
        }
    }

    public String getString(String paramName, String defaultString)
    {
        String temp = getString(paramName);
        if(temp == null)
            temp = defaultString;
        return temp;
    }

    public String[] getStrings(String paramName)
    {
        String values[] = (String[])null;
        if(multipleForm)
        {
            List _values = (List)multiParams.get(paramName);
            values = new String[_values.size()];
            for(int i = 0; i < values.length; i++)
                values[i] = (String)_values.get(i);

        } else
        {
            values = request.getParameterValues(paramName);
        }
        return values;
    }

    public int getInt(String paramName)
        throws NumberFormatException
    {
        return Integer.parseInt(getString(paramName));
    }

    public int getInt(String paramName, int defaultInt)
    {
        try
        {
            String temp = getString(paramName);
            if(temp == null)
                return defaultInt;
            else
                return Integer.parseInt(temp);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean getBoolean(String paramName)
        throws NumberFormatException
    {
        return Boolean.valueOf(getString(paramName)).booleanValue();
    }

    public boolean getBoolean(String paramName, boolean defaultBoolean)
    {
        try
        {
            String temp = getString(paramName);
            if(temp == null)
                return defaultBoolean;
            else
                return Boolean.valueOf(temp).booleanValue();
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public static String getString(HttpServletRequest request, String paramName)
    {
        try
        {
            request.setCharacterEncoding("GBK");
        }
        catch(UnsupportedEncodingException uee)
        {
            uee.printStackTrace(System.out);
        }
        String temp = request.getParameter(paramName);
        if(temp != null && !temp.equals(""))
        {
            if(temp.indexOf("&#") != -1)
                return getAsciiBasedUnicode(temp);
            else
                return temp;
        } else
        {
            return null;
        }
    }

    public static String getString(HttpServletRequest request, String paramName, String defaultString)
    {
        String temp = getString(request, paramName);
        if(temp == null)
            temp = defaultString;
        return temp;
    }

    public static String[] getStrings(HttpServletRequest request, String paramName)
    {
        return request.getParameterValues(paramName);
    }

    public static int getInt(HttpServletRequest request, String paramName)
        throws NumberFormatException
    {
        return Integer.parseInt(getString(request, paramName));
    }

    public static int getInt(HttpServletRequest request, String paramName, int defaultInt)
    {
        try
        {
            String temp = getString(request, paramName);
            if(temp == null)
                return defaultInt;
            else
                return Integer.parseInt(temp);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean getBoolean(HttpServletRequest request, String paramName)
        throws NumberFormatException
    {
        return Boolean.valueOf(getString(request, paramName)).booleanValue();
    }

    public static boolean getBoolean(HttpServletRequest request, String paramName, boolean defaultBoolean)
    {
        try
        {
            String temp = getString(request, paramName);
            if(temp == null)
                return defaultBoolean;
            else
                return Boolean.valueOf(temp).booleanValue();
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        return false;
    }

  private static String getAsciiBasedUnicode(String s1)
  {
    StringBuffer sb = new StringBuffer();
    int len = s1.length();
    for(int i = 0; i < len; i++)
    {
      char c1 = s1.charAt(i);
      if(i + 1 < len)
      {
        char c2 = s1.charAt(i + 1);
        if(c1 == '&' && c2 == '#')
        {
          String s = getUnicode(s1, i + 2, len);
          if(s != null)
          {
            i += s.length() + 2;
            sb.append((char)Integer.valueOf(s).intValue());
          } else {
            sb.append(c1);
          }
        } else {
          sb.append(c1);
        }
      } else {
        sb.append(c1);
      }
    }
    return sb.toString();
  }

  private static String getUnicode(String s, int offset, int max)
  {
    StringBuffer sb = new StringBuffer();
    for(int i = offset; i < max; i++)
    {
      char c = s.charAt(i);
      if(c == ';')
        break;
      if(c > '9' || c < '0')
        return null;
      sb.append(c);
    }
    return sb.toString();
  }
}
