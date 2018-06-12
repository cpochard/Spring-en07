package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.banque.dao.IDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IEntity;

/**
 * DAO abstrait.
 *
 * @param <T>
 *            Un type d'entite
 */
// Classe générique (<T extends IEntity>), donc quand on voudra hériter de la
// classe AbstractDAO elle devra une entité de type de DAO particulier qu'on
// spécifiera entre <>. Elle implémente aussi l'interface IDAO qui contient
// toutes les méthodes
@Repository("abstractDAO")
public abstract class AbstractDAO<T extends IEntity> implements IDAO<T> {

	private static final Logger LOG = LogManager.getLogger(AbstractDAO.class);

	@Value("${tmpDBdriver}")
	private String dbDriver;
	@Value("${tmpDBurl}")
	private String dbUrl;
	@Value("${tmpDBlogin}")
	private String dbLogin;
	@Value("${tmpDBpwd}")
	private String dbPwd;

	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbLogin() {
		return dbLogin;
	}

	public void setDbLogin(String dbLogin) {
		this.dbLogin = dbLogin;
	}

	public String getDbPwd() {
		return dbPwd;
	}

	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
	}

	/**
	 * Constructeur de l'objet.
	 */
	protected AbstractDAO() {
		super();
	}

	/**
	 * Retourne le nom de la table.
	 *
	 * @return le nom de la table.
	 */
	// Méthode abstraite. Attention : la première classe qui va hériter de cette
	// méthode abstraite devra redéfinir cette méthodes abstraite (mais pas les
	// méthodes concrètes)
	protected abstract String getTableName();

	/**
	 * Retourne la clef primaire de la table.
	 *
	 * @return la clef primaire de la table.
	 */
	protected String getPkName() {
		return "id";
	}

	/**
	 * Retourne la liste des colonnes de la table
	 *
	 * @return la liste des colonnes de la table
	 */
	protected abstract String getAllColumnNames();

	/**
	 * Transforme un resultset en objet
	 *
	 * @param rs
	 *            un resultset
	 * @return un objet a partir du resultset
	 * @throws SQLException
	 *             si un probleme survient
	 */
	// ResultSet = resultat d'une requete sql avec le jdbc
	protected abstract T fromResultSet(ResultSet rs) throws SQLException;

	/**
	 * A la responsabilite de creer un statement qui servira pour l'insertion.
	 *
	 * @param pUneEntite
	 *            une entite a inserer
	 * @param connexion
	 *            une connexion
	 * @return un statement adapte a l'insertion
	 * @throws SQLException
	 *             si un probleme survient
	 */
	protected abstract PreparedStatement buildStatementForInsert(T pUneEntite, Connection connexion)
			throws SQLException;

	/**
	 * A la responsabilite de creer un statement qui servira pour la mise a jour.
	 *
	 * @param pUneEntite
	 *            une entite a mettre a jour
	 * @param connexion
	 *            une connexion
	 * @return un statement adapte a la mise a jour
	 * @throws SQLException
	 *             si un probleme survient
	 */
	protected abstract PreparedStatement buildStatementForUpdate(T pUneEntite, Connection connexion)
			throws SQLException;

	/**
	 * Gere la fin de la transaction sur l'objet Connection.
	 *
	 * @param pConnexionCreated
	 *            indique si la connection est nouvelle ou non
	 * @param pDoCommit
	 *            indique si il faut commiter ou pas
	 * @param pStatement
	 *            le statement
	 * @param pResultSet
	 *            le resultset
	 * @param pConnexion
	 *            la connection
	 */
	public static void handleTransaction(boolean pConnexionCreated, boolean pDoCommit, Statement pStatement,
			ResultSet pResultSet, Connection pConnexion) {
		if (pConnexionCreated && pConnexion != null) {
			if (pDoCommit) {
				AbstractDAO.LOG.debug("-- commit");
				try {
					if (!pConnexion.getAutoCommit()) {
						pConnexion.commit();
					}
				} catch (SQLException e) {
					AbstractDAO.LOG.error("Impossible de faire un commit!", e);
				}
			} else {
				AbstractDAO.LOG.warn("-- rollback");
				try {
					if (!pConnexion.getAutoCommit()) {
						pConnexion.rollback();
					}
				} catch (SQLException e) {
					AbstractDAO.LOG.error("Impossible de faire un rollback!", e);
				}
			}
		}
		if (pResultSet != null) {
			try {
				pResultSet.close();
			} catch (SQLException e) {
				AbstractDAO.LOG.error("Impossible de fermer le resultset!", e);
			}
		}
		if (pStatement != null) {
			try {
				pStatement.close();
			} catch (SQLException e) {
				AbstractDAO.LOG.error("Impossible de fermer le statement!", e);
			}
		}
		if (pConnexionCreated && pConnexion != null) {
			try {
				pConnexion.close();
			} catch (SQLException e) {
				AbstractDAO.LOG.error("Impossible de fermer le connexion!", e);
			}

		}
	}

	@Override
	public T insert(T pUneEntite, Connection connexion) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		AbstractDAO.LOG.debug("Insert " + pUneEntite.getClass());
		boolean doCommit = false;
		boolean connexionCreated = connexion == null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
				// Autocommit pour mettre dans mySql (BDD)
				connexion.setAutoCommit(false);
			}
			ps = this.buildStatementForInsert(pUneEntite, connexion);
			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				pUneEntite.setId(Integer.valueOf(rs.getInt(1)));
			}
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, doCommit, ps, rs, connexion);
		}
		return pUneEntite;
	}

	@Override
	public T update(T pUneEntite, Connection connexion) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		AbstractDAO.LOG.debug("update " + pUneEntite.getClass());
		if (pUneEntite.getId() == null) {
			throw new ExceptionDao("L'entite n'a pas d'ID");
		}
		boolean doCommit = false;
		boolean connexionCreated = connexion == null;

		PreparedStatement ps = null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
				connexion.setAutoCommit(false);
			}

			ps = this.buildStatementForUpdate(pUneEntite, connexion);
			ps.execute();
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, doCommit, ps, null, connexion);
		}
		return pUneEntite;
	}

	@Override
	public boolean delete(T pUneEntite, Connection connexion) throws ExceptionDao {
		if (pUneEntite == null) {
			return false;
		}
		AbstractDAO.LOG.debug("delete " + pUneEntite.getClass());
		if (pUneEntite.getId() == null) {
			throw new ExceptionDao("L'entite n'a pas d'ID");
		}
		boolean doCommit = false;
		boolean connexionCreated = connexion == null;
		PreparedStatement ps = null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
				connexion.setAutoCommit(false);
			}

			String request = "delete from " + this.getTableName() + " where " + this.getPkName() + "=?;";
			ps = connexion.prepareStatement(request);
			ps.setInt(1, pUneEntite.getId().intValue());

			ps.execute();
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, doCommit, ps, null, connexion);
		}

		return doCommit;
	}

	@Override
	public T select(int pUneClef, Connection connexion) throws ExceptionDao {
		T result = null;
		AbstractDAO.LOG.debug("select sur " + this.getClass() + " avec id=" + String.valueOf(pUneClef));
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean connexionCreated = connexion == null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
				connexion.setReadOnly(true);
			}
			String request = "select " + this.getAllColumnNames() + " from " + this.getTableName() + " where "
					+ this.getPkName() + "=?;";
			ps = connexion.prepareStatement(request);
			ps.setInt(1, pUneClef);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = this.fromResultSet(rs);
			}

		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, true, ps, rs, connexion);
		}

		return result;
	}

	@Override
	public List<T> selectAll(String pAWhere, String pAnOrderBy, Connection connexion) throws ExceptionDao {
		List<T> result = new ArrayList<T>();
		AbstractDAO.LOG.debug("selectAll sur " + this.getClass() + " avec where=" + pAWhere + " prderBy=" + pAnOrderBy);
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean connexionCreated = connexion == null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
				connexion.setReadOnly(true);
			}

			StringBuilder request = new StringBuilder();
			request.append("select ").append(this.getAllColumnNames()).append(" from ");
			request.append(this.getTableName());
			if (pAWhere != null) {
				request.append(" where ");
				request.append(pAWhere);
			}
			if (pAnOrderBy != null) {
				request.append(" order by ");
				request.append(pAnOrderBy);
			}
			request.append(';');
			AbstractDAO.LOG.debug("selectAll sur " + this.getClass() + " - requete=" + request.toString());
			ps = connexion.prepareStatement(request.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				T ce = this.fromResultSet(rs);
				result.add(ce);
			}

		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, true, ps, rs, connexion);
		}

		return result;
	}

	@Override
	public final Connection getConnexion() throws ExceptionDao {
		try {
			Class.forName(dbDriver);
		} catch (Exception e) {
			AbstractDAO.LOG.error("Impossible de charger le driver pour la base", e);
			throw new ExceptionDao(e);
		}

		try {
			return DriverManager.getConnection(dbUrl, dbLogin, dbPwd);
		} catch (SQLException e) {
			AbstractDAO.LOG.error("Erreur lors de l'acces a la base", e);
			throw new ExceptionDao(e);
		}
	}

	/**
	 * Place les elements dans la requete.
	 *
	 * @param ps
	 *            la requete
	 * @param gaps
	 *            les elements
	 * @throws SQLException
	 *             si un des elements ne rentre pas
	 */
	protected static final void setPrepareStatement(PreparedStatement ps, List<?> gaps) throws SQLException {
		Iterator<?> iter = gaps.iterator();
		int id = 0;
		while (iter.hasNext()) {
			id++;
			Object lE = iter.next();
			if (lE == null) {
				continue;
			}
			if (lE instanceof String) {
				ps.setString(id, (String) lE);
			} else if (lE instanceof java.sql.Date) {
				ps.setDate(id, (java.sql.Date) lE);
			} else if (lE instanceof java.util.Date) {
				ps.setDate(id, new java.sql.Date(((java.util.Date) lE).getTime()));
			} else if (lE instanceof Timestamp) {
				ps.setTimestamp(id, (Timestamp) lE);
			} else if (lE instanceof Integer) {
				ps.setInt(id, ((Integer) lE).intValue());
			} else if (lE instanceof Double) {
				ps.setDouble(id, ((Double) lE).doubleValue());
			} else {
				throw new SQLException("Type invalid '" + lE.getClass().getSimpleName() + "'");
			}

		}
	}

}