package com.mitashgaurh.musiclibrary.java.config.mapper.impl;

import com.mitashgaurh.musiclibrary.java.config.mapper.BeanMapper;
import com.mitashgaurh.musiclibrary.java.config.mapper.BeanMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class BeanMapperFactoryImpl implements BeanMapperFactory {
	
	@Autowired
	private OrikaBeanMapper orikaBeanMapper;

	public BeanMapper getMapper(String mappingStrategy) {
		if(mappingStrategy.equals(ORIKA_MAPPER)) {
			return orikaBeanMapper;
		} else {
			return orikaBeanMapper;
		}
	}
}
