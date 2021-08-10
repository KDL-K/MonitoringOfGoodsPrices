package com.shevlik.pricemonitoring.api.dao;

import java.util.List;

import com.shevlik.pricemonitoring.model.PriceHistory;
import com.shevlik.pricemonitoring.model.dto.DatePriceDto;
import com.shevlik.pricemonitoring.model.dto.HistoryPeriodDto;

public interface IPriceHistoryDao extends GenericDao<PriceHistory>{

	List<DatePriceDto> getProductPriceHistory(HistoryPeriodDto historyPeriod);

}
