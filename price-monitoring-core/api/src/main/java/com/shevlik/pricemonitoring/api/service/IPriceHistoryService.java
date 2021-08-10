package com.shevlik.pricemonitoring.api.service;

import java.util.List;

import com.shevlik.pricemonitoring.model.dto.ChartDto;
import com.shevlik.pricemonitoring.model.dto.DatePriceDto;
import com.shevlik.pricemonitoring.model.dto.HistoryPeriodDto;

public interface IPriceHistoryService {
	
	List<DatePriceDto> getProductPriceHistory(HistoryPeriodDto historyPeriod);

	ChartDto getProductPriceHistoryChart(String chartName, HistoryPeriodDto historyPeriod);

}
