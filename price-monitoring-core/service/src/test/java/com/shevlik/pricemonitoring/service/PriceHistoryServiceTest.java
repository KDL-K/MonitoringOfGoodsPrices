package com.shevlik.pricemonitoring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shevlik.pricemonitoring.api.dao.IPriceHistoryDao;
import com.shevlik.pricemonitoring.api.service.IPriceHistoryService;
import com.shevlik.pricemonitoring.model.GoodsCategory;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.MeasureUnit;
import com.shevlik.pricemonitoring.model.OriginCountry;
import com.shevlik.pricemonitoring.model.PriceHistory;
import com.shevlik.pricemonitoring.model.Product;
import com.shevlik.pricemonitoring.model.dto.DatePriceDto;
import com.shevlik.pricemonitoring.model.dto.HistoryPeriodDto;
import com.shevlik.pricemonitoring.service.impldao.PriceHistoryDaoT;

class PriceHistoryServiceTest {
	IPriceHistoryDao historyDao;
	
	IPriceHistoryService historyService;

	@BeforeEach
	void setUp() throws Exception {
		Map<Long, PriceHistory> historyMap = new HashMap<Long, PriceHistory>();
		this.historyDao = new PriceHistoryDaoT(historyMap);
		
		this.historyService = new PriceHistoryService(historyDao);
	}

	@Test
	void testGetProductPriceHistory() {
		PriceHistory priceHistory0 = new PriceHistory(0L, LocalDate.now().minusDays(1L), 
				new Product(0L, "Product0", new MeasureUnit (0L,"Unit0", null),
						new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null),
						new OriginCountry (0L,"Origin0", null),null), 2.0);
		PriceHistory priceHistory1 = new PriceHistory(1L, LocalDate.now().plusDays(3L), 
				new Product(0L, "Product0", new MeasureUnit (0L,"Unit0", null),
						new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null),
						new OriginCountry (0L,"Origin0", null),null), 5.0);
		PriceHistory priceHistory2 = new PriceHistory(2L, LocalDate.now().plusDays(6L), 
				new Product(0L, "Product0", new MeasureUnit (0L,"Unit0", null),
						new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null),
						new OriginCountry (0L,"Origin0", null),null), 3.0);
		PriceHistory priceHistory3 = new PriceHistory(3L, LocalDate.now().plusDays(1L), 
				new Product(0L, "Product0", new MeasureUnit (0L,"Unit0", null),
						new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null),
						new OriginCountry (0L,"Origin0", null),null), 1.0);
		PriceHistory priceHistory4 = new PriceHistory(4L, LocalDate.now().plusDays(1L), 
				new Product(1L, "Product1", new MeasureUnit (0L,"Unit0", null),
						new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null),
						new OriginCountry (0L,"Origin0", null),null), 4.0);
		historyDao.save(priceHistory0);
		historyDao.save(priceHistory1);
		historyDao.save(priceHistory2);
		historyDao.save(priceHistory3);
		historyDao.save(priceHistory4);
		
		HistoryPeriodDto dto = new HistoryPeriodDto(0L,LocalDate.now(), LocalDate.now().plusDays(5L));
		List<DatePriceDto> actual = historyService.getProductPriceHistory(dto);
		
		List<DatePriceDto> expected = new ArrayList<DatePriceDto>();
		expected.add(new DatePriceDto(LocalDate.now().plusDays(1L), 1.0));
		expected.add(new DatePriceDto(LocalDate.now().plusDays(3L), 5.0));
		assertEquals(expected, actual);
	}

}
