package com.banque.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ex.MauvaisMotdepasseException;
import com.banque.service.ex.UtilisateurInconnuException;

/**
 * Test sur la classe IAuthentificationService.
 */
public class TestUtilisateurService {
	private static final Logger LOG = LogManager.getLogger(TestUtilisateurService.class);
	private static IAuthentificationService authentificationService;

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void initLog() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
		TestUtilisateurService.authentificationService = new AuthentificationService();
	}

	/**
	 * Test
	 */
	@Test
	public void testAuthentifierOk() {
		final String login = "df";
		final String pwd = "df";
		IUtilisateurEntity user = null;
		try {
			user = TestUtilisateurService.authentificationService.authentifier(login, pwd);
		} catch (Exception e) {
			TestUtilisateurService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", user);
		Assert.assertEquals("Le login de l'utilisateur est " + login, user.getLogin(), login);
	}

	/**
	 * Test
	 *
	 * @throws Exception
	 *             UtilisateurInconnuException attendue
	 */
	@Test(expected = UtilisateurInconnuException.class)
	public void testAuthentifierKo1() throws Exception {
		final String login = "dfd";
		final String pwd = "df";
		TestUtilisateurService.authentificationService.authentifier(login, pwd);
	}

	/**
	 * Test
	 *
	 * @throws Exception
	 *             MauvaisMotdepasseException attendue
	 */
	@Test(expected = MauvaisMotdepasseException.class)
	public void testAuthentifierKo2() throws Exception {
		final String login = "df";
		final String pwd = "dfd";
		TestUtilisateurService.authentificationService.authentifier(login, pwd);
	}

}
