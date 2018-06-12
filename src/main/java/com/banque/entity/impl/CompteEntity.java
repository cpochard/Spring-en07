package com.banque.entity.impl;

import com.banque.entity.ICompteEntity;

/**
 * Le bean qui represente un Compte. <br>
 */
public class CompteEntity extends AbstractEntity implements ICompteEntity {

	private static final long serialVersionUID = 1L;

	private String libelle;
	private Double solde;
	private Double decouvert;
	private Double taux;

	private Integer utilisateurId;

	/**
	 * Constructeur de l'objet. <br>
	 */
	public CompteEntity() {
		this(null, null, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            l'id d'un compte
	 */
	public CompteEntity(Integer unId) {
		this(unId, null, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            l'id d'un compte
	 * @param unLibelle
	 *            le libelle du compte
	 * @param unSolde
	 *            le solde du compte
	 * @param unDecouvert
	 *            le decouvert du compte
	 * @param unTaux
	 *            un taux
	 */
	public CompteEntity(Integer unId, String unLibelle, Double unSolde, Double unDecouvert, Double unTaux) {
		super(unId);
		this.setLibelle(unLibelle);
		this.setSolde(unSolde);
		this.setDecouvert(unDecouvert);
		this.setTaux(unTaux);
	}

	@Override
	public Double getTaux() {
		return this.taux;
	}

	@Override
	public void setTaux(Double taux) {
		this.taux = taux;
	}

	@Override
	public Integer getUtilisateurId() {
		return this.utilisateurId;
	}

	@Override
	public void setUtilisateurId(Integer pUtilisateurId) {
		this.utilisateurId = pUtilisateurId;
	}

	@Override
	public Double getDecouvert() {
		return this.decouvert;
	}

	@Override
	public String getLibelle() {
		return this.libelle;
	}

	@Override
	public Double getSolde() {
		return this.solde;
	}

	@Override
	public void setDecouvert(Double unDecouvert) {
		this.decouvert = unDecouvert;
	}

	@Override
	public void setLibelle(String unLibelle) {
		if (unLibelle == null || unLibelle.trim().length() == 0) {
			this.libelle = null;
		} else {
			this.libelle = unLibelle;
		}
	}

	@Override
	public void setSolde(Double unSolde) {
		this.solde = unSolde;
	}

	@Override
	protected String asString() {
		StringBuilder sb = new StringBuilder();
		sb.append("libelle=");
		sb.append(this.getLibelle());
		sb.append(",solde=");
		sb.append(this.getSolde());
		sb.append(",decouvert=");
		sb.append(this.getDecouvert());
		sb.append(",taux=");
		sb.append(this.getTaux());
		sb.append(",utilisateurId=");
		sb.append(this.getUtilisateurId());
		return sb.toString();
	}
}