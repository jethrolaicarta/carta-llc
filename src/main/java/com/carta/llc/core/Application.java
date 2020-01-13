package com.carta.llc.core;

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

import com.carta.llc.core.data.dao.EntitlementDao;
import com.carta.llc.core.data.dao.EntitlementDaoH2Impl;
import com.carta.llc.core.data.dao.EntitlementDaoRestImpl;
import com.carta.llc.core.data.model.Entitlement;

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

//	@Value("${llc.db.useInMemoryDb:true}")
//	private Boolean useInMemoryDb;
//
//	@Autowired
//	@Qualifier("jdbcTemplate")
//	private JdbcTemplate jdbcTemplate;
//
//	@Autowired
//	@Qualifier("entitlementDao")
//	private EntitlementDao entitlementDao;
//
//	@Bean
//	@DependsOn({ "jdbcTemplate" })
//	@Qualifier("entitlementDao")
//	public EntitlementDao setUpEntitlementDao() {
//		EntitlementDao entitlementDao;
//		if (useInMemoryDb) {
//			entitlementDao = new EntitlementDaoH2Impl();
//			((EntitlementDaoH2Impl) entitlementDao).setJdbcTemplate(jdbcTemplate);
//			if (((EntitlementDaoH2Impl) entitlementDao).getJdbcTemplate() == null) {
//				throw new IllegalStateException("jdbcTemplate is not injected successfully.");
//			}
//		} else {
//			entitlementDao = new EntitlementDaoRestImpl();
//
//		}
//		logger.info(String.format("EntityADao (%s) bean has been inialized", entitlementDao.getClass().getName()));
//
//		return entitlementDao;
//	}

//	@PostConstruct
//	public void insertSeedData() {
//		// insert seed data for health check
//		Entitlement entitlement = Entitlement.builder().id(Constants.SEED_ENTITLEMENT_ID).build();
//		entitlementDao.create(entitlement);
//
//		logger.info(String.format("Upserted seed Entitlement data with id (%s) for database health check",
//				Constants.SEED_ENTITLEMENT_ID));
//	}

	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("com.carta.llc.core.api.controller"))
					.paths(PathSelectors.any()).build();
		}
	}
}
