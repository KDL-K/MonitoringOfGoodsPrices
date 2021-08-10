package com.shevlik.pricemonitoring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.shevlik.pricemonitoring.api.dao.IAssortmentDao;
import com.shevlik.pricemonitoring.api.dao.IPriceHistoryDao;
import com.shevlik.pricemonitoring.api.dao.IProductDao;
import com.shevlik.pricemonitoring.api.dao.ITradeStoreDao;
import com.shevlik.pricemonitoring.api.service.IAssortmentService;
import com.shevlik.pricemonitoring.model.Address;
import com.shevlik.pricemonitoring.model.Assortment;
import com.shevlik.pricemonitoring.model.GoodsCategory;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.MeasureUnit;
import com.shevlik.pricemonitoring.model.OriginCountry;
import com.shevlik.pricemonitoring.model.PriceHistory;
import com.shevlik.pricemonitoring.model.Product;
import com.shevlik.pricemonitoring.model.TradeStore;
import com.shevlik.pricemonitoring.model.dto.AddressDto;
import com.shevlik.pricemonitoring.model.dto.AssortmentDto;
import com.shevlik.pricemonitoring.model.dto.GoodsCategoryDto;
import com.shevlik.pricemonitoring.model.dto.GoodsSectionLightDto;
import com.shevlik.pricemonitoring.model.dto.MeasureUnitDto;
import com.shevlik.pricemonitoring.model.dto.OriginCountryDto;
import com.shevlik.pricemonitoring.model.dto.PriceCompareDto;
import com.shevlik.pricemonitoring.model.dto.ProductDto;
import com.shevlik.pricemonitoring.model.dto.TradeStoreDto;
import com.shevlik.pricemonitoring.service.impldao.AssortmentDaoT;
import com.shevlik.pricemonitoring.service.impldao.PriceHistoryDaoT;
import com.shevlik.pricemonitoring.service.impldao.ProductDaoT;
import com.shevlik.pricemonitoring.service.impldao.TradeStoreDaoT;


class AssortmentServiceTest {
	IAssortmentDao assortmentDao;
	IPriceHistoryDao historyDao;
	IProductDao productDao;
	ITradeStoreDao storeDao;
	
	IAssortmentService assortmentService;
	
	MeasureUnitDto unitDto0;
	GoodsCategoryDto categoryDto0;
	OriginCountryDto originDto0;
	AddressDto addressDto0;
	
	@BeforeEach
	void setUp() throws Exception {
		//---------historyDao--------
	    Map<Long, PriceHistory> historyMap = new HashMap<Long, PriceHistory>();
		historyDao = new PriceHistoryDaoT(historyMap);
	//---------productDao---------
		MeasureUnit unit0 = new MeasureUnit (0L,"Unit0", null);
		GoodsCategory category0 = new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null);
		OriginCountry origin0 = new OriginCountry (0L,"Origin0", null);
		Address address0 = new Address (0L,"Country0", "City0", "Street0","Building0",null);
		
		Map<Long, Product> productMap = new HashMap<Long, Product>();
		
		productMap.put(0L, new Product(0L,"Product0", unit0,category0,origin0,null));
		productMap.put(1L, new Product(1L,"Product1", unit0,category0,origin0,null));
		productDao = new ProductDaoT(productMap);
		
		unitDto0 = new MeasureUnitDto (0L,"Unit0");
		categoryDto0 = new GoodsCategoryDto (0L, "Category0", new GoodsSectionLightDto(0L,"Section0"));
		originDto0 = new OriginCountryDto (0L, "Origin0");
		addressDto0 = new AddressDto (0L,"Country0", "City0", "Street0","Building0");
		
	//---------storeDao----------
		Map<Long, TradeStore> storeMap = new HashMap<Long, TradeStore>();
		storeMap.put(0L, new TradeStore (0L, "Store0", "Phone0", address0, null));
		storeMap.put(1L, new TradeStore (1L, "Store1", "Phone1", address0, null));
		storeDao = new TradeStoreDaoT(storeMap);
	//-------assortmentDao--------
		Map<Long, Assortment> assortmentMap = new HashMap<Long, Assortment>();
		assortmentMap.put(0L, new Assortment(0L,2.0,LocalDate.of(2021, 7, 21),productDao.getById(0L),storeDao.getById(0L)));
		assortmentMap.put(1L, new Assortment(1L,5.0,LocalDate.of(2021, 7, 22),productDao.getById(1L),storeDao.getById(1L)));
		assortmentMap.put(2L, new Assortment(2L,6.0,LocalDate.of(2021, 7, 23),productDao.getById(0L),storeDao.getById(1L)));
	    assortmentDao = new AssortmentDaoT(assortmentMap);
	
