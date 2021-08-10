package com.shevlik.pricemonitoring.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shevlik.pricemonitoring.api.dao.IPriceHistoryDao;
import com.shevlik.pricemonitoring.api.service.IPriceHistoryService;
import com.shevlik.pricemonitoring.model.dto.ChartDto;
import com.shevlik.pricemonitoring.model.dto.DatePriceDto;
import com.shevlik.pricemonitoring.model.dto.HistoryPeriodDto;
import com.shevlik.pricemonitoring.service.util.GraphicCreator;
import com.shevlik.pricemonitoring.service.util.LineChartMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class PriceHistoryService implements IPriceHistoryService{
	private final IPriceHistoryDao historyDao;

	@Override
	public List<DatePriceDto> getProductPriceHistory(HistoryPeriodDto historyPeriod) {
		log.info ("PriceHistoryService. IN getProductPriceHistory - period: {} - {}", historyPeriod.getStartDate(), historyPeriod.getEndDate());
		List<DatePriceDto> datePriceListDto = historyDao.getProductPriceHistory(historyPeriod);
		
		return datePriceListDto;
	}

	@Override
	public ChartDto getProductPriceHistoryChart(String chartName, HistoryPeriodDto historyPeriod) {
		log.info ("PriceHistoryService. IN getProductPriceHistoryChart - period: {} - {}", historyPeriod.getStartDate(), historyPeriod.getEndDate());
		List<DatePriceDto> datePriceListDto = historyDao.getProductPriceHistory(historyPeriod);
		
		log.info ("PriceHistoryService. IN getProductPriceHistoryChart - creating chart");
		GraphicCreator graphicCreator = new LineChartMaker();
		File chartFile = graphicCreator.creatGraphic(chartName, datePriceListDto);
		ChartDto chart = new ChartDto();
		chart.setChartUrl(chartFile.getAbsolutePath());
		
		return chart;
	}

}
