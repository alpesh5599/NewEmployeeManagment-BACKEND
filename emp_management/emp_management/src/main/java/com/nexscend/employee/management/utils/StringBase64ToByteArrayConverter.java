package com.nexscend.employee.management.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.convert.converter.Converter;

public class StringBase64ToByteArrayConverter implements Converter<String, byte[]> {

	@Override
	public byte[] convert(String value) {
		return Base64.decodeBase64(value);
	}

}