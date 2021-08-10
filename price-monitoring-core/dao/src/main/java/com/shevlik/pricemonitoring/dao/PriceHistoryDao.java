package com.shevlik.pricemonitoring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.api.dao.IPriceHistoryDao;
import com.shevlik.pricemonitoring.model.PriceHistory;
import com.shevlik.pricemonitoring.model.dto.DatePriceDto;
import com.shevlik.pricemonitoring.model.dto.HistoryPeriodDto;

import lombok.extern.log4j.Log4j2;



@Repository
@Log4j2
public class PriceHistoryDao extends AbstractDao<PriceHistory> implements IPriceHistoryDao{
	

	@Override
	protected Class<PriceHistory> getClazz() {
		return PriceHistory.class;
	}

	@Override
	public List<DatePriceDto> getProductPriceHistory(HistoryPeriodDto historyPeriod) {
		log.info("PriceHistoryDao. IN getProductPriceHistory - product id: {}, interval: {} - {}.", historyPeriod.getProductId(), historyPeriod.getStartDate(), historyPeriod.getEndDate());
		final String QUERY_SELECT_NEW_DATE_PRICE = "SELECT"
				+ " NEW com.shevlik.pricemonitoring.model.dto.DatePriceDto(h.priceDate, h.price)"
				+ " FROM PriceHistory h"
				+ " WHERE h.product.id=:productId"
				+ " AND h.priceDate BETWEEN :startDate AND :endDate"
				+ " ORDER BY h.priceDate ASC, h.price DESC";

		TypedQuery<DatePriceDto> query = entityManager.createQuery(QUERY_SELECT_NEW_DATE_PRICE, DatePriceDto.class);
		query.setParameter("productId", historyPeriod.getProductId());
		query.setParameter("startDate", historyPeriod.getStartDate());
		query.setParameter("endDate", historyPeriod.getEndDate());
		List<DatePriceDto> datePriceList = query.getResultList();
		
		log.info("PriceHistoryDao. IN getProductPriceHistory - result received.");

		return datePriceList;
	}

}
