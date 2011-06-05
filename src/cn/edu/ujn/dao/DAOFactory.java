package cn.edu.ujn.dao;

abstract public class DAOFactory
{
  public static DAOFactory getDAOFactory()
  {
    String dao_type = "mysql";
    if(dao_type.equals("mysql"))
      return new MysqlDAOFactory();
    else
      return new MysqlDAOFactory();
  }
  public abstract LoginDAO getLoginDAO();
  public abstract GuidDAO   getGuidDAO();
  public abstract AppDAO   getAppDAO();
  public abstract MapDAO   getMapDAO();
  public abstract UserDAO getUserDAO();
  public abstract GroupDAO getGroupDAO();
  public abstract AuthDAO getAuthDAO();
}