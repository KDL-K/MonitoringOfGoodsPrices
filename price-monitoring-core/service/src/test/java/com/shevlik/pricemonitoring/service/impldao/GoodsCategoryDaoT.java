package com.shevlik.pricemonitoring.service.impldao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shevlik.pricemonitoring.api.dao.IGoodsCategoryDao;
import com.shevlik.pricemonitoring.model.GoodsCategory;

public class GoodsCategoryDaoT implements IGoodsCategoryDao{
	private Map<Long, GoodsCategory> categoryMap;
	
	public GoodsCategoryDaoT(Map<Long, GoodsCategory> categoryMap) {
		this.categoryMap = categoryMap;
	}

	@Override
	public void save(GoodsCategory entity) {
		if (entity.getId() == null) {
			entity.setId(0L);
			categoryMap.put(0L, entity);
		}else{
			categoryMap.put(entity.getId(), entity);
		}
		
	}

	@Override
	public GoodsCategory getById(Long id) {
		return categoryMap.get(id);
	}

	@Override
	public void update(GoodsCategory entity) {
		categoryMap.put(entity.getId(), entity);
		
	}

	@Override
	public void delete(GoodsCategory entity) {
		categoryMap.remove(entity.getId());
		
	}

	@Override
	public List<GoodsCategory> getAll() {
		List<GoodsCategory> list = categoryMap.entrySet()
				.stream().map(a ->a.getValue())
				.collect(Collectors.toList());
		return list;
	}

}
