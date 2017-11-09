package com.kiwi.insurance.fbs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kiwi.insurance.spl.domain.model.Config;
import com.kiwi.insurance.spl.domain.repository.ConfigRepository;
import com.kiwi.insurance.web.domain.model.ParentConfigDto;

@RunWith(SpringRunner.class)
@SpringBootTest(
	properties = "spring.datasource.initialize=false"
)
public class ConfigServiceTests {

	@Autowired
	private ConfigService configService;
	
	@MockBean
	private ConfigRepository configRepository;
	
	@Test
	public void testGetConfigs() {

		//Given
		List<Config> configs = new ArrayList<Config>();
		Config config1 = new Config();
		config1.setConfigType("Country");
		config1.setCode("MY");
		config1.setDescription("Malaysia");
		config1.setSequence(1);
		config1.setActive(true);
		config1.setCreatedBy("SYSTEM");
		config1.setCreatedDate(new Date());
		configs.add(config1);
		
		Config config2 = new Config();
		config2.setConfigType("Country");
		config2.setCode("SG");
		config2.setDescription("Singapore");
		config2.setSequence(2);
		config2.setActive(true);
		config2.setCreatedBy("SYSTEM");
		config2.setCreatedDate(new Date());
		configs.add(config2);
		
		Config config3 = new Config();
		config3.setConfigType("Country");
		config3.setCode("IN");
		config3.setDescription("India");
		config3.setSequence(3);
		config3.setActive(false);
		config3.setCreatedBy("SYSTEM");
		config3.setCreatedDate(new Date());
		configs.add(config3);
		
		Config config4 = new Config();
		config4.setConfigType("Gender");
		config4.setCode("M");
		config4.setDescription("Male");
		config4.setSequence(1);
		config4.setActive(true);
		config4.setCreatedBy("SYSTEM");
		config4.setCreatedDate(new Date());
		configs.add(config4);
		
		Config config5 = new Config();
		config5.setConfigType("Gender");
		config5.setCode("F");
		config5.setDescription("Female");
		config5.setSequence(2);
		config5.setActive(true);
		config5.setCreatedBy("SYSTEM");
		config5.setCreatedDate(new Date());
		configs.add(config5);
		
		List<String> configTypes = new ArrayList<String>();
		configTypes.add("Country");
		configTypes.add("Gender");
		
		// When
		Mockito.when(configRepository.findDistinctConfigType()).thenReturn(configTypes);
		Mockito.when(configRepository.findAll()).thenReturn(configs);
		
		// Then
		List<ParentConfigDto> configsResult = configService.getConfigs();
		Assert.assertNotNull("The configs is NULL!", configsResult);
		Assert.assertEquals("The number of ountry configs is not 2 ", 2, configsResult.size());
	}
}
