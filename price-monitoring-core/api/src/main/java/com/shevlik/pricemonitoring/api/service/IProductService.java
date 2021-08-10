package com.shevlik.pricemonitoring.api.service;

import java.util.List;

import com.shevlik.pricemonitoring.model.dto.OriginCountryDto;
import com.shevlik.pricemonitoring.model.dto.ProductDto;

public interface IProductService {
	void save (ProductDto entity);
	ProductDto getById (Long id);
	void update (ProductDto entity);
	void delete (Long id);
	List<ProductDto> getAll ();

	List<ProductDto> getAllByCategory(Long categoryId);
	List<ProductDto> getByNameStartsWith(String name);
	List<ProductDto> getAllBySection(Long sectionId);
	List<ProductDto> getByOrigin(Long categoryId, List<OriginCountryDto> dtoList);
	void createProductFile(String fileName);
	void loadProductsToDB(String fileName);

}
