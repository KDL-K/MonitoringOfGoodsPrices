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
import com.shevlik.pricemonitoring.api.dao.ITradeStoreDao;
import com.shevlik.pricemonitoring.api.service.ITradeStoreService;
import com.shevlik.pricemonitoring.model.Address;
import com.shevlik.pricemonitoring.model.TradeStore;
import com.shevlik.pricemonitoring.model.dto.AddressDto;
import com.shevlik.pricemonitoring.model.dto.TradeStoreDto;
import com.shevlik.pricemonitoring.service.impldao.AddressDaoT;
import com.shevlik.pricemonitoring.service.impldao.TradeStoreDaoT;

class TradeStoreServiceTest {
	ITradeStoreDao storeDao;
	IAddressDao addressDao;
	
	ITradeStoreService storeService;

	@BeforeEach
	void setUp() throws Exception {
		Map<Long, TradeStore> storeMap = new HashMap<Long, TradeStore>();
		Map<Long, Address> addressMap = new HashMap<Long, Address>();
		this.storeDao = new TradeStoreDaoT(storeMap);
		this.addressDao = new AddressDaoT(addressMap);
		
		this.storeService = new TradeStoreService(storeDao, addressDao, new ModelMapper());
		addressDao.save(new Address (0L,"Country0", "City0", "Street0","Building0",null));
	}

	@Test
	void testSave() {
		TradeStoreDto dto = new TradeStoreDto (null, "Store0", "Phone0", new AddressDto (0L,"Country0", "City0", "Street0","Building0"));
		storeService.save(dto);
		
		TradeStore actual = storeDao.getById(0L);
		TradeStore expected = new TradeStore (0L, "Store0", "Phone0", addressDao.getById(0L), null);
		
		assertEquals(expected, actual);
	}

	@Test
	void testGetById() {
        storeDao.save(new TradeStore (0L, "Store0", "Phone0", addressDao.getById(0L), null));
		
        TradeStoreDto actual = storeService.getById(0L);
        TradeStoreDto expected = new TradeStoreDto (0L, "Store0", "Phone0", new AddressDto (0L,"Country0", "City0", "Street0","Building0"));
		assertEquals(expected, actual);
	}

	@Test
	void testUpdate() {
        storeDao.save(new TradeStore (0L, "Store0", "Phone0", addressDao.getById(0L), null));

	    storeService.update(new TradeStoreDto (0L, "Store100", "Phone0", new AddressDto (0L,"Country0", "City0", "Street0","Building0")));
	    TradeStore actual = storeDao.getById(0L);
	    TradeStore expected = new TradeStore (0L, "Store100", "Phone0", addressDao.getById(0L), null);
		assertEquals(expected, actual);
	}

	@Test
	void testDelete() {
        storeDao.save(new TradeStore (0L, "Store0", "Phone0", addressDao.getById(0L), null));

		storeService.delete(0L);
		assertNull(storeDao.getById(0L));
	}

	@Test
	void testGetAll() {
        storeDao.save(new TradeStore (0L, "Store0", "Phone0", addressDao.getById(0L), null));
        storeDao.save(new TradeStore (1L, "Store1", "Phone1", addressDao.getById(0L), null));
        storeDao.save(new TradeStore (2L, "Store2", "Phone2", addressDao.getById(0L), null));
		
		List<TradeStoreDto> actual = storeService.getAll();
		
		List<TradeStoreDto> expected = new ArrayList<TradeStoreDto>();
		expected.add(new TradeStoreDto (0L, "Store0", "Phone0", new AddressDto (0L,"Country0", "City0", "Street0","Building0")));
		expected.add(new TradeStoreDto (1L, "Store1", "Phone1", new AddressDto (0L,"Country0", "City0", "Street0","Building0")));
		expected.add(new TradeStoreDto (2L, "Store2", "Phone2", new AddressDto (0L,"Country0", "City0", "Street0","Building0")));
		
		assertEquals(expected, actual);
	}

}
