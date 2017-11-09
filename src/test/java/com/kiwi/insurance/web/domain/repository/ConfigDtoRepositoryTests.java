package com.kiwi.insurance.web.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kiwi.insurance.InsuranceApplication;
import com.kiwi.insurance.fbs.service.ConfigService;
import com.kiwi.insurance.web.domain.model.ConfigDto;
import com.kiwi.insurance.web.domain.model.ParentConfigDto;

import io.katharsis.client.KatharsisClient;
import io.katharsis.queryspec.QuerySpec;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = WebEnvironment.DEFINED_PORT,
	classes = InsuranceApplication.class,
	properties = "spring.datasource.initialize=true"
)
public class ConfigDtoRepositoryTests {
	
	@Autowired
	private ConfigDtoRepository configDtoRepository;
	
	@MockBean
	private ConfigService configService;
	
	@Before
	public void init() {
		
		// Given
		List<ConfigDto> configs1 = new ArrayList<ConfigDto>();
		ConfigDto config1 = new ConfigDto();
		config1.setCode("IN");
		config1.setDescription("India");
		configs1.add(config1);
		
		ConfigDto config2 = new ConfigDto();
		config2.setCode("MY");
		config2.setDescription("Malaysia");
		configs1.add(config2);
		
		ConfigDto config3 = new ConfigDto();
		config3.setCode("SG");
		config3.setDescription("Singapore");
		configs1.add(config3);

		ParentConfigDto parentConfig1 = new ParentConfigDto();
		parentConfig1.setConfigType("Country");
		parentConfig1.setConfigs(configs1);

		List<ConfigDto> configs2 = new ArrayList<ConfigDto>();
		ConfigDto config4 = new ConfigDto();
		config4.setCode("M");
		config4.setDescription("Male");
		configs2.add(config4);
		
		ConfigDto config5 = new ConfigDto();
		config5.setCode("F");
		config5.setDescription("Female");
		configs2.add(config5);
		
		ParentConfigDto parentConfig2 = new ParentConfigDto();
		parentConfig2.setConfigType("Gender");
		parentConfig2.setConfigs(configs2);
		
		List<ParentConfigDto> parentConfigs = new ArrayList<ParentConfigDto>();
		parentConfigs.add(parentConfig1);
		parentConfigs.add(parentConfig2);
		
		// When
		Mockito.when(configService.getConfigsByConfigType("Country")).thenReturn(parentConfig1);
		Mockito.when(configService.getConfigs()).thenReturn(parentConfigs);
	}
	
	@Test
	public void testFindOneClient() throws Exception {
		KatharsisClient client = new KatharsisClient("http://localhost:9090/kiwi/api/configs/Country");
		configDtoRepository = client.getRepositoryForInterface(ConfigDtoRepository.class);
		QuerySpec querySpec = new QuerySpec(ParentConfigDto.class);
		ParentConfigDto configResult = configDtoRepository.findOne("Country", querySpec);
		Assert.assertEquals("Country", configResult.getConfigType());
	}
	
	@Test
	public void testFindAllClient() throws Exception {
		KatharsisClient client = new KatharsisClient("http://localhost:9090/kiwi/api/configs");
		configDtoRepository = client.getRepositoryForInterface(ConfigDtoRepository.class);
		QuerySpec querySpec = new QuerySpec(ParentConfigDto.class);
		List<ParentConfigDto> configsResult = configDtoRepository.findAll(querySpec);
		Assert.assertNotEquals(0, configsResult.size());
	}

}
