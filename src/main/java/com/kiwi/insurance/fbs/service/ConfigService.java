package com.kiwi.insurance.fbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.insurance.config.ConfigMapper;
import com.kiwi.insurance.spl.domain.model.Config;
import com.kiwi.insurance.spl.domain.repository.ConfigRepository;
import com.kiwi.insurance.web.domain.model.ConfigDto;
import com.kiwi.insurance.web.domain.model.ParentConfigDto;

@Service
public class ConfigService {
	
	@Autowired
	private ConfigRepository configRepository;
	
	@Autowired
	private ConfigMapper configMapper;
	
	public ParentConfigDto getConfigsByConfigType(String configType) {
		ParentConfigDto configGroup = new ParentConfigDto();
		
		List<Config> configs = configRepository.findByActiveConfigType(configType);
		
		List<ConfigDto> configsDto = new ArrayList<ConfigDto>();
		for (Config config : configs) {
			if (configType.equals(config.getConfigType())) {
				ConfigDto configDto = configMapper.map(config, ConfigDto.class);
				configsDto.add(configDto);
			}
		}
		configGroup.setConfigType(configType);
		configGroup.setConfigs(configsDto);
		return configGroup;
	}
	
	public List<ParentConfigDto> getConfigs() {
		List<ParentConfigDto> configGroups = new ArrayList<ParentConfigDto>();
		
		List<String> configTypes = configRepository.findDistinctConfigType();
		List<Config> configs = (List<Config>) configRepository.findAllActiveConfigs();
		
		for (String configType : configTypes) {
			List<ConfigDto> configsDto = new ArrayList<ConfigDto>();
			for (Config config : configs) {
				if (configType.equals(config.getConfigType())) {
					ConfigDto configDto = configMapper.map(config, ConfigDto.class);
					configsDto.add(configDto);
				}
			}
			ParentConfigDto configGroup = new ParentConfigDto();
			configGroup.setConfigType(configType);
			configGroup.setConfigs(configsDto);
			configGroups.add(configGroup);
		}
		return configGroups;
	}

}
