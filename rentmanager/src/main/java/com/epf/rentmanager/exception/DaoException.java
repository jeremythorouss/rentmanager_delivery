package com.epf.rentmanager.exception;

public class DaoException extends Exception {
	public DaoException(){
		super("Exception dans la DAO");
	}
	 public DaoException(String message) {
	        super(message);
	    }
}
