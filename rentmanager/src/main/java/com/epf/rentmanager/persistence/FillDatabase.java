package com.epf.rentmanager.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.DeleteDbFiles;

import com.epf.rentmanager.persistence.ConnectionManager;

public class FillDatabase {


    public static void main(String[] args) throws Exception {
        try {
            DeleteDbFiles.execute("~", "RentManagerDatabase", true);
            insertWithPreparedStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement createPreparedStatement = null;
        System.out.println("ok on est dans la boucle");
        List<String> createTablesQueries = new ArrayList<>();
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Client(id INT primary key auto_increment, client_id INT, nom VARCHAR(100), prenom VARCHAR(100), email VARCHAR(100), naissance DATETIME)");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Vehicle(id INT primary key auto_increment, model VARCHAR(100),  constructeur VARCHAR(100), nb_places TINYINT(255))");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Reservation(id INT primary key auto_increment, client_id INT, foreign key(client_id) REFERENCES Client(id), vehicle_id INT, foreign key(vehicle_id) REFERENCES Vehicle(id), debut DATETIME, fin DATETIME)");

        try {
            connection.setAutoCommit(false);

            for (String createQuery : createTablesQueries) {
            	createPreparedStatement = connection.prepareStatement(createQuery);
	            createPreparedStatement.executeUpdate();
	            createPreparedStatement.close();
            }

            // Remplissage de la base avec des Vehicules et des Clients
            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO Vehicle(constructeur,model ,nb_places) VALUES('Renault','clio', 4)");
            stmt.execute("INSERT INTO Vehicle(constructeur,model ,nb_places) VALUES('Peugeot','clio', 4)");
            stmt.execute("INSERT INTO Vehicle(constructeur,model ,nb_places) VALUES('Seat','clio', 4)");
            stmt.execute("INSERT INTO Vehicle(constructeur,model ,nb_places) VALUES('Nissan','clio', 4)");
            
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Dupont', 'Jean', 'jean.dupont@email.com', '1988-01-22')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Morin', 'Sabrina', 'sabrina.morin@email.com', '1988-01-22')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Afleck', 'Steeve', 'steeve.afleck@email.com', '1988-01-22')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('Rousseau', 'Jacques', 'jacques.rousseau@email.com', '1988-01-22')");
                  
            stmt.execute(
                    "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES('1', '1', '2000-01-01', '2000-01-06')");
            stmt.execute(
                    "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES('1', '1', '2000-01-07', '2000-01-12')");
            stmt.execute(
                    "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES('4', '1', '2000-01-13', '2000-01-18')");
            stmt.execute(
                    "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES('4', '1', '2000-01-19', '2000-01-26')");
            stmt.execute(
                    "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES('1', '2', '2000-01-01', '2000-01-06')");
            stmt.execute(
                    "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES('1', '3', '2000-01-01', '2000-01-05')");
            stmt.execute(
                    "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES('2', '3', '2000-01-07', '2000-01-08')");
            stmt.execute(
                    "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES('2', '4', '2000-01-01', '2000-01-06')");
            stmt.execute(
                    "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES('3', '4', '2000-01-06', '2000-01-11')");

            
            connection.commit();
            System.out.println("Success!");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
