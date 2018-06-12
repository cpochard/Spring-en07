package com.banque.service;

import java.util.Date;
import java.util.List;

import com.banque.entity.IOperationEntity;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;

/**
 * Service des operations.
 */
public interface IOperationService {

	/**
	 * Recupere une operation.
	 *
	 * @param unUtilisateurId
	 *            un id d'utilisateur
	 * @param unCompteId
	 *            un id de compte
	 * @param uneOperationId
	 *            un id d'operation
	 *
	 * @return l'operation trouvee
	 * @throws FonctionnelleException
	 *             si un probleme metier survient
	 * @throws ErreurTechniqueException
	 *             si un probleme technique survient
	 */
	public abstract IOperationEntity select(int unUtilisateurId, int unCompteId, int uneOperationId)
			throws FonctionnelleException, ErreurTechniqueException;

	/**
	 * Recupere toutes les operations d'un compte.
	 *
	 * @param unUtilisateurId
	 *            un id d'utilisateur
	 * @param unCompteId
	 *            un id de compte
	 *
	 * @return les operations trouvees, une liste vide si aucunne.
	 * @throws FonctionnelleException
	 *             si un probleme metier survient
	 * @throws ErreurTechniqueException
	 *             si un probleme technique survient
	 */
	public abstract List<IOperationEntity> selectAll(int unUtilisateurId, int unCompteId)
			throws FonctionnelleException, ErreurTechniqueException;

	/**
	 * Recupere toutes les operations respectant les criteres donnees.
	 *
	 * @param unUtilisateurId
	 *            un id d'utilisateur
	 * @param unCompteId
	 *            un id de compte
	 * @param unDebut
	 *            une date de debut
	 * @param uneFin
	 *            une date de fin
	 * @param pCredit
	 *            si vrai remonte les credit
	 * @param pDebit
	 *            si vrai remmonte les debits
	 *
	 * @return les operations trouvees, une liste vide si aucunne.
	 * @throws FonctionnelleException
	 *             si un probleme metier survient
	 * @throws ErreurTechniqueException
	 *             si un probleme technique survient
	 */
	public abstract List<IOperationEntity> selectCritere(int unUtilisateurId, int unCompteId, Date unDebut, Date uneFin,
			boolean pCredit, boolean pDebit) throws FonctionnelleException, ErreurTechniqueException;

	/**
	 * Fait un virement entre deux comptes.
	 *
	 * @param unUtilisateurId
	 *            un id d'utilisateur
	 * @param unCompteIdSrc
	 *            compte source
	 * @param unCompteIdDst
	 *            compte destination
	 * @param unMontant
	 *            la somme +/-
	 *
	 * @return les deux operations crees par le virement
	 * @throws FonctionnelleException
	 *             si un probleme metier survient
	 * @throws ErreurTechniqueException
	 *             si un probleme technique survient
	 */
	public abstract List<IOperationEntity> faireVirement(int unUtilisateurId, int unCompteIdSrc, int unCompteIdDst,
			double unMontant) throws FonctionnelleException, ErreurTechniqueException;

}