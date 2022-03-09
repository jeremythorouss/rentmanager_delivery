package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

	private int id;
	private int idClient;
	private int idVehicle;
	private LocalDate dateStart;
	private LocalDate datEnd;

	private String vehiclename;
	private String clientname;

	public Reservation() {

	}

	public Reservation(int id, String vehiclename, String clientname, LocalDate dateStart, LocalDate datEnd) {

		this.id = id;
		this.dateStart = dateStart;
		this.datEnd = datEnd;
		this.vehiclename = vehiclename;
		this.clientname = clientname;
	}


	public String getVehiclename() {
		return vehiclename;
	}

	public void setVehiclename(String vehiclename) {
		this.vehiclename = vehiclename;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reservation(int idClient, int idVehicle, LocalDate dateStart, LocalDate datEnd) {
		super();
		this.idClient = idClient;
		this.idVehicle = idVehicle;
		this.dateStart = dateStart;
		this.datEnd = datEnd;
	}

	public Reservation(int id, int idClient, int idVehicle, LocalDate dateStart, LocalDate datEnd) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.idVehicle = idVehicle;
		this.dateStart = dateStart;
		this.datEnd = datEnd;
	}

	public Reservation(String vehiclename, String clientname, LocalDate dateStart, LocalDate datEnd) {
		this.dateStart = dateStart;
		this.datEnd = datEnd;
		this.vehiclename = vehiclename;
		this.clientname = clientname;
	}
	
	

	public Reservation(int id,int idClient, int idVehicle, LocalDate dateStart, LocalDate datEnd, String vehiclename,
			String clientname) {
		super();
		this.id=id;
		this.idClient = idClient;
		this.idVehicle = idVehicle;
		this.dateStart = dateStart;
		this.datEnd = datEnd;
		this.vehiclename = vehiclename;
		this.clientname = clientname;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDate getDatEnd() {
		return datEnd;
	}

	public void setDatEnd(LocalDate datEnd) {
		this.datEnd = datEnd;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", idClient=" + idClient + ", idVehicle=" + idVehicle + ", dateStart="
				+ dateStart + ", datEnd=" + datEnd + ", vehiclename=" + vehiclename + ", clientname=" + clientname
				+ "]";
	}

}
