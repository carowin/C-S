package org.entreprise;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employe
 *
 */
@Entity

public class Employe implements Serializable {

	   
	@Id
	private String nom;
	private double salaire;
	private static final long serialVersionUID = 1L;

	public Employe() {
		super();
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}   
	public double getSalaire() {
		return this.salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
   
}
