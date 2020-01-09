package com.carta.llc.test.inttest;

import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.carta.llc.Application;
import com.carta.llc.data.dao.EntityADao;
import com.carta.llc.data.model.EntityA;
import com.carta.llc.test.IntegrationTest;
import com.google.gson.JsonObject;

/**
 * *
 * 
 * @author jlai TODO change test port to a different one from default one
 */
@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = Application.class)
public class EntityA_IntegrationTest {

	private static final Logger logger = LoggerFactory.getLogger(EntityA_IntegrationTest.class);

	private final static String TEST_ENDPOINT = "/entity/a";

	@Autowired
	@Qualifier("entityADao")
	private EntityADao entityADao;

	private Random random = new Random();

	@Autowired
	private TestRestTemplate template = new TestRestTemplate();

	@Test
	public void postEntityA_nullBody() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> response = template.postForEntity(TEST_ENDPOINT, new HttpEntity<String>(null, headers),
				String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void postEntityA_emptyBody() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> response = template.postForEntity(TEST_ENDPOINT, new HttpEntity<String>("", headers),
				String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void postEntityA_notFound() {
		final String Uuid = this.getClass().getName() + "-" + random.nextLong();
		final String status = "NOT_VERIFIED";

		EntityA entity = entityADao.findByUuid(Uuid);
		Assert.assertNull(entity);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JsonObject request = new JsonObject();
		request.addProperty("Uuid", Uuid);
		request.addProperty("status", status);

		ResponseEntity<String> response = template.postForEntity(TEST_ENDPOINT,
				new HttpEntity<String>(request.toString(), headers), String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

		entity = entityADao.findByUuid(Uuid);
		Assert.assertNotNull(entity);
		Assert.assertEquals(status, entity.getStatus());
		Assert.assertEquals(Uuid, entity.getUuid());
	}

	@Test
	public void postEntityA_missingStatus() {
		final String Uuid = this.getClass().getName() + "-" + random.nextLong();

		EntityA status = entityADao.findByUuid(Uuid);
		Assert.assertNull(status);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JsonObject request = new JsonObject();
		request.addProperty("Uuid", Uuid);

		ResponseEntity<String> response = template.postForEntity(TEST_ENDPOINT,
				new HttpEntity<String>(request.toString(), headers), String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void postEntityA_missingUuid() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JsonObject request = new JsonObject();
		request.addProperty("status", "SKIPPED");

		ResponseEntity<String> response = template.postForEntity(TEST_ENDPOINT,
				new HttpEntity<String>(request.toString(), headers), String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void postEnrollmentVerifyIdentity_success() {
		final String uuid = this.getClass().getName() + "-" + random.nextLong();
		final String status = "SKIPPED";

		EntityA entity = new EntityA(uuid, status, new Date(), "n/a", new Date());

		entityADao.upsert(entity);

		entity = entityADao.findByUuid(uuid);
		Assert.assertNotNull(entity);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JsonObject request = new JsonObject();
		request.addProperty("Uuid", uuid);
		request.addProperty("status", status);

		ResponseEntity<String> response = template.postForEntity(TEST_ENDPOINT,
				new HttpEntity<String>(request.toString(), headers), String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

		entity = entityADao.findByUuid(uuid);
		Assert.assertNotNull(entity);
		Assert.assertEquals(status, entity.getStatus());
		Assert.assertEquals(uuid, entity.getUuid());
	}

	@Test
	public void getEntityA_success() {
		final String uuid = this.getClass().getName() + "-" + random.nextLong();
		final String status = "SKIPPED";
		final Date date = new Date();

		EntityA entity = new EntityA(uuid, status, date, "n/a", date);

		entityADao.upsert(entity);

		entity = entityADao.findByUuid(uuid);
		Assert.assertNotNull(entity);

		ResponseEntity<String> response = template.getForEntity(TEST_ENDPOINT + "/" + uuid, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

		entity = entityADao.findByUuid(uuid);
		Assert.assertNotNull(entity);
		Assert.assertEquals(status, entity.getStatus());
		Assert.assertEquals(uuid, entity.getUuid());
	}

	@Test
	public void getEntityA_notFound() {
		final String Uuid = this.getClass().getName() + "-" + random.nextLong();

		EntityA evs = entityADao.findByUuid(Uuid);
		Assert.assertNull(evs);

		ResponseEntity<String> response = template.getForEntity(TEST_ENDPOINT + "/" + Uuid, String.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

		evs = entityADao.findByUuid(Uuid);
		Assert.assertNull(evs);
	}
}
