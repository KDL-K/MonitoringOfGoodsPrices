package com.shevlik.pricemonitoring.service.impldao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shevlik.pricemonitoring.api.dao.IPriceHistoryDao;
import com.shevlik.pricemonitoring.model.PriceHistory;
import com.shevlik.pricemonitoring.model.dto.DatePriceDto;
import com.shevlik.pricemonitoring.model.dto.HistoryPeriodDto;

public class PriceHistoryDaoT implements IPriceHistoryDao{
	private Map<Long, PriceHistory> historyMap;
	
	public PriceHistoryDaoT(Map<Long, PriceHistory> historyMap) {
		this.historyMap = historyMap;
	}

	@Override
	public void save(PriceHistory entity) {
		if(entity.getId() == null) {
			entity.setId(0L);
			historyMap.put(0L, entity);
		}else {
			historyMap.put(entity.getId(), entity);
		}			
	}

	@Override
	public PriceHistory getById(Long id) {
		return historyMap.get(id);
	}

	@Override
	public void update(PriceHistory entity) {
		historyMap.put(entity.getId(), entity);
		
	}

	@Override
	public void delete(PriceHistory entity) {
		historyMap.remove(entity.getId());
		
	}

	@Override
	public List<PriceHistory> getAll() {
		List<PriceHistory> list = historyMap.entrySet()
				.stream().map(a ->a.getValue())
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<DatePriceDto> getProductPriceHistory(HistoryPeriodDto historyPeriod) {
		List<PriceHistory> list = historyMap.entrySet().stream()
				.filter(p->p.getValue().getProduct().getId().equals(historyPeriod.getProductId()))
				.map(p->p.getValue()).collect(Collectors.toList());
	    
		List<DatePriceDto> datePriceList= new ArrayList<DatePriceDto>();
		for(int i=list.size()-1; i>=0; i--) {
			PriceHistory priceHistory = list.get(i);
			if(priceHistory.getPriceDate().isAfter(historyPeriod.getStartDate().minusDays(1L)) && priceHistory.getPriceDate().isBefore(historyPeriod.getEndDate().minusDays(1L))) {
				datePriceList.add(new DatePriceDto(priceHistory.getPriceDate(), priceHistory.getPrice()));
			}
		}
		
		return datePriceList;
	}
}
