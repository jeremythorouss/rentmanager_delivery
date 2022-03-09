package com.epf.rentmanager.exception;

public class ServiceException extends Exception {
	ServiceException(){
		super("Exception au niveau du service");
	}
    public ServiceException(String message) {
        super(message);
    }
}
