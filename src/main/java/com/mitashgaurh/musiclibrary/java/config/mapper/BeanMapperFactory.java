package com.mitashgaurh.musiclibrary.java.config.mapper;


public interface BeanMapperFactory {

    String ORIKA_MAPPER = "ORIKA_MAPPER";

    BeanMapper getMapper(String mappingStrategy);
}
