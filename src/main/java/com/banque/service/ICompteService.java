package com.banque.service;

import java.util.List;

import com.banque.entity.ICompteEntity;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;

/**
 * Represente un service qui va gerer les comptes.
 */
public interface ICompteService {

	/**
	 * Recupere un compte.
	 *
	 * @param unUtilisateurId
	 *            un id d'utilisateur
	 * @param unCompteId
	 *            un id de compte
	 * @return le compte trouve
	 * @throws FonctionnelleException
	 *             si un probleme metier survient
	 * @throws ErreurTechniqueException
	 *             si un probleme technique survient
	 */
	public abstract ICompteEntity select(int unUtilisateurId, int unCompteId)
			throws FonctionnelleException, ErreurTechniqueException;

	/**
	 * Recupere tous les comptes d'un utilisateur.
	 *
	 * @param unUtilisateurId
	 *            un id d'utilisateur
	 * @return les comptes trouves, une liste vide si aucun
	 * @throws FonctionnelleException
	 *             si un probleme metier survient
	 * @throws ErreurTechniqueException
	 *             si un probleme technique survient
	 */
	public abstract List<ICompteEntity> selectAll(int unUtilisateurId)
			throws FonctionnelleException, ErreurTechniqueException;

}