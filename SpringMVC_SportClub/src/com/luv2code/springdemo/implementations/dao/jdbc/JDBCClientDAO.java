package com.luv2code.springdemo.implementations.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.luv2code.springdemo.abstractions.dao.ClientDAO;
import com.luv2code.springdemo.entity.Client;
import com.luv2code.springdemo.entity.Sex;

public class JDBCClientDAO implements ClientDAO{

	public static final String SELECT_BY_ID = 
			"SELECT id as clientId, firstname, lastname, date_of_birth, sex "
		  + "FROM client as c where c.id = ?";
	
	@Override
	public Client findById(int id) {
		try (Connection con = DriverManager.getConnection(JDBCConstants.URL, JDBCConstants.USER, JDBCConstants.PASSWD);
				PreparedStatement pst = con.prepareStatement(SELECT_BY_ID);) {

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Client c = clientFromResultSetRow(rs);
				return c;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public static Client clientFromResultSetRow(ResultSet rs) throws SQLException {
		int id = rs.getInt("clientId");
		String firstname = rs.getString("firstname");
		String lastname = rs.getString("lastname");
		String sexString = rs.getString("sex");
		Sex sex = sexString == null ? Sex.UNDECIDED : Sex.valueOf(sexString);
		LocalDate dob = rs.getDate("date_of_birth").toLocalDate();
		return new Client(id, firstname, lastname, dob, sex);

	}
}
