package com.shevlik.pricemonitoring.service.impldao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shevlik.pricemonitoring.api.dao.IAssortmentDao;
import com.shevlik.pricemonitoring.model.Assortment;
import com.shevlik.pricemonitoring.model.TradeStore;

public class AssortmentDaoT implements IAssortmentDao{
	private Map<Long, Assortment> assortmentMap;
	
	public AssortmentDaoT(Map<Long, Assortment> assortmentMap) {
		this.assortmentMap = assortmentMap;
	}
	
	@Override
	public Assortment getById(Long id) {
		return assortmentMap.get(id);
	}

	@Override
	public void save(Assortment entity) {
		if (entity.getId() == null) {
			entity.setId(0L);
			assortmentMap.put(0L, entity);
		}else{
			assortmentMap.put(entity.getId(), entity);
		}
	}

	@Override
	public void update(Assortment entity) {
		assortmentMap.put(entity.getId(), entity);
	}

	@Override
	public void delete(Assortment entity) {
		assortmentMap.remove(entity.getId());
		
	}

	@Override
	public List<Assortment> getAll() {
		List<Assortment> list = assortmentMap.entrySet()
				.stream().map(a ->a.getValue())
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<Assortment> getAssortmentByStores(List<TradeStore> stores) {
		return getAll();
	}

	@Override
	public List<Assortment> getByStore(TradeStore store) {
		List<Assortment> assortmentList = assortmentMap.entrySet().stream()
		    .filter(a-> a.getValue().getTradeStore().equals(store))
		    .map(a->a.getValue()).collect(Collectors.toList());
	
		return assortmentList;
	}
}
