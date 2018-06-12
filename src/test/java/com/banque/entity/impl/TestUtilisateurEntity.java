package com.banque.entity.impl;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.entity.ESex;
import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;

/**
 * Test sur la classe IUtilisateurEntity.
 */
public class TestUtilisateurEntity {

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void initLog() {
		System.setProperty("log4j.configurationFile", "log4j-test.properties");
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsOk() {
		IUtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(ESex.HOMME);

		IUtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(5));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(ESex.HOMME);

		Assert.assertTrue("Les deux utilisateurs sont egaux", unClient1.equals(unClient2));
		Assert.assertTrue("Les deux utilisateurs sont egaux", unClient2.equals(unClient1));
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo() {
		IUtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(ESex.HOMME);

		IUtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(15));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(ESex.HOMME);

		Assert.assertFalse("Les deux utilisateurs sont differents", unClient1.equals(unClient2));
		Assert.assertFalse("Les deux utilisateurs sont differents", unClient2.equals(unClient1));
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo1() {
		IUtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(ESex.HOMME);

		IOperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertFalse("Un utilisateur n'est pas une operation", unClient1.equals(unObj));
		Assert.assertFalse("Une operation n'est pas un utilisateur", unObj.equals(unClient1));
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo2() {
		IUtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(ESex.HOMME);

		ICompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertFalse("Un utilisateur n'est pas un compte", unClient1.equals(unObj));
		Assert.assertFalse("Un compte n'est pas un utilisateur", unObj.equals(unClient1));
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeOk() {
		IUtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(ESex.HOMME);

		IUtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(5));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(ESex.HOMME);

		Assert.assertEquals("Les deux utilisateurs ont le meme hashcode", unClient1.hashCode(), unClient2.hashCode());
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo() {
		IUtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(ESex.HOMME);

		IUtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(15));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(ESex.HOMME);

		Assert.assertNotEquals("Les deux utilisateurs n'ont pas le meme hashcode", unClient1.hashCode(),
				unClient2.hashCode());
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo1() {
		IUtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(ESex.HOMME);

		IOperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'une operation",
				unClient1.hashCode(), unObj.hashCode());
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo2() {
		IUtilisateurEntity unClient1 = new UtilisateurEntity();
		unClient1.setId(Integer.valueOf(5));
		unClient1.setNom("Smith");
		unClient1.setPrenom("Jhon");
		unClient1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient1.setPassword("bonjour");
		unClient1.setSex(ESex.HOMME);

		ICompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'un compte",
				unClient1.hashCode(), unObj.hashCode());
	}
}
