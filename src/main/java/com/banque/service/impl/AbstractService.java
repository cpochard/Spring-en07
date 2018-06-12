package com.banque.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Classe parente de tous les services.
 */
@Service("abstractService")
abstract class AbstractService {
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(AbstractService.class);

	/**
	 * Constructeur de l'objet.
	 */
	protected AbstractService() {
		super();
	}
}
