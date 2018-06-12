package com.banque.entity.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;

/**
 * Le bean qui represente un utilisateur. <br>
 */
public class UtilisateurEntity extends AbstractEntity implements IUtilisateurEntity {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private Integer codePostal;
	private java.sql.Date dateDeNaissance;
	private ESex sex;
	private Timestamp derniereConnection;

	/**
	 * Constructeur de l'objet. <br>
	 */
	public UtilisateurEntity() {
		this(null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            l'id d'un utilisateur
	 */
	public UtilisateurEntity(Integer unId) {
		super(unId);
	}

	@Override
	public ESex getSex() {
		return this.sex;
	}

	@Override
	public void setSex(ESex pSex) {
		this.sex = pSex;
	}

	@Override
	public String getLogin() {
		return this.login;
	}

	@Override
	public void setLogin(String pLogin) {
		this.login = pLogin;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String pPassword) {
		this.password = pPassword;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public void setNom(String pNom) {
		this.nom = pNom;
	}

	@Override
	public String getPrenom() {
		return this.prenom;
	}

	@Override
	public void setPrenom(String pPrenom) {
		this.prenom = pPrenom;
	}

	@Override
	public Timestamp getDerniereConnection() {
		return this.derniereConnection;
	}

	@Override
	public void setDerniereConnection(Timestamp pDerniereConnection) {
		this.derniereConnection = pDerniereConnection;
	}

	@Override
	public String getAdresse() {
		return this.adresse;
	}

	@Override
	public void setAdresse(String pAdresse) {
		if (pAdresse == null || pAdresse.trim().isEmpty()) {
			this.adresse = null;
		} else {
			this.adresse = pAdresse;
		}
	}

	@Override
	public String getTelephone() {
		return this.telephone;
	}

	@Override
	public void setTelephone(String pTelephone) {
		if (pTelephone == null || pTelephone.trim().isEmpty()) {
			this.telephone = null;
		} else {
			this.telephone = pTelephone;
		}
	}

	@Override
	public Integer getCodePostal() {
		return this.codePostal;
	}

	@Override
	public void setCodePostal(Integer pCodePostal) {
		this.codePostal = pCodePostal;
	}

	@Override
	public java.sql.Date getDateDeNaissance() {
		return this.dateDeNaissance;
	}

	@Override
	public void setDateDeNaissance(java.sql.Date pDateDeNaissance) {
		this.dateDeNaissance = pDateDeNaissance;
	}

	@Override
	public void setDateDeNaissance(int unJour, int unMois, int uneAnnee) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, uneAnnee);
		// Mois commence a zero
		calendar.set(Calendar.MONTH, unMois - 1);
		calendar.set(Calendar.DAY_OF_MONTH, unJour);
		this.setDateDeNaissance(new java.sql.Date(calendar.getTimeInMillis()));
	}

	@Override
	protected String asString() {
		StringBuilder sb = new StringBuilder();
		sb.append("sex=");
		sb.append(this.getSex());
		sb.append(",nom=");
		sb.append(this.getNom());
		sb.append(",prenom=");
		sb.append(this.getPrenom());
		sb.append(",login=");
		sb.append(this.getLogin());
		sb.append(",adresse=");
		sb.append(this.getAdresse());
		sb.append(",codePostal=");
		sb.append(this.getCodePostal());
		sb.append(",telephone=");
		sb.append(this.getTelephone());
		sb.append(",dateDeNaissance=");
		sb.append(this.getDateDeNaissance());
		sb.append(",derniereConnection=");
		sb.append(this.getDerniereConnection());

		return sb.toString();
	}
}