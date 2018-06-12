package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IOperationEntity;
import com.banque.entity.impl.OperationEntity;

/**
 * Gestion des operations.
 */
@Repository("operationDAO")
public class OperationDAO extends AbstractDAO<IOperationEntity> implements IOperationDAO {
	private static final Logger LOG = LogManager.getLogger(OperationDAO.class);

	/**
	 * Constructeur de l'objet.
	 */
	public OperationDAO() {
		super();
	}

	@Override
	protected String getTableName() {
		return "operation";
	}

	@Override
	protected IOperationEntity fromResultSet(ResultSet rs) throws SQLException {
		IOperationEntity result = new OperationEntity();
		result.setId(Integer.valueOf(rs.getInt("id")));
		result.setLibelle(rs.getString("libelle"));
		double vm = rs.getDouble("montant");
		// Le montant etait-il null ?
		boolean mnull = rs.wasNull();
		if (!mnull) {
			result.setMontant(Double.valueOf(vm));
		} else {
			result.setMontant(null);
		}
		result.setDate(rs.getTimestamp("date"));
		result.setCompteId(Integer.valueOf(rs.getInt("compteId")));
		return result;
	}

	@Override
	protected String getAllColumnNames() {
		return "id,libelle,montant,date,compteId";
	}

	@Override
	protected PreparedStatement buildStatementForInsert(IOperationEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "insert into " + this.getTableName() + " (libelle, montant, date, compteId) values (?,?,?,?);";
		PreparedStatement ps = connexion.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, pUneEntite.getLibelle());
		ps.setDouble(2, pUneEntite.getMontant().doubleValue());
		ps.setTimestamp(3, pUneEntite.getDate());
		ps.setInt(4, pUneEntite.getCompteId().intValue());
		return ps;
	}

	@Override
	protected PreparedStatement buildStatementForUpdate(IOperationEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "update " + this.getTableName() + " set libelle=?, montant=?, date=?, compteId=? where "
				+ this.getPkName() + "=?;";
		PreparedStatement ps = connexion.prepareStatement(request);
		ps.setString(1, pUneEntite.getLibelle());
		ps.setDouble(2, pUneEntite.getMontant().doubleValue());
		ps.setTimestamp(3, pUneEntite.getDate());
		ps.setInt(4, pUneEntite.getCompteId().intValue());
		ps.setInt(5, pUneEntite.getId().intValue());
		return ps;
	}

	@Override
	public List<IOperationEntity> selectCriteria(int unCompteId, Date unDebut, Date uneFin, Boolean pCreditDebit,
			Connection connexion) throws ExceptionDao {
		List<IOperationEntity> result = new ArrayList<IOperationEntity>();
		OperationDAO.LOG.debug("selectCriteria sur " + this.getClass() + " unCompteId=" + String.valueOf(unCompteId)
				+ " unDebut=" + String.valueOf(unDebut) + " uneFin=" + String.valueOf(uneFin) + " pCreditDebit="
				+ String.valueOf(pCreditDebit));
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
			request.append(" where compteId=?");
			List<Object> gaps = new ArrayList<Object>();
			gaps.add(Integer.valueOf(unCompteId));
			if (unDebut != null && uneFin == null) {
				request.append(" and date >= ?");
				gaps.add(unDebut);
			}

			if (uneFin != null && unDebut == null) {
				// Probleme sur la date de fin car MySQL a des dates en
				// 2016-08-26 18:38:22
				// Mais nous on lui donne des date en 2016-08-26 00:00:00
				// Du coup, on doit gerer la date de fin en faisant +1 jour
				// Le < (et pas <=) evite d'avoir l'operation du lendemain a
				// 00:00:00
				request.append(" and date < DATE_ADD(?, INTERVAL 1 DAY)");
				gaps.add(uneFin);
			}

			if (uneFin != null && unDebut != null) {
				// Probleme sur la date de fin car MySQL a des dates en
				// 2016-08-26 18:38:22
				// Mais nous on lui donne des date en 2016-08-26 00:00:00
				// Du coup, on doit gerer la date de fin en faisant +1 jour
				// Le < (et pas <=) evite d'avoir l'operation du lendemain a
				// 00:00:00
				request.append(" and date >= ? and date < DATE_ADD(?, INTERVAL 1 DAY)");
				gaps.add(unDebut);
				gaps.add(uneFin);
			}

			if (pCreditDebit != null) {
				if (pCreditDebit.booleanValue()) {
					request.append(" and montant >= 0");
				} else {
					request.append(" and montant <= 0");
				}
			}

			request.append(" order by date DESC");

			request.append(';');
			OperationDAO.LOG.debug("selectCriteria sur " + this.getClass() + " requete=" + request.toString());
			ps = connexion.prepareStatement(request.toString());
			AbstractDAO.setPrepareStatement(ps, gaps);

			rs = ps.executeQuery();
			while (rs.next()) {
				result.add(this.fromResultSet(rs));
			}
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, true, ps, rs, connexion);
		}

		return result;
	}

}