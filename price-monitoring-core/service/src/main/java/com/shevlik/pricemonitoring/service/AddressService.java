package com.shevlik.pricemonitoring.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shevlik.pricemonitoring.api.dao.IAddressDao;
import com.shevlik.pricemonitoring.api.service.IAddressService;
import com.shevlik.pricemonitoring.model.Address;
import com.shevlik.pricemonitoring.model.dto.AddressDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class AddressService implements IAddressService{
	private final IAddressDao addressDao;
	private final ModelMapper mapper;
	
	@Override
	public void save(AddressDto entity) {
		log.info ("AddressService. IN save");
		Address address = mapper.map(entity, Address.class);
		addressDao.save(address);
	}

	@Override
	public AddressDto getById(Long id) {
		log.info ("AddressService. IN getById - address id: {}", id);
		Address address = addressDao.getById(id);
		AddressDto dto = mapper.map(address, AddressDto.class);
		return dto;
	}

	@Override
	public void update(AddressDto entity) {
		log.info ("AddressService. IN update - address id: {}", entity.getId());
		Address address = addressDao.getById(entity.getId());
		mapper.map(entity, address);
		addressDao.update(address);	
	}

	@Override
	public void delete(Long id) {
		log.info ("AddressService. IN delete - address id: {}", id);
		Address address = addressDao.getById(id);
		addressDao.delete(address);		
	}

	@Override
	public List<AddressDto> getAll() {
		log.info ("AddressService. IN getAll");
		List<Address> addressList = addressDao.getAll();
		Type listType = new TypeToken<List<AddressDto>>() {}.getType();
		List<AddressDto> addressDtoList = mapper.map(addressList, listType);
		return addressDtoList;
	}
}
