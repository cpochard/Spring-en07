package com.banque.entity;

/**
 * Interface qui represente un Compte. <br>
 */
public interface ICompteEntity extends IEntity {

	/**
	 * Recupere la propriete taux. <br/>
	 *
	 * @return la propriete taux
	 */
	public abstract Double getTaux();

	/**
	 * Modifie la propriete taux. <br/>
	 *
	 * @param taux
	 *            la nouvelle valeur de la propriete taux
	 */
	public abstract void setTaux(Double taux);

	/**
	 * Recupere la propriete <i>utilisateurId</i>.
	 *
	 * @return the utilisateurId la valeur de la propriete.
	 */
	public abstract Integer getUtilisateurId();

	/**
	 * Fixe la propriete <i>utilisateurId</i>.
	 *
	 * @param pUtilisateurId
	 *            la nouvelle valeur pour la propriete utilisateurId.
	 */
	public abstract void setUtilisateurId(Integer pUtilisateurId);

	/**
	 * Recupere le decouvert du compte.
	 *
	 * @return le decouvert du compte.
	 */
	Double getDecouvert();

	/**
	 * Recupere le libelle du compte.
	 *
	 * @return le libelle du compte.
	 */
	public abstract String getLibelle();

	/**
	 * Recupere le solde du compte.
	 *
	 * @return le solde du compte.
	 */
	public abstract Double getSolde();

	/**
	 * Fixe le decouvert du compte.
	 *
	 * @param unDecouvert
	 *            le decouvert du compte.
	 */
	public abstract void setDecouvert(Double unDecouvert);

	/**
	 * Fixe le libelle du compte.
	 *
	 * @param unLibelle
	 *            le libelle du compte.
	 */
	public abstract void setLibelle(String unLibelle);

	/**
	 * Fixe le solde du compte.
	 *
	 * @param unSolde
	 *            le solde du compte.
	 */
	public abstract void setSolde(Double unSolde);

}