package com.shevlik.pricemonitoring.api.service;

import java.util.List;

import com.shevlik.pricemonitoring.model.dto.MeasureUnitDto;

public interface IMeasureUnitService {
	void save (MeasureUnitDto entity);
	MeasureUnitDto getById (Long id);
	void update (MeasureUnitDto entity);
	void delete (Long id);
	List<MeasureUnitDto> getAll ();

}
