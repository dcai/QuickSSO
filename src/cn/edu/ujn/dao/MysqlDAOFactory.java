package cn.edu.ujn.dao;


//import cn.edu.ujn.aop.AOPFactory;
import org.apache.log4j.Logger;

public class MysqlDAOFactory extends DAOFactory
{
  Logger log = Logger.getLogger(MysqlDAOFactory.class);
  public LoginDAO getLoginDAO()
  {
	//return (LoginDAO)AOPFactory.getAOPProxyedObject("cn.edu.ujn.dao.MysqlLoginDAO");
	MysqlLoginDAO ldao = new MysqlLoginDAO();
    return ldao;
  }
  public GuidDAO getGuidDAO()
  {
    return new MysqlGuidDAO();
  }
  public AppDAO getAppDAO()
  {
    return new MysqlAppDAO();
  }
  public MapDAO getMapDAO()
  {
    return new MysqlMapDAO();
  }
  public UserDAO getUserDAO()
  {
    return new MysqlUserDAO();
  }
  
  public AuthDAO getAuthDAO()
  {
	  return new MysqlAuthDAO();
  }
  public GroupDAO getGroupDAO(){
	  /*
		AspectInstance apInstance = new AspectInstance();
		org.codehaus.nanning.Mixin mixinInstance = new Mixin();

		mixinInstance.setInterfaceClass(GroupDAO.class);

		mixinInstance.setTarget(new MysqlGroupDAO());

		mixinInstance.addInterceptor(new GroupHander());

		apInstance.addMixin(mixinInstance);

	    Object groupDao = apInstance.getProxy();
	  //return new MysqlGroupDAO();
	   * */
	    return new MysqlGroupDAO();
  }
}