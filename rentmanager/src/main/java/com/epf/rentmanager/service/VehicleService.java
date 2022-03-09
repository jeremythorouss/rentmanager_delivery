package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	

	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	public int create(Vehicle vehicle) throws ServiceException 
	{
		if (!vehicle.hasProperModel()) 
		{
			throw new ServiceException("Le modele du vehicule n'est pas bon");
		} 
		else if (!vehicle.hasProperConstructeur()) 
		{
			throw new ServiceException("Le constructeur du vehicule n'est pas bon");
		} 
		else if (!vehicle.hasProperNbPlaces())
		{
			throw new ServiceException("Le nombre de places du vehicule n'est pas bon");
		}
		else 
		{
			try 
			{
				return vehicleDao.create(vehicle);
			}
			catch(DaoException e) {
					e.printStackTrace();
			}
		
		}
		return 0;
	}
	///important///
	public Vehicle findById(int id) throws ServiceException {
		try {
			return this.vehicleDao.findById(id).get();
		}
		catch(DaoException e) {
				e.printStackTrace();
		}
			return null;
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		}
		catch(DaoException e) {
				e.printStackTrace();
		}
			return null;

	}
	public long delete(int id) throws ServiceException {
		try {
			return this.vehicleDao.delete(id);
		}
		catch(DaoException e) {
				e.printStackTrace();
		}
		return 0;
	}
	public void update(Vehicle vehicle) throws ServiceException {
		try {
			String nomMarque = vehicle.getConstructeur().toUpperCase();
			vehicle.setConstructeur(nomMarque);
			vehicleDao.update(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
}

	public int count() throws ServiceException {
		int count = 0;
		try {
			count = vehicleDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return count;
	}
	
}

