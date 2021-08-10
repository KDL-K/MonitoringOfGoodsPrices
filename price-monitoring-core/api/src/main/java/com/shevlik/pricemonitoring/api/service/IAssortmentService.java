package com.shevlik.pricemonitoring.api.service;

import java.util.List;

import com.shevlik.pricemonitoring.model.dto.AssortmentDto;
import com.shevlik.pricemonitoring.model.dto.PriceCompareDto;

public interface IAssortmentService {
	void save (AssortmentDto entity);
	AssortmentDto getById (Long id);
	void delete (Long id);
	List<AssortmentDto> getAll ();

	void setNewPrice(Long id, Double price);
	List<PriceCompareDto> getAssortmentPriceCompare(List<Long> storeIdList);
	void createAssortmentFile(String fileName, Long storeId);
	void loadAssortmentToDB(String fileName, Long storeId);

}
