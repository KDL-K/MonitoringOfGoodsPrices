package com.shevlik.pricemonitoring.api.dao;

import java.util.List;

import com.shevlik.pricemonitoring.model.TradeStore;

public interface ITradeStoreDao extends GenericDao<TradeStore>{

	List<TradeStore> getAllByIdSet(List<Long> storeIdList);

}
