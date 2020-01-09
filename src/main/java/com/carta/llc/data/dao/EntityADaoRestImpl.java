package com.carta.llc.data.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carta.llc.data.model.EntityA;

/**
 * @author jlai
 */
@Repository
public class EntityADaoRestImpl implements EntityADao {

	private static final String ENTITY_A_TABLE_NAME = "entity_a";
	final private Logger logger = LoggerFactory.getLogger(EntityADaoRestImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public EntityA findByUuid(final String uuid) {

		if (StringUtils.isBlank(uuid)) {
			return null;
		}

		List<EntityA> entityAs = jdbcTemplate.query(
				String.format("SELECT * FROM %s WHERE uuid = ?", ENTITY_A_TABLE_NAME), new Object[] { uuid },
				(rs, rowNum) -> new EntityA(rs.getString("uuid"), rs.getString("status"), rs.getDate("created_date"),
						rs.getString("detail"), rs.getDate("updated_date")));

		EntityA result = entityAs == null || entityAs.isEmpty() ? null : entityAs.get(0);
		return result;
	}

	@Override
	public void upsert(String uuid, String status, Date createdDate, String detail) {
		jdbcTemplate.update(String.format("MERGE INTO %s KEY(uuid) VALUES (?,?,?,?,?, ?);", ENTITY_A_TABLE_NAME), uuid,
				status, new Timestamp(createdDate.getTime()), detail, new Timestamp(new Date().getTime()));
	}

	@Override
	public void upsert(EntityA entityA) {
		jdbcTemplate.update(String.format("MERGE INTO %s KEY(uuid) VALUES (?,?,?,?,?,?);", ENTITY_A_TABLE_NAME),
				entityA.getUuid(), entityA.getStatus(), new Timestamp(entityA.getCreatedDate().getTime()),
				entityA.getDetail(), new Timestamp(new Date().getTime()));
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<EntityA> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
