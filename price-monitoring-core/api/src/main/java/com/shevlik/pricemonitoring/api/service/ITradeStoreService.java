package com.shevlik.pricemonitoring.api.service;

import java.util.List;

import com.shevlik.pricemonitoring.model.dto.TradeStoreDto;

public interface ITradeStoreService {
	void save (TradeStoreDto entity);
	TradeStoreDto getById (Long id);
	void update (TradeStoreDto entity);
	void delete (Long id);
	List<TradeStoreDto> getAll ();

}
