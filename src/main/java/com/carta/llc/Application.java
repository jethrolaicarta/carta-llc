package com.carta.llc;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.carta.llc.data.dao.EntityADao;
import com.carta.llc.data.dao.EntityADaoH2Impl;
import com.carta.llc.data.dao.EntityADaoRestImpl;
import com.carta.llc.data.model.EntityAStatus;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Entrypoint of the application
 *
 * @author Jethro Lai
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ConfigurableEnvironment env;

	@Value("${llc.db.useInMemoryDb:true}")
	private Boolean useInMemoryDb;

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("entityADao")
	private EntityADao entityADao;

	@Bean
	@DependsOn({ "jdbcTemplate" })
	@Qualifier("entityADao")
	public EntityADao setUpEntityADao() {
		EntityADao entityADao;
		if (useInMemoryDb) {
			entityADao = new EntityADaoH2Impl();
			((EntityADaoH2Impl) entityADao).setJdbcTemplate(jdbcTemplate);
			if (((EntityADaoH2Impl) entityADao).getJdbcTemplate() == null) {
				throw new IllegalStateException("jdbcTemplate is not injected successfully.");
			}
		} else {
			entityADao = new EntityADaoRestImpl();

		}
		logger.info(String.format("EntityADao (%s) bean has been inialized", entityADao.getClass().getName()));

		return entityADao;
	}

	@PostConstruct
	public void insertEntityASeedData() {
		// insert seed data for health check
		entityADao.upsert(Constants.SEED_ENTITYA_UUID, EntityAStatus.NOT_VERIFIED.name(), new Date(), "no detail");

		logger.info(String.format("Upserted seed EntityA data with uuid (%s) for database health check",
				Constants.SEED_ENTITYA_UUID));

	}

	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("com.carta.llc.api.controller"))
					.paths(PathSelectors.any()).build();
		}
	}
}
