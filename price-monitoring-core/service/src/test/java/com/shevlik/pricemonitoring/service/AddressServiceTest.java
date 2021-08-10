package com.shevlik.pricemonitoring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.shevlik.pricemonitoring.api.dao.IAddressDao;
import com.shevlik.pricemonitoring.api.service.IAddressService;
import com.shevlik.pricemonitoring.model.Address;
import com.shevlik.pricemonitoring.model.dto.AddressDto;
import com.shevlik.pricemonitoring.service.impldao.AddressDaoT;

class AddressServiceTest {
	IAddressDao addressDao;
	
	IAddressService addressService;

	@BeforeEach
	void setUp() throws Exception {
		Map<Long, Address> addressMap = new HashMap<Long, Address>();
		this.addressDao = new AddressDaoT(addressMap);
		
		this.addressService = new AddressService(addressDao, new ModelMapper());
	}

	@Test
	void testSave_ActualAddressEqualsExpected() {
		AddressDto dto = new AddressDto (null, "Country0", "City0", "Street0","Building0");
		addressService.save(dto);
		Address actual = addressDao.getById(0L);
		Address expected = new Address (0L,"Country0", "City0", "Street0","Building0",null);
		assertEquals(expected, actual);
	}

	@Test
	void testGetById_ActualAddressEqualsExpected() {
		addressDao.save(new Address (0L,"Country0", "City0", "Street0","Building0",null));
		
		AddressDto actual = addressService.getById(0L);
		AddressDto expected = new AddressDto (0L,"Country0", "City0", "Street0","Building0");
		assertEquals(expected, actual);
	}

	@Test
	void testUpdate() {
		addressDao.save(new Address (0L,"Country0", "City0", "Street0","Building0",null));

	    addressService.update(new AddressDto (0L,"Country100", "City0", "Street0","Building0"));
	    Address actual = addressDao.getById(0L);
		Address expected = new Address (0L,"Country100", "City0", "Street0","Building0",null);
		assertEquals(expected, actual);
	}

	@Test
	void testDelete() {
		addressDao.save(new Address (0L,"Country0", "City0", "Street0","Building0",null));

		addressService.delete(0L);
		assertNull(addressDao.getById(0L));
	}

	@Test
	void testGetAll() {
		addressDao.save(new Address (0L,"Country0", "City0", "Street0","Building0",null));
		addressDao.save(new Address (1L,"Country1", "City1", "Street1","Building1",null));
		addressDao.save(new Address (2L,"Country2", "City2", "Street2","Building2",null));
		
		List<AddressDto> actual = addressService.getAll();
		
		List<AddressDto> expected = new ArrayList<AddressDto>();
		expected.add(new AddressDto (0L,"Country0", "City0", "Street0","Building0"));
		expected.add(new AddressDto (1L,"Country1", "City1", "Street1","Building1"));
		expected.add(new AddressDto (2L,"Country2", "City2", "Street2","Building2"));
		
		assertEquals(expected, actual);
	}

}
