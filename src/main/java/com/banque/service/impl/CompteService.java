package com.banque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.banque.dao.ICompteDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ICompteEntity;
import com.banque.service.ICompteService;
import com.banque.service.ex.AucunDroitException;
import com.banque.service.ex.EntityIntrouvableException;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;

/**
 * Gestion des comptes.
 */
@Service("compteService")
public class CompteService extends AbstractService implements ICompteService {
	private static final Logger LOG = LogManager.getLogger(CompteService.class);

	@Autowired
	@Qualifier("compteDAO")
	private ICompteDAO compteDao;

	/**
	 * Constructeur de l'objet.
	 */
	public CompteService() {
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

	@Override
	public ICompteEntity select(int unUtilisateurId, int unCompteId)
			throws FonctionnelleException, ErreurTechniqueException {
		CompteService.LOG
				.debug("select compte uId=" + String.valueOf(unUtilisateurId) + " cpId=" + String.valueOf(unCompteId));
		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		if (unCompteId < 0) {
			throw new IllegalArgumentException("compteId<0");
		}
		ICompteEntity resultat = null;
		try {
			resultat = this.getCompteDao().select(unCompteId, null);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (resultat == null) {
			throw new EntityIntrouvableException();
		}

		if (unUtilisateurId != resultat.getUtilisateurId().intValue()) {
			throw new AucunDroitException();
		}
		CompteService.LOG.debug("select compte resultat=" + resultat);
		return resultat;
	}

	@Override
	public List<ICompteEntity> selectAll(int unUtilisateurId) throws FonctionnelleException, ErreurTechniqueException {
		CompteService.LOG.debug("selectAll compte uId=" + String.valueOf(unUtilisateurId));
		if (unUtilisateurId < 0) {
			throw new IllegalArgumentException("utilisateurId<0");
		}
		List<ICompteEntity> resultat = new ArrayList<ICompteEntity>();
		try {
			resultat = this.getCompteDao().selectAll("utilisateurId=" + unUtilisateurId, "libelle ASC", null);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		CompteService.LOG.debug("selectAll compte trouve " + String.valueOf(resultat.size()) + " compte(s)");
		return resultat;
	}
}