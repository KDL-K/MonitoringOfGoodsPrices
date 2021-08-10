package com.shevlik.pricemonitoring.api.dao;

import java.util.List;

import com.shevlik.pricemonitoring.model.Assortment;
import com.shevlik.pricemonitoring.model.TradeStore;

public interface IAssortmentDao extends GenericDao<Assortment>{

	List<Assortment> getAssortmentByStores(List<TradeStore> stores);

	List<Assortment> getByStore(TradeStore store);

}
