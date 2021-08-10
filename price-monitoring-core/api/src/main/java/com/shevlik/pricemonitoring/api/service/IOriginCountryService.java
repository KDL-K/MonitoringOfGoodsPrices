package com.shevlik.pricemonitoring.api.service;

import java.util.List;

import com.shevlik.pricemonitoring.model.dto.OriginCountryDto;

public interface IOriginCountryService {
	void save (OriginCountryDto entity);
	OriginCountryDto getById (Long id);
	void update (OriginCountryDto entity);
	void delete (Long id);
	List<OriginCountryDto> getAll ();

}
