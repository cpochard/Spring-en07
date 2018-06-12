package com.banque.entity;

import java.sql.Timestamp;

/**
 * Represente un utilisateur.
 */
public interface IUtilisateurEntity extends IEntity {

	/**
	 * Recupere la propriete <i>sex</i>.
	 *
	 * @return la valeur de la propriete (voir ESex).
	 */
	public abstract ESex getSex();

	/**
	 * Fixe la propriete <i>sex</i>.
	 *
	 * @param pSex
	 *            la nouvelle valeur pour la propriete sex (voir ESex).
	 */
	public abstract void setSex(ESex pSex);

	/**
	 * Recupere la propriete <i>login</i>.
	 *
	 * @return la valeur de la propriete.
	 */
	public abstract String getLogin();

	/**
	 * Fixe la propriete <i>login</i>.
	 *
	 * @param pLogin
	 *            la nouvelle valeur pour la propriete login.
	 */
	public abstract void setLogin(String pLogin);

	/**
	 * Recupere la propriete <i>password</i>.
	 *
	 * @return la valeur de la propriete.
	 */
	public abstract String getPassword();

	/**
	 * Fixe la propriete <i>password</i>.
	 *
	 * @param pPassword
	 *            la nouvelle valeur pour la propriete password.
	 */
	public abstract void setPassword(String pPassword);

	/**
	 * Recupere la propriete <i>nom</i>.
	 *
	 * @return la valeur de la propriete.
	 */
	public abstract String getNom();

	/**
	 * Fixe la propriete <i>nom</i>.
	 *
	 * @param pNom
	 *            la nouvelle valeur pour la propriete nom.
	 */
	public abstract void setNom(String pNom);

	/**
	 * Recupere la propriete <i>prenom</i>.
	 *
	 * @return la valeur de la propriete.
	 */
	public abstract String getPrenom();

	/**
	 * Fixe la propriete <i>prenom</i>.
	 *
	 * @param pPrenom
	 *            la nouvelle valeur pour la propriete prenom.
	 */
	public abstract void setPrenom(String pPrenom);

	/**
	 * Recupere la propriete <i>derniereConnection</i>.
	 *
	 * @return la valeur de la propriete.
	 */
	public abstract Timestamp getDerniereConnection();

	/**
	 * Fixe la propriete <i>derniereConnection</i>.
	 *
	 * @param pDerniereConnection
	 *            la nouvelle valeur pour la propriete derniereConnection.
	 */
	public abstract void setDerniereConnection(Timestamp pDerniereConnection);

	/**
	 * Recupere la propriete <i>adresse</i>.
	 *
	 * @return la valeur de la propriete.
	 */
	public abstract String getAdresse();

	/**
	 * Fixe la propriete <i>adresse</i>.
	 *
	 * @param pAdresse
	 *            la nouvelle valeur pour la propriete adresse.
	 */
	public abstract void setAdresse(String pAdresse);

	/**
	 * Recupere la propriete <i>telephone</i>.
	 *
	 * @return la valeur de la propriete.
	 */
	public abstract String getTelephone();

	/**
	 * Fixe la propriete <i>telephone</i>.
	 *
	 * @param pTelephone
	 *            la nouvelle valeur pour la propriete telephone.
	 */
	public abstract void setTelephone(String pTelephone);

	/**
	 * Recupere la propriete <i>codePostal</i>.
	 *
	 * @return la valeur de la propriete.
	 */
	public abstract Integer getCodePostal();

	/**
	 * Fixe la propriete <i>codePostal</i>.
	 *
	 * @param pCodePostal
	 *            la nouvelle valeur pour la propriete codePostal.
	 */
	public abstract void setCodePostal(Integer pCodePostal);

	/**
	 * Recupere la propriete <i>dateDeNaissance</i>.
	 *
	 * @return la valeur de la propriete.
	 */
	public abstract java.sql.Date getDateDeNaissance();

	/**
	 * Fixe la propriete <i>dateDeNaissance</i>.
	 *
	 * @param pDateDeNaissance
	 *            la nouvelle valeur pour la propriete dateDeNaissance.
	 */
	public abstract void setDateDeNaissance(java.sql.Date pDateDeNaissance);

	/**
	 * Fixe la propriete <i>dateDeNaissance</i>.
	 *
	 * @param unJour
	 *            jour dans le mois de naissance (1 a 31)
	 * @param unMois
	 *            mois de naissance (1 a 12)
	 * @param uneAnnee
	 *            annee de naissance (ex: 2005)
	 */
	public abstract void setDateDeNaissance(int unJour, int unMois, int uneAnnee);

}