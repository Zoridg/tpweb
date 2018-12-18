package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the annuaire database table.
 * 
 */
@Entity
@NamedQuery(name="Annuaire.findAll", query="SELECT a FROM Annuaire a")
public class Annuaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer num;

	private String fonction;

	private String nom;

	private String prenom;

	private String sexe;

	private Integer tel;

	public Annuaire() {
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getFonction() {
		return this.fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Integer getTel() {
		return this.tel;
	}

	public void setTel(Integer tel) {
		this.tel = tel;
	}
	
	public String toStringTab() {
		return    "<td>" + num + "</td>"
				+ "<td>" + nom + "</td>"
				+ "<td>" + prenom + "</td>"
				+ "<td>" + sexe + "</td>"
				+ "<td>" + tel + "</td>"
				+ "<td>" + fonction + "</td>";
	}
	
	public static String toStringColumnTab() {
		return    "<th>Num</th>"
				+ "<th>Nom</th>"
				+ "<th>Prenom</th>"
				+ "<th>Sexe</th>"
				+ "<th>Tel</th>"
				+ "<th>Fonction</th>";
	}
	
	public List<Annuaire> findByCriteria(EntityManager em){
		TypedQuery<Annuaire> query = em.createQuery(
				"SELECT a FROM Annuaire a "
				+ "WHERE a.nom LIKE :nom "
				+ "AND a.prenom LIKE :prenom "
				+ "AND a.sexe LIKE :sexe "
				+ "AND a.fonction LIKE :fonction"
				, Annuaire.class);
		
		query.setParameter("nom", this.getNom() == null ? "%%" : "%" + this.getNom() + "%");
		query.setParameter("prenom", this.getPrenom() == null ? "%%" : "%" + this.getPrenom() + "%");
		query.setParameter("sexe", this.getSexe() == null ? "%%" : "%" + this.getSexe() + "%");
		query.setParameter("fonction", this.getFonction() == null ? "%%" : "%" + this.getFonction() + "%");
		
		return query.getResultList();
	}

}