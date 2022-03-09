package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}

	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id= ?;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client;";
	//contraintes
	private static final String FIND_EMAIL_QUERY = "SELECT id FROM Client WHERE email=?;";
	
	/*
	 * Cree le client.
	 * parametre: le client .
	 * returne L'id du client cree
	 */
	public long create(Client client) throws DaoException {
		try {
			Connection conn=ConnectionManager.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(CREATE_CLIENT_QUERY);
			LocalDate birthdate=client.getBirthdate();
			pstmt.setString(1, client.getLastname());
			pstmt.setString(2, client.getFirstname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(birthdate));
			
			return pstmt.executeUpdate();
			
	} catch (SQLException e) {
		e.printStackTrace();
		
	}

			

			
		return 0;
	}
	
	/*
	 * delete le client.
	 * parametre: int.
	 * returne rien.
	 */
	public void delete(int id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_QUERY);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	///important///
	/*
	 * trouve le client.
	 * parametre: int.
	 * returne le client.
	 */
	public Optional<Client> findById(int id) throws DaoException {
	
		try {
			Connection conn=ConnectionManager.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(FIND_CLIENT_QUERY);
			
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			
			rs.next();
			

			String clientLastname=rs.getString("nom");
			String clientFirstname=rs.getString("prenom");
			String clientemail=rs.getString("email");
			LocalDate clientBirthdate=rs.getDate("naissance").toLocalDate();

			Client client=new Client(
				id,clientLastname,clientFirstname,clientemail,clientBirthdate);
			return Optional.of(client);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		
		
		return Optional.empty();
	}
	/*
	 * trouve les clients.
	 * parametre: rien.
	 * returne les clients.
	 */
	public List<Client> findAll() throws DaoException {
		ArrayList list_clients = new ArrayList();
		 try {
			Connection conn=ConnectionManager.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(FIND_CLIENTS_QUERY);
			
			ResultSet rs=pstmt.executeQuery();
				
				while (rs.next()){
					
					int clientID=rs.getInt("id");
					String clientLastname=rs.getString("nom");
					String clientFirstname=rs.getString("prenom");
					String clientemail=rs.getString("email");
					LocalDate clientBirthdate=rs.getDate("naissance").toLocalDate();

					Client client=new Client();
					client.setId(clientID);
					client.setLastname(clientLastname);
					client.setEmail(clientemail);
					client.setFirstname(clientFirstname);
					client.setBirthdate(clientBirthdate);
					
					list_clients.add(client);
				}
				

			
			
			
			return list_clients;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return list_clients;
		
		

	}
	/*
	 * compte les clients.
	 * parametre: rien.
	 * returne int.
	 */
	public int count() throws DaoException {
		int count = 0;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(COUNT_CLIENTS_QUERY);
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
	 * update le client.
	 * parametre: client.
	 * returne le client.
	 */
	public void update(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_CLIENT_QUERY);
			pstmt.setString(1, client.getLastname());
			pstmt.setString(2, client.getFirstname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthdate()));
			pstmt.setInt(5, client.getId());

			pstmt.execute();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//contraintes
	public boolean hasNoSameEmail(Client client) throws DaoException {
		boolean hasNoSameEmail = true;
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(FIND_EMAIL_QUERY);
			pstmt.setString(1, client.getEmail());
			pstmt.execute();

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {	
					hasNoSameEmail = false;
			}
			resultSet.close();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new DaoException("L'email choisit existe deja ");
		}
		return hasNoSameEmail;
	}
}
