package com.banque.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.entity.IOperationEntity;
import com.banque.service.IOperationService;

/**
 * Test sur la classe IOperationService.
 */
public class TestOperationService {
	private static final Logger LOG = LogManager.getLogger(TestOperationService.class);
	private static IOperationService operationService;

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void initLog() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
		TestOperationService.operationService = new OperationService();
	}

	/**
	 * Test
	 */
	@Test
	public void selectOk() {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		final int uneOperationId = 2;
		IOperationEntity op = null;
		try {
			op = TestOperationService.operationService.select(unUtilisateurId, unCompteId, uneOperationId);
		} catch (Exception e) {
			TestOperationService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", op);
		Assert.assertEquals("L'id de l'operation doit etre " + uneOperationId, op.getId().intValue(), uneOperationId);
		Assert.assertEquals("L'id du compte de l'operation doit etre " + unCompteId, op.getCompteId().intValue(),
				unCompteId);
	}

	/**
	 * Test
	 */
	@Test
	public void selectAllOk() {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		List<IOperationEntity> liste = null;
		try {
			liste = TestOperationService.operationService.selectAll(unUtilisateurId, unCompteId);
		} catch (Exception e) {
			TestOperationService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		for (IOperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertEquals("L'operation doit avoir un compte id = " + unCompteId,
					uneEntity.getCompteId().intValue(), unCompteId);
		}
	}

	/**
	 * Test
	 */
	@Test
	public void selectCritereOk() {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		List<IOperationEntity> liste = null;
		try {
			liste = TestOperationService.operationService.selectCritere(unUtilisateurId, unCompteId, null, null, true,
					false);
		} catch (Exception e) {
			TestOperationService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		for (IOperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertEquals("L'operation doit avoir un compte id = " + unCompteId,
					uneEntity.getCompteId().intValue(), unCompteId);
			Assert.assertNotNull("L'operation doit avoir un montant", uneEntity.getMontant());
			Assert.assertTrue("L'operation doit etre un credit", uneEntity.getMontant().doubleValue() > 0);
		}
	}

	/**
	 * Test
	 */
	@Test
	public void faireVirementOk() {
		final int unUtilisateurId = 1;
		final int unCompteSrcId = 12;
		final int unCompteDestId = 15;
		final double unMontant = 10d;
		List<IOperationEntity> liste = null;
		try {
			liste = TestOperationService.operationService.faireVirement(unUtilisateurId, unCompteSrcId, unCompteDestId,
					unMontant);
		} catch (Exception e) {
			TestOperationService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		Assert.assertEquals("La liste doit avoir deux elements ", liste.size(), 2);
		for (IOperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertNotNull("L'operation doit avoir un compte id non null", uneEntity.getCompteId());
			Assert.assertTrue("L'operation doit avoir un compte id = " + unCompteSrcId + " ou = " + unCompteDestId,
					uneEntity.getCompteId().intValue() == unCompteSrcId
							|| uneEntity.getCompteId().intValue() == unCompteDestId);
			Assert.assertNotNull("L'operation doit avoir un montant", uneEntity.getMontant());
			Assert.assertTrue("L'operation doit avoir un montant = " + unMontant,
					Math.abs(uneEntity.getMontant().doubleValue()) == unMontant);
		}
	}

}
