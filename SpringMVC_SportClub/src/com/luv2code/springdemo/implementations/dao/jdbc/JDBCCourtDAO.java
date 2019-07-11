package com.luv2code.springdemo.implementations.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.luv2code.springdemo.abstractions.dao.CourtDAO;
import com.luv2code.springdemo.entity.Court;
import com.luv2code.springdemo.entity.SoccerCourt;
import com.luv2code.springdemo.entity.Surface;
import com.luv2code.springdemo.entity.TennisCourt;

public class JDBCCourtDAO implements CourtDAO {

	public static String SELECT_BY_ID = "SELECT c.id as courtId, name, num_players, cost, surface_type,  "
			+ "t.id as tennisId, t.has_double_lines, " + "s.id as soccerId, s.has_big_field_doors "
			+ "from court as c left outer join tennis_court as t on c.id = t.id " + "left outer join soccer_court as s on c.id = s.id "
			+ "where c.id = ?";

	@Override
	public Court findById(int id) {
		try (Connection con = DriverManager.getConnection(JDBCConstants.URL, JDBCConstants.USER, JDBCConstants.PASSWD);
				PreparedStatement pst = con.prepareStatement(SELECT_BY_ID);) {

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Court c = courtFromResultSetRow(rs);
				return c;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Court courtFromResultSetRow(ResultSet rs) throws SQLException {
		int id = rs.getInt("courtId");
		String name = rs.getString("name");
		int numPlayers = rs.getInt("num_players");
		double cost = rs.getDouble("cost");
		int surfaceType = rs.getInt("surface_type");
		Surface surface = Surface.values()[surfaceType];
		Integer tennisId = (Integer) rs.getObject("tennisId");
		if (!rs.wasNull()) {
			boolean hasDoubleLines = rs.getBoolean("has_double_lines");
			return new TennisCourt(tennisId, name, surface, numPlayers, cost, hasDoubleLines);
		}
		Integer soccerId = (Integer) rs.getObject("soccerId");
		if (!rs.wasNull()) {
			boolean hasBigFieldDoors = rs.getBoolean("has_big_field_doors");
			return new SoccerCourt(soccerId, name, surface, numPlayers, cost, hasBigFieldDoors);
		}
		throw new RuntimeException("kind of court not recognized");
	}

}
