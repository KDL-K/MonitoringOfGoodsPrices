package com.shevlik.pricemonitoring.service.impldao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shevlik.pricemonitoring.api.dao.IGoodsSectionDao;
import com.shevlik.pricemonitoring.model.GoodsSection;

public class GoodsSectionDaoT implements IGoodsSectionDao{
	private Map<Long, GoodsSection> sectionMap;
	
	public GoodsSectionDaoT(Map<Long, GoodsSection> sectionMap) {
		this.sectionMap = sectionMap;
	}

	@Override
	public void save(GoodsSection entity) {
		if (entity.getId() == null) {
			entity.setId(0L);
			sectionMap.put(0L, entity);
		}else{
			sectionMap.put(entity.getId(), entity);
		}
		
	}

	@Override
	public GoodsSection getById(Long id) {
		return sectionMap.get(id);
	}

	@Override
	public void update(GoodsSection entity) {
		sectionMap.put(entity.getId(), entity);
		
	}

	@Override
	public void delete(GoodsSection entity) {
		sectionMap.remove(entity.getId());
		
	}

	@Override
	public List<GoodsSection> getAll() {
		List<GoodsSection> list = sectionMap.entrySet()
				.stream().map(a ->a.getValue())
				.collect(Collectors.toList());
		return list;
	}

}
