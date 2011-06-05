package cn.edu.ujn.bean;
import org.apache.log4j.*;
public class AuthBean {
	private static Logger log = Logger.getLogger(AuthBean.class);
	private boolean auth_view;
	private boolean auth_add;
	private boolean auth_del;
	private boolean auth_edit;
	public AuthBean(){
		this.auth_add = false;
		this.auth_del = false;
		this.auth_edit = false;
		this.auth_view = false;
	}
	public AuthBean(boolean auth_view, boolean auth_add, boolean auth_del, boolean auth_edit)
	{
		log.info("");
		this.auth_add = auth_view;
		this.auth_del = auth_del;
		this.auth_edit = auth_edit;
		this.auth_view = auth_view;
	}
	public boolean canAdd() {
		return auth_add;
	}
	public boolean canDel() {
		return auth_del;
	}
	public boolean canEdit() {
		return auth_edit;
	}
	public boolean canView() {
		return auth_view;
	}

}
