
package cn.edu.ujn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AuthorizationFactory {
	private static Object initLock = new Object();

	private static String classname = "cn.edu.ujn.DbAuthorizationFactory";

	private static AuthorizationFactory factory = null;

	public HttpSession session = null;

	public HttpServletRequest request = null;

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public static AuthorizationFactory getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				if (factory == null) {
					try {
						Class c = Class.forName(classname);
						factory = (AuthorizationFactory) c.newInstance();
					} catch (Exception e) {
						System.err.println("Exception loading class: " + e);
						e.printStackTrace();
						return null;
					}
				} else {
					System.err
							.println("Error: could not create AuthorizationFactory ");
					return null;
				}
			}
		}
		return factory;
	}

}