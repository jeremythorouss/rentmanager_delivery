package com.epf.rentmanager.model;

public class Vehicle {
	
private int id;
private String constructeur;
private String model;
private int nb_places;
public Vehicle() {

}
public Vehicle(int id, String constructeur, String model, int nb_places) {
	super();
	this.id = id;
	this.constructeur = constructeur;
	this.model = model;
	this.nb_places = nb_places;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getConstructeur() {
	return constructeur;
}
public void setConstructeur(String constructeur) {
	this.constructeur = constructeur;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public int getNb_places() {
	return nb_places;
}
public void setNb_places(int nb_places) {
	this.nb_places = nb_places;
}
public Vehicle( String constructeur, String model, int nb_places) {
	this.constructeur = constructeur;
	this.model = model;
	this.nb_places = nb_places;
}

//contrainte

public boolean hasProperModel(){
    boolean hasProperModele = false;
    if (this.getModel()!=null) {
        hasProperModele = true;
    }
    return hasProperModele;
}



public boolean hasProperConstructeur(){
    boolean hasProperConstructeur = false;
    if (this.getConstructeur()!=null) {
        hasProperConstructeur = true;
    }
    return hasProperConstructeur;
}



public boolean hasProperNbPlaces(){
    boolean hasProperNbPlaces = false;
    nb_places=this.getNb_places();
    if (nb_places>2 && nb_places<9 ) {
        hasProperNbPlaces = true;
    }
    return hasProperNbPlaces;
}


@Override
public String toString() {
	return "Vehicle [id=" + id + ", constructeur=" + constructeur + ", model=" + model + ", nb_places=" + nb_places  + "]";
}

	
	
}
