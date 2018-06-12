package com.banque.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.banque.dao.ICompteDAO;
import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.impl.AbstractDAO;
import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.impl.OperationEntity;
import com.banque.service.IOperationService;
import com.banque.service.ex.AucunDroitException;
import com.banque.service.ex.DecouvertException;
import com.banque.service.ex.EntityIntrouvableException;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;

/**
 * Gestion des operations.
 */
@Service("operationService")
public class OperationService extends AbstractService implements IOperationService {
	private static final Logger LOG = LogManager.getLogger(OperationService.class);

	@Autowired
	@Qualifier("operationDAO")
	private IOperationDAO operationDao;

	@Autowired
	@Qualifier("compteDAO")
	private ICompteDAO compteDao;

	/**
	 * Constructeur de l'objet.
	 */
	public OperationService() {
		super();
	}

	/**
	 * Recupere la propriete <i>compteDao</i>.
	 *
	 * @return the compteDao la valeur de la propriete.
	 */
	protected ICompteDAO getCompteDao() {
		return this.compteDao;
	}

	public void setCompteDao(ICompteDAO compteDao) {
		this.compteDao = compteDao;
	}

	/**
	 * Recupere la propriete <i>operationDao</i>.
	 *
	 * @return the operationDao la valeur de la propriete.
	 */
	protected IOperationDAO getOperationDao() {
		return this.operationDao;
	}

	public void setOperationDao(IOperationDAO operationDao) {
		this.operationDao = operationDao;
	}

	@Override
	public IOperationEntity select(int unUtilisateurId, int unCompteId, int uneOperationId)
			throws FonctionnelleException, ErreurTechniqueException {
		OperationService.LOG.debug("select operation uId=" + String.valueOf(unUtilisateurId) + " cpId="
				+ String.valueOf(unCompteId) + " opId=" + String.valueOf(uneOperationId));

		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteId < 0) {
			throw new IllegalArgumentException("compteId<0");
		}
		if (uneOperationId < 0) {
			throw new IllegalArgumentException("operationId<0");
		}

