package com.shevlik.pricemonitoring.api.service;

import java.util.List;

import com.shevlik.pricemonitoring.model.dto.GoodsSectionDto;

public interface IGoodsSectionService {
	void save (GoodsSectionDto entity);
	GoodsSectionDto getById (Long id);
	void update (GoodsSectionDto entity);
	void delete (Long id);
	List<GoodsSectionDto> getAll ();

}
