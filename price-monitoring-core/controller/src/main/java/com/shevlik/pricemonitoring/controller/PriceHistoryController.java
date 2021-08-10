package com.shevlik.pricemonitoring.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shevlik.pricemonitoring.api.service.IPriceHistoryService;
import com.shevlik.pricemonitoring.model.dto.ChartDto;
import com.shevlik.pricemonitoring.model.dto.DatePriceDto;
import com.shevlik.pricemonitoring.model.dto.HistoryPeriodDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/histories")
@RequiredArgsConstructor
public class PriceHistoryController {
	private final IPriceHistoryService historyService;
	
	@GetMapping("/of-product")
	public ResponseEntity<List<DatePriceDto>> getProductPriceHistory(@RequestBody HistoryPeriodDto historyPeriod){
		List<DatePriceDto> datePriceList = historyService.getProductPriceHistory(historyPeriod);
		return ResponseEntity.ok(datePriceList);	
	}
	
	@GetMapping("/of-product/chart/{chart-name}")
	public ResponseEntity<ChartDto> getProductPriceHistoryChart(@PathVariable(name="chart-name") String chartName, @RequestBody HistoryPeriodDto historyPeriod){
		ChartDto chart = historyService.getProductPriceHistoryChart(chartName, historyPeriod);
		return ResponseEntity.ok(chart);
	}

}
