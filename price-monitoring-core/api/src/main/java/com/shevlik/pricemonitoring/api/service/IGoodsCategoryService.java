package com.shevlik.pricemonitoring.api.service;

import java.util.List;

import com.shevlik.pricemonitoring.model.dto.GoodsCategoryDto;

public interface IGoodsCategoryService {
	void save (GoodsCategoryDto entity);
	GoodsCategoryDto getById (Long id);
	void update (GoodsCategoryDto entity);
	void delete (Long id);
	List<GoodsCategoryDto> getAll ();

}
