package com.carta.llc.core.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.carta.llc.core.service.EntitlementService;

/**
 * health check endpoint. Any custom health check logic can be added in this
 * class Spring boot actuactor and management have default healthcheck endpoint.
 * This class should only be used for custom health check implementation
 *
 */
public class HealthGRPCController {
	private static final Logger logger = LoggerFactory.getLogger(HealthGRPCController.class);

	@Autowired
	@Qualifier("entitlementService")
	private EntitlementService entitlementService;

}
