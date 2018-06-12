package com.banque.service;

import com.banque.entity.IUtilisateurEntity;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;

/**
 * Service d'authentification.
 */
public interface IAuthentificationService {

	/**
	 * Authentifie un utilisateur.
	 *
	 * @param pLogin
	 *            le login
	 * @param pPassword
	 *            le password
	 * @return l'utilisateur authentifie, leve une exception sinon
	 * @throws FonctionnelleException
	 *             si un probleme metier survient
	 * @throws ErreurTechniqueException
	 *             si un probleme technique survient
	 */
	public abstract IUtilisateurEntity authentifier(String pLogin, String pPassword)
			throws FonctionnelleException, ErreurTechniqueException;

}