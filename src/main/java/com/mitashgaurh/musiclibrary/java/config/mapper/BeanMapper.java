package com.mitashgaurh.musiclibrary.java.config.mapper;

import java.util.Collection;

public interface BeanMapper {

	Object map(Object sourceObj, Class<?> destClass);
	
	void map(Object sourceObj, Object destObj);
	
	<S,D> void mapCollections(Iterable<S> source, Collection<D> destination,
                              Class<D> destClass);
}
