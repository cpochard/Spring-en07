package com.banque.dao;

import java.sql.Connection;
import java.util.List;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IEntity;

/**
 * Interface mere de tous les DAO. <br/>
 * Ne pas oublier de changer le mot de passe si necessaire.
 *
 * @param <T>
 *            Un type d'entite.
 */
public interface IDAO<T extends IEntity> {
	/** Driver SQL. */
	// public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * URL de la base de donnees. En MySQL >= 5.7 les options evitent un warning.
	 */
	// public static final String DB_URL =
	// "jdbc:mysql://localhost/banque?useSSL=false";
	/** Login de la base. */
	// public static final String DB_LOGIN = "root";
	/** Password de la base. */
	// public static final String DB_PWD = "";

	/**
	 * Ajoute un element dans la base.
	 *
	 * @param pUneEntite
	 *            un element
	 * @param connexion
	 *            une connection
	 * @return l'objet ajoute
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract T insert(T pUneEntite, Connection connexion) throws ExceptionDao;

	/**
	 * Met un element a jour dans la base.
	 *
	 * @param pUneEntite
	 *            un element
	 * @param connexion
	 *            une connection
	 * @return l'objet mis a jour
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract T update(T pUneEntite, Connection connexion) throws ExceptionDao;

	/**
	 * Suprime un compte de la base.
	 *
	 * @param pUneEntite
	 *            un objet compte
	 * @param connexion
	 *            une connection
	 * @return true si il a ete supprime, false sinon
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract boolean delete(T pUneEntite, Connection connexion) throws ExceptionDao;

	/**
	 * Selectionne un compte dans la base en fonction de sa clef.
	 *
	 * @param pUneClef
	 *            une clef
	 * @param connexion
	 *            une connection
	 * @return l'objet compte trouve, null sinon
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract T select(int pUneClef, Connection connexion) throws ExceptionDao;

	/**
	 * Selectionne tous les comptes dans la base.
	 *
	 * @param pAWhere
	 *            une condition where (sans le mot clef where)
	 * @param pAnOrderBy
	 *            une condition d'order by (sans le mot clef order by)
	 * @param connexion
	 *            une connection
	 * @return la liste des comptes trouves, une liste vide sinon
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract List<T> selectAll(String pAWhere, String pAnOrderBy, Connection connexion) throws ExceptionDao;

	/**
	 * Recupere une connexion
	 *
	 * @return une connexion
	 * @throws ExceptionDao
	 */
	public abstract Connection getConnexion() throws ExceptionDao;

}