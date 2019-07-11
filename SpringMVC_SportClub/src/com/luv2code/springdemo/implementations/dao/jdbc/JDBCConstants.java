package com.luv2code.springdemo.implementations.dao.jdbc;

public class JDBCConstants {
	public static final String USER = "postgresMaster";
	public static final String PASSWD = "goPostgresGo";
	public static final String URL = "jdbc:postgresql://localhost/SportClub";
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
