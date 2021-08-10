package com.shevlik.pricemonitoring.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shevlik.pricemonitoring.api.dao.IAddressDao;
import com.shevlik.pricemonitoring.api.dao.ITradeStoreDao;
import com.shevlik.pricemonitoring.api.service.ITradeStoreService;
import com.shevlik.pricemonitoring.model.Address;
import com.shevlik.pricemonitoring.model.TradeStore;
import com.shevlik.pricemonitoring.model.dto.TradeStoreDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TradeStoreService implements ITradeStoreService{
	private final ITradeStoreDao storeDao;
	private final IAddressDao addressDao;
	private final ModelMapper mapper;

	@Override
	public void save(TradeStoreDto entity) {
		log.info ("TradeStoreService. IN save - store: {}", entity.getName());
		TradeStore store = mapper.map(entity, TradeStore.class);
		
		Address address = addressDao.getById(entity.getAddress().getId());
		
		store.setAddress(address);
		storeDao.save(store);
	}

	@Override
	public TradeStoreDto getById(Long id) {
		log.info ("TradeStoreService. IN getById - store id: {}", id);
		TradeStore store = storeDao.getById(id);
		TradeStoreDto dto = mapper.map(store, TradeStoreDto.class);
		return dto;
	}

	@Override
	public void update(TradeStoreDto entity) {
		log.info ("TradeStoreService. IN update - store id: {}", entity.getId());
		TradeStore store = storeDao.getById(entity.getId());
		mapper.map(entity, store);
		storeDao.update(store);	
	}

	@Override
	public void delete(Long id) {
		log.info ("TradeStoreService. IN delete - store id: {}", id);
		TradeStore store = storeDao.getById(id);
		storeDao.delete(store);		
	}

	@Override
	public List<TradeStoreDto> getAll() {
		log.info ("TradeStoreService. IN getAll");
		List<TradeStore> stores = storeDao.getAll();
		Type listType = new TypeToken<List<TradeStoreDto>>() {}.getType();
		List<TradeStoreDto> storesDto = mapper.map(stores, listType);
		return storesDto;
	}
	
	

}
