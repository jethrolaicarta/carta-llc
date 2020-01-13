package com.carta.llc.core.data.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carta.llc.core.data.model.Entitlement;

/**
 * @author jlai
 */
@Repository
public class EntitlementDaoH2Impl implements EntitlementDao<Entitlement> {

	private static final String ENTITLEMENT_TABLE_NAME = "entitlement";
	final private Logger logger = LoggerFactory.getLogger(EntitlementDaoH2Impl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Entitlement get(final String id) {

		if (StringUtils.isBlank(id)) {
			return null;
		}

		List<Entitlement> entity = jdbcTemplate.query(
				String.format("SELECT * FROM %s WHERE uuid = ?", ENTITLEMENT_TABLE_NAME), new Object[] { id },
				(rs, rowNum) -> Entitlement.builder().id(rs.getString("id")).build());

		Entitlement result = entity == null || entity.isEmpty() ? null : entity.get(0);
		return result;
	}

	@Override
	public Entitlement create(Entitlement entity) {
		jdbcTemplate.update(String.format("INSERT INTO %s VALUES (?, ?)", ENTITLEMENT_TABLE_NAME), entity.getId(),
				System.currentTimeMillis());
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
	public Entitlement upcert(Entitlement entity) {
		return null;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

}