		// On verifie que le compte appartient bien a l'utilisateur
		ICompteEntity compte = null;
		try {
			compte = this.getCompteDao().select(unCompteId, null);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (compte == null) {
			throw new EntityIntrouvableException();
		}
		if (unUtilisateurId != compte.getUtilisateurId().intValue()) {
			throw new AucunDroitException();
		}

		IOperationEntity resultat = null;
		try {
			resultat = this.getOperationDao().select(uneOperationId, null);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (resultat == null) {
			throw new EntityIntrouvableException();
		}
		if (unCompteId != resultat.getCompteId().intValue()) {
			throw new AucunDroitException();
		}
		OperationService.LOG.debug("select operation resultat=" + resultat);

		return resultat;
	}

	@Override
	public List<IOperationEntity> selectAll(int unUtilisateurId, int unCompteId)
			throws FonctionnelleException, ErreurTechniqueException {
		OperationService.LOG.debug(
				"selectAll operation uId=" + String.valueOf(unUtilisateurId) + " cpId=" + String.valueOf(unCompteId));

		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteId < 0) {
			throw new IllegalArgumentException("compteId<0");
		}

		ICompteEntity compte;
		try {
			compte = this.getCompteDao().select(unCompteId, null);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (compte == null) {
			throw new EntityIntrouvableException();
		}
		if (unUtilisateurId != compte.getUtilisateurId().intValue()) {
			throw new AucunDroitException();
		}

		List<IOperationEntity> resultat = new ArrayList<IOperationEntity>();
		try {
			resultat = this.getOperationDao().selectAll("compteId=" + unCompteId, "date DESC", null);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		OperationService.LOG.debug("selectAll trouve " + String.valueOf(resultat.size()) + " operation(s)");
		return resultat;
	}

	@Override
	public List<IOperationEntity> selectCritere(int unUtilisateurId, int unCompteId, Date unDebut, Date uneFin,
			boolean pCredit, boolean pDebit) throws FonctionnelleException, ErreurTechniqueException {
		OperationService.LOG.debug("selectCritere operation uId=" + String.valueOf(unUtilisateurId) + " cpId="
				+ String.valueOf(unCompteId) + " debut=" + unDebut + " fin=" + uneFin + " credit="
				+ String.valueOf(pCredit) + " debit=" + String.valueOf(pDebit));

		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteId < 0) {
			throw new IllegalArgumentException("compteId<0");
		}
		Boolean crediDebit = null;
		if (pCredit && !pDebit) {
			crediDebit = Boolean.TRUE;
		} else if (!pCredit && pDebit) {
			crediDebit = Boolean.FALSE;
		}
		List<IOperationEntity> resultat = new ArrayList<IOperationEntity>();
		try {
			resultat = this.getOperationDao().selectCriteria(unCompteId, unDebut, uneFin, crediDebit, null);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		OperationService.LOG.debug("selectCritere trouve " + String.valueOf(resultat.size()) + " operation(s)");
		return resultat;
	}

	@Override
	public List<IOperationEntity> faireVirement(int unUtilisateurId, int unCompteIdSrc, int unCompteIdDst,
			double unMontant) throws FonctionnelleException, ErreurTechniqueException {
		OperationService.LOG.debug("faireVirement uId=" + String.valueOf(unUtilisateurId) + " cpIdSrc="
				+ String.valueOf(unCompteIdSrc) + " cpIdDest=" + String.valueOf(unCompteIdDst));

		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteIdSrc < 0) {
			throw new IllegalArgumentException("compteIdSrc<0");
		}
		if (unCompteIdDst < 0) {
			throw new IllegalArgumentException("compteIdDst<0");
		}
		if (unMontant < 0) {
			throw new IllegalArgumentException("montant<0");
		}
		List<IOperationEntity> resultat = new ArrayList<IOperationEntity>(2);
		IOperationEntity opSrc = null;
		IOperationEntity opDst = null;
		Connection connexion = null;
		boolean doCommit = false;
		try {
			connexion = this.getCompteDao().getConnexion();
			connexion.setAutoCommit(false);
			ICompteEntity compteSrc = null;
			try {
				compteSrc = this.getCompteDao().select(unCompteIdSrc, connexion);
			} catch (ExceptionDao e) {
				throw new ErreurTechniqueException(e);
			}
			if (compteSrc == null) {
				throw new EntityIntrouvableException();
			}
			if (unUtilisateurId != compteSrc.getUtilisateurId().intValue()) {
				throw new AucunDroitException();
			}
			ICompteEntity compteDst = null;
			try {
				compteDst = this.getCompteDao().select(unCompteIdDst, connexion);
			} catch (ExceptionDao e) {
				throw new ErreurTechniqueException(e);
			}
			if (compteDst == null) {
				throw new EntityIntrouvableException();
			}
			if (unUtilisateurId != compteDst.getUtilisateurId().intValue()) {
				throw new AucunDroitException();
			}

			double montant = unMontant;
			// Simulation
			double soldeSrc = compteSrc.getSolde().doubleValue();
			final double decouvertSrc = compteSrc.getDecouvert() != null ? compteSrc.getDecouvert().doubleValue()
					: Double.MIN_VALUE;
			double soldeDst = compteDst.getSolde().doubleValue();
			final double decouvertDst = compteDst.getDecouvert() != null ? compteDst.getDecouvert().doubleValue()
					: Double.MIN_VALUE;

			// On retire de la source
			soldeSrc -= montant;
			// On ajoute a destination
			soldeDst += montant;
			// On regarde si les decouverts suivent
			if (soldeSrc <= decouvertSrc || soldeDst <= decouvertDst) {
				throw new DecouvertException();
			}

			Timestamp now = new Timestamp(System.currentTimeMillis());
			opSrc = new OperationEntity();
			opSrc.setCompteId(Integer.valueOf(unCompteIdSrc));
			opSrc.setDate(now);
			opSrc.setMontant(Double.valueOf(-montant));
			opSrc.setLibelle("Transaction avec le compte " + unCompteIdDst);

			opDst = new OperationEntity();
			opDst.setCompteId(Integer.valueOf(unCompteIdDst));
			opDst.setDate(now);
			opDst.setMontant(Double.valueOf(unMontant));
			opDst.setLibelle("Transaction avec le compte " + unCompteIdSrc);

			opSrc = this.getOperationDao().insert(opSrc, connexion);
			opDst = this.getOperationDao().insert(opDst, connexion);
			compteSrc.setSolde(Double.valueOf(soldeSrc));
			compteDst.setSolde(Double.valueOf(soldeDst));
			this.getCompteDao().update(compteSrc, connexion);
			this.getCompteDao().update(compteDst, connexion);

			resultat.add(opSrc);
			resultat.add(opDst);
			doCommit = true;
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		} catch (SQLException e) {
			throw new ErreurTechniqueException(e);
		} finally {
			AbstractDAO.handleTransaction(true, doCommit, null, null, connexion);
		}
		return resultat;
	}
}