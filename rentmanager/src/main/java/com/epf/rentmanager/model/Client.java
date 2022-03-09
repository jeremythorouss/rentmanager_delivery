package com.epf.rentmanager.model;

import java.time.*;
public class Client {
	private int id; 
	private String lastname;
	private String firstname;
	private String email;
	private LocalDate birthdate;
	
	public Client() {
		
	}

	
	public Client(int id, String lastname, String firstname, String email, LocalDate birthdate) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.birthdate = birthdate;
	}
	public Client(String lastname, String firstname, String email, LocalDate birthdate) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.birthdate = birthdate;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}


	@Override
	public String toString() {
		return "Client [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", email=" + email + ", birthdate="
				+ birthdate + "]";
	}

//contrainte
	public int getAge() {
		LocalDate now=LocalDate.now();
		return Period.between(this.birthdate, now).getYears();
	}

	
	/** 
	 * Verifie si le Client a plus de 18 ans
	 * @return boolean / True si age > 18 / False sinon
	 */
	public boolean isLegal() {
		return this.getAge() >= 18;
	}

	
	/** 
	 * Verifie si le Client Ã  un prenom de plus de 3 lettre
	 * @return boolean / True si le prenom du Client Ã  plus de 3 chars / False sinon
	 */
	public boolean isLongFirst() {
		return this.getFirstname().length() >= 3;
	}

	
	/** 
	 * Verifie si le Client Ã  un nom de plus de 3 lettre
	 * @return boolean / True si le nom du Client Ã  plus de 3 chars / False sinon
	 */
	public boolean isLongLast() {
		return this.getLastname().length() >= 3;
	}
	
	/** 
	 * Verifie si l'email contient un @.
	 * @return boolean / True si email contient @ / False sinon
	 */
	public boolean hasProperEmail() {
		return this.getEmail().contains("@");
	}

}
