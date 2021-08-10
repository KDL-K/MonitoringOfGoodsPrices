package com.shevlik.pricemonitoring.service.impldao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shevlik.pricemonitoring.api.dao.ITradeStoreDao;
import com.shevlik.pricemonitoring.model.TradeStore;

public class TradeStoreDaoT implements ITradeStoreDao{
	private Map<Long, TradeStore> storeMap;
	
	public TradeStoreDaoT(Map<Long, TradeStore> storeMap) {
		this.storeMap = storeMap;
	}

	@Override
	public void save(TradeStore entity) {
		if (entity.getId() == null) {
			entity.setId(0L);
			storeMap.put(0L, entity);
		}else{
			storeMap.put(entity.getId(), entity);
		}
		
	}

	@Override
	public TradeStore getById(Long id) {
		return storeMap.get(id);
	}

	@Override
	public void update(TradeStore entity) {	
		storeMap.put(entity.getId(), entity);
	}

	@Override
	public void delete(TradeStore entity) {
		storeMap.remove(entity.getId());
	}

	@Override
	public List<TradeStore> getAll() {
		List<TradeStore> list = storeMap.entrySet()
				.stream().map(a ->a.getValue())
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<TradeStore> getAllByIdSet(List<Long> storeIdList) {
		List<TradeStore> list = storeMap.entrySet().stream()
				.filter(s->storeIdList.stream().anyMatch(i->i.equals(s.getValue().getId())))
				.map(p->p.getValue()).collect(Collectors.toList());
	    return list;
	}
}
