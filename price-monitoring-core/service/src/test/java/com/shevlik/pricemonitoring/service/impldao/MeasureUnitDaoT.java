package com.shevlik.pricemonitoring.service.impldao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shevlik.pricemonitoring.api.dao.IMeasureUnitDao;
import com.shevlik.pricemonitoring.model.MeasureUnit;

public class MeasureUnitDaoT implements IMeasureUnitDao{
	private Map<Long, MeasureUnit> unitMap;
	
	public MeasureUnitDaoT(Map<Long, MeasureUnit> unitMap) {
		this.unitMap = unitMap;
	}

	@Override
	public void save(MeasureUnit entity) {
		if (entity.getId() == null) {
			entity.setId(0L);
			unitMap.put(0L, entity);
		}else{
			unitMap.put(entity.getId(), entity);
		}
		
	}

	@Override
	public MeasureUnit getById(Long id) {
		return unitMap.get(id);
	}

	@Override
	public void update(MeasureUnit entity) {
		unitMap.put(entity.getId(), entity);
		
	}

	@Override
	public void delete(MeasureUnit entity) {
		unitMap.remove(entity.getId());
		
	}

	@Override
	public List<MeasureUnit> getAll() {
		List<MeasureUnit> list = unitMap.entrySet()
				.stream().map(a ->a.getValue())
				.collect(Collectors.toList());
		return list;
	}

}
