package com.kiwi.insurance.spl.domain.repository;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.kiwi.insurance.spl.domain.model.Config;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = {
	"spring.datasource.initialize=false"
})
@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/create-config-schema.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop-config-schema.sql")
})
public class ConfigRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ConfigRepository configRepository;
	
	@Test
	public void testFindByConfigType() {
		// Given
		Config config1 = new Config();
		config1.setConfigType("Country");
		config1.setCode("MY");	
		config1.setDescription("Malaysia");
		config1.setSequence(1);
		config1.setActive(true);
		config1.setCreatedBy("SYSTEM");
		config1.setCreatedDate(new Date());
		entityManager.persist(config1);
		
		Config config2 = new Config();
		config2.setConfigType("Country");
		config2.setCode("SG");
		config2.setDescription("Singapore");
		config2.setSequence(2);
		config2.setActive(true);
		config2.setCreatedBy("SYSTEM");
		config2.setCreatedDate(new Date());
		entityManager.persist(config2);
		
		Config config3 = new Config();
		config3.setConfigType("Country");
		config3.setCode("IN");
		config3.setDescription("India");
		config3.setSequence(3);
		config3.setActive(false);
		config3.setCreatedBy("SYSTEM");
		config3.setCreatedDate(new Date());
		entityManager.persist(config3);
		entityManager.flush();
		
		// When
		List<Config> configsResult = configRepository.findByConfigType("Country");
		
		// Then
		Assert.assertNotNull("The config is NULL!", configsResult);	
		Assert.assertEquals("The number of ountry configs is not 3 ", 3, configsResult.size());
	}
	
	@Test
	public void testFindByActiveConfigType() {
		// Given
		Config config1 = new Config();
		config1.setConfigType("Country");
		config1.setCode("MY");
		config1.setDescription("Malaysia");
		config1.setSequence(1);
		config1.setActive(true);
		config1.setCreatedBy("SYSTEM");
		config1.setCreatedDate(new Date());
		entityManager.persist(config1);
		
		Config config2 = new Config();
		config2.setConfigType("Country");
		config2.setCode("SG");
		config2.setDescription("Singapore");
		config2.setSequence(2);
		config2.setActive(true);
		config2.setCreatedBy("SYSTEM");
		config2.setCreatedDate(new Date());
		entityManager.persist(config2);
		
		Config config3 = new Config();
		config3.setConfigType("Country");
		config3.setCode("IN");
		config3.setDescription("India");
		config3.setSequence(3);
		config3.setActive(false);
		config3.setCreatedBy("SYSTEM");
		config3.setCreatedDate(new Date());
		entityManager.persist(config3);
		entityManager.flush();
		
		// When
		List<Config> configsResult = configRepository.findByActiveConfigType("Country");
		// Then
		Assert.assertNotNull("The config is NULL!", configsResult);	
		Assert.assertEquals("The number of ountry configs is not 2 ", 2, configsResult.size());
	}
	
	@Test
	public void testFindDistinctConfigType() {
		// Given
		Config config1 = new Config();
		config1.setConfigType("Country");
		config1.setCode("MY");
		config1.setDescription("Malaysia");
		config1.setSequence(1);
		config1.setActive(true);
		config1.setCreatedBy("SYSTEM");
		config1.setCreatedDate(new Date());
		entityManager.persist(config1);
		
		Config config2 = new Config();
		config2.setConfigType("Country");
		config2.setCode("SG");
		config2.setDescription("Singapore");
		config2.setSequence(2);
		config2.setActive(true);
		config2.setCreatedBy("SYSTEM");
		config2.setCreatedDate(new Date());
		entityManager.persist(config2);
		
		Config config3 = new Config();
		config3.setConfigType("Country");
		config3.setCode("IN");
		config3.setDescription("India");
		config3.setSequence(3);
		config3.setActive(false);
		config3.setCreatedBy("SYSTEM");
		config3.setCreatedDate(new Date());
		entityManager.persist(config3);
		
		Config config4 = new Config();
		config4.setConfigType("Gender");
		config4.setCode("M");
		config4.setDescription("Male");
		config4.setSequence(1);
		config4.setActive(true);
		config4.setCreatedBy("SYSTEM");
		config4.setCreatedDate(new Date());
		entityManager.persist(config4);
		
		Config config5 = new Config();
		config5.setConfigType("Gender");
		config5.setCode("F");
		config5.setDescription("Female");
		config5.setSequence(2);
		config5.setActive(true);
		config5.setCreatedBy("SYSTEM");
		config5.setCreatedDate(new Date());
		entityManager.persist(config5);
		entityManager.flush();
		
		// When
		List<String> configsResult = configRepository.findDistinctConfigType();
		// Then
		Assert.assertNotNull("The config is NULL!", configsResult);	
		Assert.assertEquals("The number of ountry configs is not 2 ", 2, configsResult.size());
	}

}
