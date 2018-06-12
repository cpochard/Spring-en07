package com.banque.entity.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;

/**
 * Test sur la classe ICompteEntity.
 */
public class TestCompteEntity {

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
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		ICompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(5));
		unCpt2.setDecouvert(Double.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(Double.valueOf(5000D));
		unCpt2.setTaux(Double.valueOf(0.01D));
		unCpt2.setUtilisateurId(Integer.valueOf(1));

		Assert.assertTrue("Les deux comptes sont egaux", unCpt1.equals(unCpt2));
		Assert.assertTrue("Les deux comptes sont egaux", unCpt2.equals(unCpt1));
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo() {
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		ICompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(15));
		unCpt2.setDecouvert(Double.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(Double.valueOf(5000D));
		unCpt2.setTaux(Double.valueOf(0.01D));
		unCpt2.setUtilisateurId(Integer.valueOf(1));

		Assert.assertFalse("Les deux comptes sont differents", unCpt1.equals(unCpt2));
		Assert.assertFalse("Les deux comptes sont differents", unCpt2.equals(unCpt1));
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo1() {
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		IUtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertFalse("Un compte n'est pas un utilisateur", unCpt1.equals(unObj));
		Assert.assertFalse("Un utilisateur n'est pas un compte", unObj.equals(unCpt1));
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo2() {
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		IOperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertFalse("Un compte n'est pas une operation", unCpt1.equals(unObj));
		Assert.assertFalse("Une operation n'est pas un compte", unObj.equals(unCpt1));
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeOk() {
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		ICompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(5));
		unCpt2.setDecouvert(Double.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(Double.valueOf(5000D));
		unCpt2.setTaux(Double.valueOf(0.01D));
		unCpt2.setUtilisateurId(Integer.valueOf(1));

		Assert.assertEquals("Les deux comptes ont le meme hashcode", unCpt1.hashCode(), unCpt2.hashCode());
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo() {
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		ICompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(15));
		unCpt2.setDecouvert(Double.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(Double.valueOf(5000D));
		unCpt2.setTaux(Double.valueOf(0.01D));
		unCpt2.setUtilisateurId(Integer.valueOf(1));

		Assert.assertNotEquals("Les deux comptes n'ont pas le meme hashcode", unCpt1.hashCode(), unCpt2.hashCode());
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo1() {
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		IOperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode de compte ne doit pas etre identique a celui d'une operation",
				unCpt1.hashCode(), unObj.hashCode());
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo2() {
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setId(Integer.valueOf(5));
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		IUtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'un compte",
				unCpt1.hashCode(), unObj.hashCode());
	}
}