	//---------assortmentService------------
		assortmentService = new AssortmentService(assortmentDao, historyDao, 
						productDao, storeDao, new ModelMapper());
	}

	@Test
	void testSetNewPrice_ActualAssortmentEqualsExpected() {
		assortmentService.setNewPrice(0L, 3.0);
		
		Assortment actual =  assortmentDao.getById(0L);
		Assortment expected = new Assortment(0L,3.0,LocalDate.now(),productDao.getById(0L),storeDao.getById(0L));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testSetNewPrice_SaveHistory_ActualHistoryEqualsExpected() {		
		assortmentService.setNewPrice(0L, 3.0);
		PriceHistory actual =  historyDao.getById(0L);
		
		PriceHistory expected = new PriceHistory(0L, LocalDate.now(), productDao.getById(0L), 3.0);
		
		assertEquals(expected, actual);
	}

	@Test
	void testSave_ActualAssortmentEqualsExpected() {
		AssortmentDto assortment = new AssortmentDto(3L, 4.0, LocalDate.now(), 
				new ProductDto(1L,"Product1", unitDto0, categoryDto0, originDto0), 
				new TradeStoreDto(0L, "Store0", "Phone0", addressDto0));
		
		assortmentService.save(assortment);
        Assortment actual =  assortmentDao.getById(3L);
		
		Assortment expected = new Assortment(3L,4.0,LocalDate.now(),
				productDao.getById(1L),storeDao.getById(0L));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testSave_SaveHistory_ActualHistoryEqualsExpected() {
		AssortmentDto assortment = new AssortmentDto(3L, 4.0, LocalDate.now(), 
				new ProductDto(1L,"Product1", unitDto0, categoryDto0, originDto0), 
				new TradeStoreDto(0L, "Store0", "Phone0", addressDto0));
		
		assortmentService.save(assortment);
        PriceHistory actual =  historyDao.getById(0L);
		
		PriceHistory expected = new PriceHistory(0L, LocalDate.now(), 
				productDao.getById(1L), 4.0);
		
		assertEquals(expected, actual);
	}

	@Test
	void testGetById_ActualAssortmentEqualsExpected() {
		AssortmentDto actual = assortmentService.getById(0L);
		
		AssortmentDto expected = new AssortmentDto (0L,2.0,LocalDate.of(2021, 7, 21),
				new ProductDto(0L,"Product0", unitDto0, categoryDto0, originDto0),
				new TradeStoreDto (0L, "Store0", "Phone0", addressDto0));
		
		assertEquals(expected, actual);
	}

	@Test
	void testDelete() {
		assortmentService.delete(0L);
		assertNull(assortmentDao.getById(0L));
	}

	@Test
	void testGetAll() {
		ProductDto product0 = new ProductDto(0L,"Product0", unitDto0, categoryDto0, originDto0);
		TradeStoreDto store0 = new TradeStoreDto(0L, "Store0", "Phone0", addressDto0);
		ProductDto product1 = new ProductDto(1L,"Product1", unitDto0, categoryDto0, originDto0);
		TradeStoreDto store1 = new TradeStoreDto(1L, "Store1", "Phone1", addressDto0);
		
		List<AssortmentDto> actual = assortmentService.getAll();
		
		List<AssortmentDto> expected = new ArrayList<AssortmentDto>();
		expected.add(new AssortmentDto(0L,2.0,LocalDate.of(2021, 7, 21),product0,store0));
		expected.add(new AssortmentDto(1L,5.0,LocalDate.of(2021, 7, 22),product1,store1));
		expected.add(new AssortmentDto(2L,6.0,LocalDate.of(2021, 7, 23),product0,store1));
		
		assertEquals(expected, actual);
	}

	@Test
	void testGetAssortmentPriceCompare() {
		ProductDto product0 = new ProductDto(0L,"Product0", unitDto0, categoryDto0, originDto0);
		TradeStoreDto store0 = new TradeStoreDto(0L, "Store0", "Phone0", addressDto0);
		ProductDto product1 = new ProductDto(1L,"Product1", unitDto0, categoryDto0, originDto0);
		TradeStoreDto store1 = new TradeStoreDto(1L, "Store1", "Phone1", addressDto0);
		
		List<AssortmentDto> dtoList1 = new ArrayList<AssortmentDto>();
		dtoList1.add(new AssortmentDto(0L,2.0,LocalDate.of(2021, 7, 21),product0,store0));
		dtoList1.add(new AssortmentDto(2L,6.0,LocalDate.of(2021, 7, 23),product0,store1));
		
		List<AssortmentDto> dtoList2 = new ArrayList<AssortmentDto>();
		dtoList2.add(new AssortmentDto(1L,5.0,LocalDate.of(2021, 7, 22),product1,store1));
		
		List<Long> storeIdList = new ArrayList<Long>();
		Collections.addAll(storeIdList, 0L, 1L);
		List<PriceCompareDto> actual = assortmentService.getAssortmentPriceCompare(storeIdList);
		
		List<PriceCompareDto> expected = new ArrayList<PriceCompareDto>();
		Collections.addAll(expected, 
				new PriceCompareDto(product1, dtoList2), 
				new PriceCompareDto(product0, dtoList1));

		assertEquals(expected, actual);
	}
}

