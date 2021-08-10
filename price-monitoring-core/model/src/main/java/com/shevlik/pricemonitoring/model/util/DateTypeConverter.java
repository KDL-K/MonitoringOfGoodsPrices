package com.shevlik.pricemonitoring.model.util;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DateTypeConverter implements AttributeConverter<LocalDate, Long>{

	@Override
	public Long convertToDatabaseColumn(LocalDate attribute) {
		return attribute.toEpochDay();
	}

	@Override
	public LocalDate convertToEntityAttribute(Long dbData) {
		return LocalDate.ofEpochDay(dbData);
	}

}
