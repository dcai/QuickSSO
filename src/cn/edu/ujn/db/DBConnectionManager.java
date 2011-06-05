package cn.edu.ujn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class DBConnectionManager {
	private Logger log = Logger.getLogger(DBConnectionManager.class);

	private static  DBConnectionManager instance = null;

	private DBConnectionPool pool;

	public static synchronized DBConnectionManager getInstance() {
		if (instance == null){
			instance = new DBConnectionManager();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return pool.getConnection();
	}

	public void freeConnection(Connection con) throws SQLException {
		pool.freeConnection(con);

	}

	private DBConnectionManager() {
		init();
	}

	private void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
		pool = new DBConnectionPool("jdbc:mysql://localhost/uso", "root",
				"123456", 20);
	}

	// inner class

	class DBConnectionPool {

		private ArrayList conns = new ArrayList();
		
		private int maxConn;
		
		private int connNum = 0;

		private String URL;

		private String password;

		private String user;

		public DBConnectionPool(String URL, String user, String password,
				int maxConn) {
			this.URL = URL;
			this.user = user;
			this.password = password;
			this.maxConn = maxConn;
		}
		public synchronized void freeConnection(Connection con) {
			if(!conns.contains(con) && con != null)
			{
				conns.add(con);
				connNum--;
				notifyAll();
			}
		}
		public synchronized Connection getConnection() throws SQLException {
			Connection con = null;
			if (conns.size() > 0) {
				con = (Connection) conns.get(0);
				conns.remove(0);
				log.info("****** CONN IN POOLS ******");
				try {
					if (con.isClosed()) {
						con = getConnection();
					}
				} catch (SQLException e) {
					//con = getConnection();
					return null;
				}
			} else if (maxConn == 0 || connNum < maxConn) {
				con = newConnection();
			}
			if (con != null) {
				connNum++;
			}
			return con;
		}

		private Connection newConnection() throws SQLException {
			log.info("###### CREATE A NEW CONN ######");
			Connection con = DriverManager.getConnection(URL, user, password);
			conns.add(con);
			return con;
		}
	}

}