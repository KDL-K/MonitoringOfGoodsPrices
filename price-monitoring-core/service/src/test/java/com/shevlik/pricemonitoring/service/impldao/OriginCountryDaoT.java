package com.shevlik.pricemonitoring.service.impldao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shevlik.pricemonitoring.api.dao.IOriginCountryDao;
import com.shevlik.pricemonitoring.model.OriginCountry;

public class OriginCountryDaoT implements IOriginCountryDao{
	private Map<Long, OriginCountry> originMap;
	
	public OriginCountryDaoT(Map<Long, OriginCountry> originMap) {
		this.originMap = originMap;
	}

	@Override
	public void save(OriginCountry entity) {
		if (entity.getId() == null) {
			entity.setId(0L);
			originMap.put(0L, entity);
		}else{
			originMap.put(entity.getId(), entity);
		}
		
	}

	@Override
	public OriginCountry getById(Long id) {
		return originMap.get(id);
	}

	@Override
	public void update(OriginCountry entity) {
		originMap.put(entity.getId(), entity);
		
	}

	@Override
	public void delete(OriginCountry entity) {
		originMap.remove(entity.getId());
		
	}

	@Override
	public List<OriginCountry> getAll() {
		List<OriginCountry> list = originMap.entrySet()
				.stream().map(a ->a.getValue())
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<OriginCountry> getAllByIdSet(List<Long> originIdList) {
		List<OriginCountry> list = originMap.entrySet().stream()
				.filter(o->originIdList.stream().anyMatch(i->i.equals(o.getValue().getId())))
				.map(p->p.getValue()).collect(Collectors.toList());
	    return list;
	}

}
