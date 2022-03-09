package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
@Service
public class ReservationService {

	private ReservationDao reservationDao;
	public static ReservationService instance;
	

	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	

	
	
	public long create(Reservation reservation) throws ServiceException {
		try {
			return reservationDao.create(reservation);
		}
		catch(DaoException e) 
		{
				e.printStackTrace();
		}
		return 0;
		}

	///important///
	public List<Reservation> findResaByClientId(int id) throws ServiceException {
		try {
			return this.reservationDao.findResaByClientId(id);
		}
		catch(DaoException e) {
				e.printStackTrace();
		}
			return null;
	}
	public List<Reservation> findResaByVehicleId(int id) throws ServiceException {
		try {
			return this.reservationDao.findResaByVehicleId(id);
		}
		catch(DaoException e) {
				e.printStackTrace();
		}
			return null;
	}
	
	public List<Reservation> findAll() throws ServiceException {
		try {
			return this.reservationDao.findAll();
		}
		catch(DaoException e) {
				e.printStackTrace();
		}
			return null;

	}
	public long delete(int id) throws ServiceException {
		try {
			reservationDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
}
	public int count() throws ServiceException {
		int count = 0;
		try {
			count = reservationDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return count;
	}
	public void update(Reservation reservation) throws ServiceException {
		try {
			
			reservationDao.update(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
}

	public Reservation findById(int idClient) throws ServiceException {
		try {
			return this.reservationDao.findById(idClient).get();
		}
		catch(DaoException e) {
				e.printStackTrace();
		}
			return null;
	}

}
