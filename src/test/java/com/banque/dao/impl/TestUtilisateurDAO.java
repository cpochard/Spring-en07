package com.banque.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.UtilisateurEntity;

/**
 * Test sur la classe UtilisateurDAO.
 */
public class TestUtilisateurDAO {
	private static final Logger LOG = LogManager.getLogger(TestUtilisateurDAO.class);

	private static IUtilisateurDAO utilisateurDao;

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void initLog() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
		TestUtilisateurDAO.utilisateurDao = new UtilisateurDAO();
	}

	/**
	 * Test
	 */
	@Test
	public void insertOk() {
		IUtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Smith");
		unUt1.setPrenom("Jhon");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(ESex.FEMME);
		unUt1.setAdresse("Quelque part dans le test");
		unUt1.setCodePostal(Integer.valueOf(78000));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date d = null;
		try {
			d = sdf.parse("1988/01/01");
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		unUt1.setDateDeNaissance(new java.sql.Date(d.getTime()));
		unUt1.setTelephone("0148759678");

		IUtilisateurEntity unUt2 = null;
		try {
			unUt2 = TestUtilisateurDAO.utilisateurDao.insert(unUt1, null);
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt2);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme libelle", unUt1.getNom(), unUt2.getNom());
	}

	/**
	 * Test
	 */
	@Test
	public void updateOk() {
		// Avant le delete nous devons l'inserer
		IUtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Nom test");
		unUt1.setPrenom("Prenom test");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(ESex.HOMME);

		try {
			unUt1 = TestUtilisateurDAO.utilisateurDao.insert(unUt1, null);
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());

		unUt1.setNom("Test is back");
		IUtilisateurEntity unUt2 = null;
		try {
			unUt2 = TestUtilisateurDAO.utilisateurDao.update(unUt1, null);
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt2);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme id", unUt1.getId(), unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme libelle", unUt1.getNom(), unUt2.getNom());
	}

	/**
	 * Test
	 */
	@Test
	public void deleteOk() {
		// Avant le delete nous devons l'inserer
		IUtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Nom test");
		unUt1.setPrenom("Prenom test");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(ESex.HOMME);

		try {
			unUt1 = TestUtilisateurDAO.utilisateurDao.insert(unUt1, null);
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());

		boolean resu = false;
		try {
			resu = TestUtilisateurDAO.utilisateurDao.delete(unUt1, null);
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertTrue("L'utilisateur a du etre supprime", resu);
	}

	/**
	 * Test
	 */
	@Test
	public void selectOk() {
		IUtilisateurEntity unUt1 = null;
		try {
			// le login df existe
			unUt1 = TestUtilisateurDAO.utilisateurDao.selectLogin("df", null);
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());
		Assert.assertEquals("L'utilisateur doit avoir un login = df", unUt1.getLogin(), "df");
	}

	/**
	 * Test
	 */
	@Test
	public void selectAllOk() {
		List<IUtilisateurEntity> liste = null;
		IUtilisateurEntity unUt1 = null;
		try {
			liste = TestUtilisateurDAO.utilisateurDao.selectAll("nom='Fargis'", null, null);
			unUt1 = liste.get(0);
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());

		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());
		Assert.assertEquals("L'utilisateur doit avoir un login = Fargis", unUt1.getNom(), "Fargis");
	}
}
