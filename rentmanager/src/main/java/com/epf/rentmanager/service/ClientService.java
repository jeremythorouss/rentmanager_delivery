package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
@Service
public class ClientService {

	private  ClientDao clientDao;
	public static ClientService instance;
	

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	

	
	public long create(Client client) throws ServiceException {
		if (!client.isLegal()) 
		{
			throw new ServiceException("un client n'ayant pas 18ans ne peut pas être créé");
		}
		else if (!client.isLongFirst()) 
		{
			throw new ServiceException("le nom et le prénom d'un client doivent faire au moins 3 caractères");
		} 
		else if (!client.isLongLast()) 
		{
			throw new ServiceException("le nom et le prénom d'un client doivent faire au moins 3 caractères");
		}
		else if (!client.hasProperEmail()) 
		{
			throw new ServiceException("L'email propose n'est pas un email");
		}
		else 
		{
			try 
			{

			return clientDao.create(client);
			}
			catch(DaoException e) 
			{
				e.printStackTrace();
			}
		return 0;
		}
	}
	///important///
	public Client findById(int id) throws ServiceException {
		try 
		{
			return this.clientDao.findById(id).get();
		}
		catch(DaoException e) {
				e.printStackTrace();
		}
			return null;
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		}
		catch(DaoException e) {
				e.printStackTrace();
		}
			return null;

	}

	
	public void delete(int id) throws ServiceException {
			try {
				clientDao.delete(id);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void update(Client client) throws ServiceException 
	{
		if (!client.isLegal()) 
		{
			throw new ServiceException("un client n'ayant pas 18ans ne peut pas être créé");
		}
		else if (!client.isLongFirst()) 
		{
			throw new ServiceException("le nom et le prénom d'un client doivent faire au moins 3 caractères");
		} 
		else if (!client.isLongLast()) 
		{
			throw new ServiceException("le nom et le prénom d'un client doivent faire au moins 3 caractères");
		}
		else if (!client.hasProperEmail()) 
		{
			throw new ServiceException("L'email propose n'est pas un email");
		}
		else 
		{
			try 
			{
				String nomMaj = client.getLastname().toUpperCase();
				client.setLastname(nomMaj);
				clientDao.update(client);
			} 
			catch (DaoException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int count() throws ServiceException {
		int count = 0;
		try {
			count = clientDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return count;
	}
}
