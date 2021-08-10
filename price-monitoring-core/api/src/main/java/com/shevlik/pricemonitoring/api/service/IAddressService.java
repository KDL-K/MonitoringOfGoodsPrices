package com.shevlik.pricemonitoring.api.service;

import java.util.List;

import com.shevlik.pricemonitoring.model.dto.AddressDto;

public interface IAddressService {
	void save (AddressDto entity);
	AddressDto getById (Long id);
	void update (AddressDto entity);
	void delete (Long id);
	List<AddressDto> getAll ();

}
