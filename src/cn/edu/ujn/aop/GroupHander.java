package cn.edu.ujn.aop;

import org.codehaus.nanning.Invocation;
import org.codehaus.nanning.MethodInterceptor;

public class GroupHander implements MethodInterceptor {

	public Object invoke(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
	    System.out.println("tcf : befor call me , right? ");
	    return invocation.invokeNext();
	}

}
