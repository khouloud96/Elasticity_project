package com.elasticity.services;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MapJsonToJavaObject {

	// Define a static logger variable so that it references the
	// Logger instance named "MapJsonToJavaObjectServiceImpl"
	private static Logger logger = Logger.getLogger(MapJsonToJavaObject.class);

	public Object parseDataFromJson(String jsonResponse, TypeReference<?> mapType) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			return objectMapper.readValue(jsonResponse, mapType);
		} catch (JsonParseException e) {
			logger.error("Exception ::", e);
		} catch (JsonMappingException e) {
			logger.error("Exception ::", e);
		} catch (IOException e) {
			logger.error("Exception ::", e);
		}
		return objectMapper;
	}
}