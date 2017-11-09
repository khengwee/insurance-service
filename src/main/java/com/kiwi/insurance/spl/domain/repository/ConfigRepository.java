package com.kiwi.insurance.spl.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kiwi.insurance.spl.domain.model.Config;

public interface ConfigRepository  extends CrudRepository<Config, String>{
	
	@Query("from Config c where c.isActive = true order by sequence")
	List<Config> findAllActiveConfigs();
	
	@Query("select c from Config c where c.configType = ?1 order by sequence")
	List<Config> findByConfigType(String configType);
	
	@Query("select c from Config c where c.isActive = true and c.configType = ?1 order by sequence")
	List<Config> findByActiveConfigType(String configType);
	
	@Query("select distinct c.configType from Config c where c.isActive = true order by c.configType")
	List<String> findDistinctConfigType();

}
