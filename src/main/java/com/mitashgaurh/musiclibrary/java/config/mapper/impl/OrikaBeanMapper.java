package com.mitashgaurh.musiclibrary.java.config.mapper.impl;

import com.mitashgaurh.musiclibrary.java.config.mapper.BeanMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("OrikaBeanMapper")
@Primary
public class OrikaBeanMapper implements BeanMapper {

	private static MapperFacade mapper;

	//@Autowired
	public OrikaBeanMapper() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		mapper = mapperFactory.getMapperFacade();
	}

	@Override
	public Object map(Object sourceObj, Class<?> destClass) {
		return mapper.map(sourceObj, destClass);
	}

	@Override
	public void map(Object sourceObj, Object destObj) {
		mapper.map(sourceObj, destObj);
	}

	@Override
	public <S, D> void mapCollections(Iterable<S> source,
			Collection<D> destination, Class<D> destClass) {
		mapper.mapAsCollection(source, destination, destClass);
	}
}
