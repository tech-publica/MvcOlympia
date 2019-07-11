package com.luv2code.springdemo.implementations.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.luv2code.springdemo.abstractions.dao.ReservationDAO;
import com.luv2code.springdemo.entity.Client;
import com.luv2code.springdemo.entity.Court;
import com.luv2code.springdemo.entity.Reservation;

public class JDBCReservationDAO implements ReservationDAO {
	public static final String SELECT_ALL = "select "
			+ " res.id as idOfRes, res.start_date, res.end_date, res.client_id, res.court_id, res.cost, "
			+ " client.id as clientId, client.firstname, client.lastname, client.date_of_birth, client.sex, "
			+ " court.id as courtId, court.name, court.num_players, court.surface_type, "
			+ " tennis_court.id as tennisId, tennis_court.has_double_lines, "
			+ " soccer_court.id as soccerId, soccer_court.has_big_field_doors "
			+ " from reservation as res inner join client on res.client_id = client.id "
			+ " inner join court on res.court_id = court.id "
			+ " left outer join tennis_court on court.id = tennis_court.id "
			+ " left outer join soccer_court on court.id = soccer_court.id "
			;
	public static final String SELECT_BY_ID = SELECT_ALL + " where res.id = ? ";
	public static final String DELETE_BY_ID = "delete from reservation where id = ? ";
    public static final String INSERT = "insert into reservation (start_date, end_date, client_id, court_id, cost) "
    		+ "values(?,?,?,?,?)";
    public static final String UPDATE = "update reservation set start_date = ? , end_date = ? , client_id = ?, court_id = ? , cost = ? "
    		+ "where id = ?";

	@Override
	public Iterable<Reservation> allReservations() {

		try (Connection con = DriverManager.getConnection(JDBCConstants.URL, JDBCConstants.USER, JDBCConstants.PASSWD); Statement st = con.createStatement()) {
			ResultSet rs = st.executeQuery(SELECT_ALL);
			List<Reservation> all = new ArrayList<>();
			while (rs.next()) {
				Reservation res = reservationFromResultSetRow(rs);
				Client client = JDBCClientDAO.clientFromResultSetRow(rs);
				Court court = JDBCCourtDAO.courtFromResultSetRow(rs);
				res.setClient(client);
				res.setCourt(court);
				all.add(res);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	private Reservation reservationFromResultSetRow(ResultSet rs) throws SQLException {
		int resId = rs.getInt("idOfRes");
		Timestamp tsStart = rs.getTimestamp("start_date");
		Timestamp tsEnd = rs.getTimestamp("end_date");
		double cost = rs.getDouble("cost");
		Reservation res = new Reservation(resId, tsStart.toLocalDateTime(), tsEnd.toLocalDateTime(), null, null, cost);
		return res;
	}

	@Override
	public Reservation add(Reservation newReservation) {
		try (Connection con = DriverManager.getConnection(JDBCConstants.URL, JDBCConstants.USER, JDBCConstants.PASSWD);
				PreparedStatement pst = con.prepareStatement(INSERT);) {

			pst.setTimestamp(1,  Timestamp.valueOf(newReservation.getStart()));
			pst.setTimestamp(2,  Timestamp.valueOf(newReservation.getEnd()));
			pst.setInt(3,  newReservation.getClient().getId());
			pst.setInt(4,  newReservation.getCourt().getId());
			pst.setDouble(5,  newReservation.getCost());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
            	int id = rs.getInt(1);
                System.out.println("Key returned from getGeneratedKeys():"
                        + id);
                newReservation.setId(id);
            } 
            rs.close();
			return newReservation;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reservation update(Reservation newReservation) {
		try (Connection con = DriverManager.getConnection(JDBCConstants.URL, JDBCConstants.USER, JDBCConstants.PASSWD);
				PreparedStatement pst = con.prepareStatement(UPDATE);) {

			pst.setTimestamp(1,  Timestamp.valueOf(newReservation.getStart()));
			pst.setTimestamp(2,  Timestamp.valueOf(newReservation.getEnd()));
			pst.setInt(3,  newReservation.getClient().getId());
			pst.setInt(4,  newReservation.getCourt().getId());
			pst.setDouble(5,  newReservation.getCost());
			pst.setInt(6,  newReservation.getId());
			pst.executeUpdate();
			return newReservation;

		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Reservation delete(int id) {
		Reservation found = findById(id);
		try (Connection con = DriverManager.getConnection(JDBCConstants.URL, JDBCConstants.USER, JDBCConstants.PASSWD);
				PreparedStatement pst = con.prepareStatement(DELETE_BY_ID);) {

			pst.setInt(1, id);
			pst.execute();
			return found;
		} catch (SQLException e) {

			return found;
		}
	}

	@Override
	public Reservation findById(int id) {
		try (Connection con = DriverManager.getConnection(JDBCConstants.URL, JDBCConstants.USER, JDBCConstants.PASSWD);
				PreparedStatement pst = con.prepareStatement(SELECT_BY_ID);) {

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Reservation res = reservationFromResultSetRow(rs);
				Client client = JDBCClientDAO.clientFromResultSetRow(rs);
				Court court = JDBCCourtDAO.courtFromResultSetRow(rs);
				res.setClient(client);
				res.setCourt(court);
				return res;
			}
			return null;

		} catch (SQLException e) {
			return null;
		}

	}

}
