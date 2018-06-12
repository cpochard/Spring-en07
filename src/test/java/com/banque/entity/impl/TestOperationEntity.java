package com.banque.entity.impl;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;

/**
 * Test sur la classe IOperationEntity.
 */
public class TestOperationEntity {

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
		IOperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompteId(Integer.valueOf(1));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		IOperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(5));
		uneOp2.setCompteId(Integer.valueOf(1));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertTrue("Les deux operations sont egales", uneOp1.equals(uneOp2));
		Assert.assertTrue("Les deux operations sont egales", uneOp2.equals(uneOp1));
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo() {
		IOperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompteId(Integer.valueOf(1));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		IOperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(15));
		uneOp2.setCompteId(Integer.valueOf(1));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertFalse("Les deux operations sont differents", uneOp1.equals(uneOp2));
		Assert.assertFalse("Les deux operations sont differents", uneOp2.equals(uneOp1));
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo1() {
		IOperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompteId(Integer.valueOf(1));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		IUtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertFalse("Une operation n'est pas un utilisateur", uneOp1.equals(unObj));
		Assert.assertFalse("Un utilisateur n'est pas une operation", unObj.equals(uneOp1));
	}

	/**
	 * Test
	 */
	@Test
	public void testEqualsKo2() {
		IOperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompteId(Integer.valueOf(1));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		ICompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertFalse("Une operation n'est pas un compte", uneOp1.equals(unObj));
		Assert.assertFalse("Un compte n'est pas une operation", unObj.equals(uneOp1));
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeOk() {
		IOperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompteId(Integer.valueOf(1));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		IOperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(5));
		uneOp2.setCompteId(Integer.valueOf(1));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertEquals("Les deux operations ont le meme hashcode", uneOp1.hashCode(), uneOp2.hashCode());
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo() {
		IOperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompteId(Integer.valueOf(1));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		IOperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(15));
		uneOp2.setCompteId(Integer.valueOf(1));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertNotEquals("Les deux operations n'ont pas le meme hashcode", uneOp1.hashCode(), uneOp2.hashCode());
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo1() {
		IOperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompteId(Integer.valueOf(1));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		ICompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode de operation ne doit pas etre identique a celui d'un compte",
				uneOp1.hashCode(), unObj.hashCode());
	}

	/**
	 * Test
	 */
	@Test
	public void testHashcodeKo2() {
		IOperationEntity uneOp1 = new OperationEntity();
		uneOp1.setId(Integer.valueOf(5));
		uneOp1.setCompteId(Integer.valueOf(1));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));

		IUtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'une operation",
				uneOp1.hashCode(), unObj.hashCode());
	}
}
