package com.mfs.dcl;

import com.mfs.dcl.model.BeyonicSystemConfigModel;
import org.apache.log4j.Logger;

import java.sql.*;

 /*
  * DBConnect Class for Connection
  *
  * @since 28-04-2022
  */

public class DBConnect {

	private static final Logger LOGGER = Logger.getLogger(DBConnect.class);
	private static DBConnect _instance = null;
	private static Object syncLock = new Object();
	Connection con;

	DBConnect() {
		openConnection();
	}

	// This Method has been written for connect to database
	public void openConnection() {

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.con = DriverManager.getConnection(SystemConfiguration.databaseConnectionUrl,
					SystemConfiguration.databaseConnectionUser, SystemConfiguration.databaseConnectionPassword);

			if (this.con == null) {
				LOGGER.error("=> DBConnect getConnection connection is null ");
			}

		} catch (Exception localException) {
			LOGGER.error("=> DBConnect getConnection  Exception  ", localException);
		}

	}

	public static DBConnect getInstance() {
		if (_instance == null) {
			synchronized (syncLock) {
				if (_instance == null) {
					_instance = new DBConnect();
				}
			}
		}
		return _instance;
	}

	//for close database connection
	public void closeConnection() {
		try {
			this.con.close();
		}
		catch (Exception localException) {
			LOGGER.error("=> DBConnect closeConnection  Exception  ", localException);
		}
	}

	//for url
	public BeyonicSystemConfigModel geturl() {
		
		BeyonicSystemConfigModel systemconfig = new BeyonicSystemConfigModel();
		try {

			PreparedStatement localPreparedStatement = con
					.prepareStatement("SELECT * FROM beyonic_system_config where system_config_key=?");
			localPreparedStatement.setString(1, "intouch_callback_url");
			ResultSet rs = localPreparedStatement.executeQuery();

			while (rs.next()) {

				systemconfig.setConfigKey(rs.getString("system_config_key"));
				systemconfig.setConfigValue(rs.getString("system_config_value"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return systemconfig;

	}



}
