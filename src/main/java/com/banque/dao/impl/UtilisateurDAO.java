package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.UtilisateurEntity;

/**
 * Gestion des utilisateurs.
 */
@Repository("utilisateurDAO")
public class UtilisateurDAO extends AbstractDAO<IUtilisateurEntity> implements IUtilisateurDAO {
	private static final Logger LOG = LogManager.getLogger(UtilisateurDAO.class);

	/**
	 * Constructeur de l'objet.
	 */
	public UtilisateurDAO() {
		super();
	}

	@Override
	protected String getTableName() {
		return "utilisateur";
	}

	@Override
	protected String getAllColumnNames() {
		return "id,nom,prenom,login,password,sex,derniereConnection,dateDeNaissance,adresse,codePostal,telephone";
	}

	@Override
	protected IUtilisateurEntity fromResultSet(ResultSet rs) throws SQLException {
		IUtilisateurEntity result = new UtilisateurEntity();
		result.setId(Integer.valueOf(rs.getInt("id")));
		result.setNom(rs.getString("nom"));
		result.setPrenom(rs.getString("prenom"));
		result.setLogin(rs.getString("login"));
		result.setPassword(rs.getString("password"));
		// Gestion de l'enumeration
		byte sex = rs.getByte("sex");
		switch (sex) {
		case 0:
			result.setSex(ESex.HOMME);
			break;
		case 1:
			result.setSex(ESex.FEMME);
			break;
		default:
			UtilisateurDAO.LOG.warn("Sex a une valeur surprenante (" + sex + ")");
			result.setSex(ESex.AUTRE);
			break;
		}
		result.setDerniereConnection(rs.getTimestamp("derniereConnection"));
		result.setAdresse(rs.getString("adresse"));
		int cp = rs.getInt("codePostal");
		if (rs.wasNull()) {
			result.setCodePostal(null);
		} else {
			result.setCodePostal(Integer.valueOf(cp));
		}
		result.setTelephone(rs.getString("telephone"));
		result.setDateDeNaissance(rs.getDate("dateDeNaissance"));
		return result;
	}

	@Override
	protected PreparedStatement buildStatementForInsert(IUtilisateurEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "insert into " + this.getTableName()
				+ " (nom,prenom,login,password,sex,derniereConnection,dateDeNaissance,adresse,codePostal,telephone) values (?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = connexion.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, pUneEntite.getNom());
		ps.setString(2, pUneEntite.getPrenom());
		ps.setString(3, pUneEntite.getLogin());
		ps.setString(4, pUneEntite.getPassword());
		if (pUneEntite.getSex().equals(ESex.HOMME)) {
			ps.setByte(5, (byte) 0);
		} else if (pUneEntite.getSex().equals(ESex.FEMME)) {
			ps.setByte(5, (byte) 1);
		} else {
			ps.setByte(5, (byte) 2);
		}
		ps.setTimestamp(6, pUneEntite.getDerniereConnection());
		ps.setDate(7, pUneEntite.getDateDeNaissance());
		ps.setString(8, pUneEntite.getAdresse());
		if (pUneEntite.getCodePostal() != null) {
			ps.setInt(9, pUneEntite.getCodePostal().intValue());
		} else {
			ps.setNull(9, Types.INTEGER);
		}
		ps.setString(10, pUneEntite.getTelephone());
		return ps;
	}

	@Override
	@SuppressWarnings("squid:S2068")
	protected PreparedStatement buildStatementForUpdate(IUtilisateurEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "update " + this.getTableName()
				+ " set nom=?,prenom=?,login=?,password=?,sex=?,derniereConnection=?,dateDeNaissance=?,adresse=?,codePostal=?,telephone=? where "
				+ this.getPkName() + "=?;";
		PreparedStatement ps = connexion.prepareStatement(request);
		ps.setString(1, pUneEntite.getNom());
		ps.setString(2, pUneEntite.getPrenom());
		ps.setString(3, pUneEntite.getLogin());
		ps.setString(4, pUneEntite.getPassword());
		if (pUneEntite.getSex().equals(ESex.HOMME)) {
			ps.setByte(5, (byte) 0);
		} else if (pUneEntite.getSex().equals(ESex.FEMME)) {
			ps.setByte(5, (byte) 1);
		} else {
			ps.setByte(5, (byte) 2);
		}
		ps.setTimestamp(6, pUneEntite.getDerniereConnection());
		ps.setDate(7, pUneEntite.getDateDeNaissance());
		ps.setString(8, pUneEntite.getAdresse());
		if (pUneEntite.getCodePostal() != null) {
			ps.setInt(9, pUneEntite.getCodePostal().intValue());
		} else {
			ps.setNull(9, Types.INTEGER);
		}
		ps.setString(10, pUneEntite.getTelephone());
		ps.setInt(11, pUneEntite.getId().intValue());

		return ps;
	}

	@Override
	public IUtilisateurEntity selectLogin(String pLogin, Connection connexion) throws ExceptionDao {
		IUtilisateurEntity result = null;
		UtilisateurDAO.LOG.debug("selectLogin sur " + this.getClass() + " pLogin=" + pLogin);
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean connexionCreated = connexion == null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
				connexion.setReadOnly(true);
			}
			String request = "select " + this.getAllColumnNames() + " from " + this.getTableName() + " where login=?;";
			ps = connexion.prepareStatement(request);
			ps.setString(1, pLogin);
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

}