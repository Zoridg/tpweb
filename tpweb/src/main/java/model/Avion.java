package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the avion database table.
 * 
 */
@Entity
@NamedQuery(name="Avion.findAll", query="SELECT a FROM Avion a")
public class Avion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer ano;

	private String compagnie;

	private Integer places;

	private String type;

	public Avion() {
	}

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getCompagnie() {
		return this.compagnie;
	}

	public void setCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}

	public Integer getPlaces() {
		return this.places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}