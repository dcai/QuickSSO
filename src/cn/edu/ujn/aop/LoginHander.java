package cn.edu.ujn.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;

import cn.edu.ujn.bean.Authorization;


public class LoginHander implements InvocationHandler {
	private static Logger log = Logger.getLogger(LoginHander.class);
	private Object proxyObj;
	public Object bind(Object obj){
		  this.proxyObj=obj;
		  return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
				  obj.getClass().getInterfaces(),this);
	}
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		try{
			for(int i = 0 ; i < args.length ; i++){
				log.info("before invoke :  --"+ args[i].toString());
			}
			result=method.invoke(proxyObj,args); //Ô­·½·¨
			log.info(((Authorization)result).getInfo());
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}