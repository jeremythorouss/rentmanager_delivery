package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
@Repository
public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}

	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, model, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, model, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, model, nb_places FROM Vehicle;";
	
	private static final String UPDATE_VEHICLES_QUERY = "UPDATE Vehicle SET constructeur = ?, model = ?, nb_places = ? WHERE id = ?;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle;";
	/*
	 * Cree le vehicle.
	 * parametre: le vehicle .
	 * returne L'id du vehicle cree
	 */
	public int create(Vehicle vehicle) throws DaoException {
		try {
			Connection conn=ConnectionManager.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(CREATE_VEHICLE_QUERY);
			pstmt.setString(1, vehicle.getConstructeur());
			pstmt.setString(2, vehicle.getModel());
			pstmt.setInt(3, vehicle.getNb_places());
			return pstmt.executeUpdate();
			
	} catch (SQLException e) {
		e.printStackTrace();
		
	}
	return 0;
	}
	
	/*
	 * supprime le vehicle.
	 * parametre: int .
	 * returne rien
	 */
	public long delete(int id) throws DaoException {
		try {
			Connection conn=ConnectionManager.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(DELETE_VEHICLE_QUERY);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return 0;
	}
	/*
	 * trouve les vehicle.
	 * parametre: int.
	 * returne le vehicle.
	 */
	public Optional<Vehicle> findById(int id) throws DaoException {
		try {
			Connection conn=ConnectionManager.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(FIND_VEHICLE_QUERY);
			
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			
			rs.next();
			
			//int clientID=rs.getInt("id");
			String vehicleConstructor=rs.getString("constructeur");
			String vehicleModel=rs.getString("model");
			int vehiclePlace=rs.getInt("nb_places");

			Vehicle vehicle=new Vehicle(
					id, vehicleConstructor,vehicleModel, vehiclePlace);
			return Optional.of(vehicle);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		
		
		return Optional.empty();

	}
	/*
	 * trouve les vehicle.
	 * parametre: rien.
	 * returne le vehicle.
	 */
	public List<Vehicle> findAll() throws DaoException {
		ArrayList list_vehicle = new ArrayList();
		 try {
			Connection conn=ConnectionManager.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(FIND_VEHICLES_QUERY);
			
			ResultSet rs=pstmt.executeQuery();
				
				while (rs.next()){
					
					int vehicleID=rs.getInt("id");
					String vehicleConstructor=rs.getString("constructeur");
					String vehicleModel=rs.getString("model");
					int vehiclePlace=rs.getInt("nb_places");

					Vehicle vehicle=new Vehicle();
					vehicle.setId(vehicleID);
					vehicle.setConstructeur(vehicleConstructor);
					vehicle.setModel(vehicleModel);
					vehicle.setNb_places(vehiclePlace);
					
					list_vehicle.add(vehicle);
				}
				

			
			
			
			return list_vehicle;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return list_vehicle;
		
	}
	/*
	 * compte les vehicle.
	 * parametre: int.
	 * returne int.
	 */
	public int count() throws DaoException {
		int count = 0;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(COUNT_VEHICLES_QUERY);
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
	 * update les vehicle.
	 * parametre: vehicle.
	 * returne le vehicle.
	 */
	public void update(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_VEHICLES_QUERY);
			pstmt.setString(1, vehicle.getConstructeur());
			pstmt.setString(2, vehicle.getModel());
			pstmt.setInt(3, vehicle.getNb_places());
			pstmt.setInt(4, vehicle.getId());

			pstmt.execute();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
