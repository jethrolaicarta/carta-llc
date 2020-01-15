package com.carta.llc.core.data.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.carta.llc.core.data.dao.EntitlementDao;
import com.carta.llc.core.data.model.Entitlement;

/**
 * @author jlai
 */
public class EntitlementDaoSQLImpl implements EntitlementDao<Entitlement> {

	private static final String ENTITLEMENT_TABLE_NAME = "entitlement";
	final private Logger logger = LoggerFactory.getLogger(EntitlementDaoSQLImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Entitlement get(final String id) {

		if (StringUtils.isBlank(id)) {
			return null;
		}

		List<Entitlement> entity = jdbcTemplate.query(
				String.format("SELECT * FROM %s WHERE id = ?", ENTITLEMENT_TABLE_NAME), new Object[] { id },
				(rs, rowNum) -> Entitlement.builder().id(rs.getString("id")).build());

		Entitlement result = entity == null || entity.isEmpty() ? null : entity.get(0);
		return result;
	}

	@Override
	public Entitlement create(Entitlement entity) {
		jdbcTemplate.update(String.format("INSERT INTO %s VALUES (?, ?);", ENTITLEMENT_TABLE_NAME), entity.getId(),
				new Timestamp(System.currentTimeMillis()));
		return null;
	}

	@Override
	public Entitlement update(Entitlement entity) {

		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Entitlement upsert(Entitlement entity) {
		return null;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

}
