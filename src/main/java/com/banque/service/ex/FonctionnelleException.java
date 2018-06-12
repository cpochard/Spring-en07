package com.banque.service.ex;

/**
 * Erreur fontionnelle.
 */
public class FonctionnelleException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public FonctionnelleException() {
		super();
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public FonctionnelleException(String pMessage) {
		super(pMessage);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public FonctionnelleException(Throwable pCause) {
		super(pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public FonctionnelleException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

}
