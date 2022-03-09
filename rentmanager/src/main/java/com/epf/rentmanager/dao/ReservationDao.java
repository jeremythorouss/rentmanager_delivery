package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
@Repository
public class ReservationDao {

	private static ReservationDao instance = null;

	private ReservationDao() {
	}
@Autowired
VehicleService vehicleService; 
@Autowired
ClientService clientService; 



	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";

	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id = ?";
	private static final String FIND_RESERVATION_QUERY = "SELECT client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	
	/*
	 * Cree la réservation.
	 * parametre: la reservation .
	 * returne L'id de la reservation cree.
	 */
	public long create(Reservation reservation) throws DaoException {
		
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(CREATE_RESERVATION_QUERY);
			
			LocalDate datedebut = reservation.getDateStart();
			LocalDate datefin = reservation.getDatEnd();
			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicle());
			pstmt.setDate(3, Date.valueOf(datedebut));
			pstmt.setDate(4, Date.valueOf(datefin));


			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return 0;
	}
	/*
	 * delete la réservation.
	 * parametre:int .
	 * returne rien.
	 */
	public long delete(int id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			pstmt.setInt(1, id);
			pstmt.execute();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/*
	 * trouve les reservations.
	 * parametre: int.
	 * returne les reservations.
	 */
	public List<Reservation> findResaByClientId(int idClient) throws DaoException {
		ArrayList list_reservation2 = new ArrayList();
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);

			pstmt.setInt(1, idClient);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			while (rs.next()) {
				
				Reservation reservation = new Reservation();
				reservation.setId(rs.getInt("id"));
				reservation.setIdClient(idClient);
				reservation.setIdVehicle(rs.getInt("vehicle_id"));
				reservation.setDateStart(rs.getDate("debut").toLocalDate());
				reservation.setDatEnd(rs.getDate("fin").toLocalDate());
				
				list_reservation2.add(reservation);
				
				} 
			return list_reservation2;
		}catch (SQLException e) {
			e.printStackTrace();

		}
		return list_reservation2;
	}

	/*
	 * trouve les reservations.
	 * parametre: int.
	 * returne les reservations.
	 */
	public List<Reservation> findResaByVehicleId(int idVehicle) throws DaoException {
		ArrayList list_reservation3 = new ArrayList();
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);

			pstmt.setInt(1, idVehicle);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			while (rs.next()) {
				
				Reservation reservation = new Reservation();
				reservation.setId(rs.getInt("id"));
				reservation.setIdVehicle(idVehicle);
				reservation.setIdClient(rs.getInt("client_id"));
				reservation.setDateStart(rs.getDate("debut").toLocalDate());
				reservation.setDatEnd(rs.getDate("fin").toLocalDate());
				
				list_reservation3.add(reservation);
				
				} 
			return list_reservation3;
		}catch (SQLException e) {
			e.printStackTrace();

		}
		return list_reservation3;
	}
	/*
	 * trouve les reservations.
	 * parametre: rien
	 * returne les reservations.
	 */
	public List<Reservation> findAll() throws DaoException {
		ArrayList list_reservation = new ArrayList();
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int reservationid = rs.getInt("id");
				int idClient = rs.getInt("client_id");
				int idVehicle = rs.getInt("vehicle_id");
				LocalDate dateStart = rs.getDate("debut").toLocalDate();
				LocalDate datEnd = rs.getDate("fin").toLocalDate();

				// String constructeur=rs.getString("constructeur");
				// String nom=rs.getString("nom");

				Vehicle vehicle = vehicleService.findById(idVehicle);
				Client client = clientService.findById(idClient);

				String vehiclename = vehicle.getConstructeur() + " " + vehicle.getModel();
				String clientname = client.getFirstname() + " " + client.getLastname();

				//Reservation reservation = new Reservation(reservationid, vehiclename, clientname, dateStart, datEnd);
				Reservation reservation = new Reservation(reservationid, idClient, idVehicle, dateStart, datEnd, vehiclename,clientname);
				list_reservation.add(reservation);
			}

			return list_reservation;

		} catch (ServiceException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list_reservation;
	}
	/*
	 * compte les reservations.
	 * parametre: rien.
	 * returne int.
	 */
	public int count() throws DaoException {
		int count = 0;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(COUNT_RESERVATIONS_QUERY);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
			pstmt.execute();
			resultSet.close();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	/*
	 * update la reservation.
	 * parametre: reservation.
	 * returne la reservation.
	 */
	public void update(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_RESERVATION_QUERY);
			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicle());
			pstmt.setDate(3, Date.valueOf(reservation.getDateStart()));
			pstmt.setDate(4, Date.valueOf(reservation.getDatEnd()));
			pstmt.setInt(5, reservation.getId());


			pstmt.execute();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * trouve les reservations.
	 * parametre: rien
	 * returne les reservations.
	 */
	public Optional<Reservation> findById(int id) throws DaoException {
		
		try {
			Connection conn=ConnectionManager.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(FIND_RESERVATION_QUERY);
			
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			
			rs.next();
			
			//int clientID=rs.getInt("id");
			int idClient=rs.getInt("client_id");
			int idVehicle=rs.getInt("vehicle_id");
			LocalDate dateStart=rs.getDate("debut").toLocalDate();;
			LocalDate datefin=rs.getDate("fin").toLocalDate();

			Reservation reservation=new Reservation(id,idClient,idVehicle,dateStart,datefin);
			return Optional.of(reservation);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		
		
		return Optional.empty();
	}
}
