package com.shevlik.pricemonitoring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.shevlik.pricemonitoring.api.dao.IOriginCountryDao;
import com.shevlik.pricemonitoring.api.service.IOriginCountryService;
import com.shevlik.pricemonitoring.model.OriginCountry;
import com.shevlik.pricemonitoring.model.dto.OriginCountryDto;
import com.shevlik.pricemonitoring.service.impldao.OriginCountryDaoT;

class OriginCountryServiceTest {
	IOriginCountryDao originDao;
	
	IOriginCountryService originService;

	@BeforeEach
	void setUp() throws Exception {
		Map<Long, OriginCountry> originMap = new HashMap<Long, OriginCountry>();
		this.originDao = new OriginCountryDaoT(originMap);
		
		this.originService = new OriginCountryService(originDao, new ModelMapper());
	}

	@Test
	void testSave() {
		OriginCountryDto dto = new OriginCountryDto (null, "Origin0");
		originService.save(dto);
		OriginCountry actual = originDao.getById(0L);
		OriginCountry expected = new OriginCountry (0L,"Origin0", null);
		assertEquals(expected, actual);
	}

	@Test
	void testGetById() {
        originDao.save(new OriginCountry (0L,"Origin0", null));
		
        OriginCountryDto actual = originService.getById(0L);
        OriginCountryDto expected = new OriginCountryDto (0L,"Origin0");
		assertEquals(expected, actual);
	}

	@Test
	void testUpdate() {
        originDao.save(new OriginCountry (0L,"Origin0", null));

	    originService.update(new OriginCountryDto (0L,"Origin100"));
	    OriginCountry actual = originDao.getById(0L);
	    OriginCountry expected = new OriginCountry (0L,"Origin100", null);
		assertEquals(expected, actual);
	}

	@Test
	void testDelete() {
        originDao.save(new OriginCountry (0L,"Origin0", null));

		originService.delete(0L);
		assertNull(originDao.getById(0L));
	}

	@Test
	void testGetAll() {
        originDao.save(new OriginCountry (0L,"Origin0", null));
        originDao.save(new OriginCountry (1L,"Origin1", null));
        originDao.save(new OriginCountry (2L,"Origin2", null));
		
		List<OriginCountryDto> actual = originService.getAll();
		
		List<OriginCountryDto> expected = new ArrayList<OriginCountryDto>();
		expected.add(new OriginCountryDto (0L,"Origin0"));
		expected.add(new OriginCountryDto (1L,"Origin1"));
		expected.add(new OriginCountryDto (2L,"Origin2"));
		
		assertEquals(expected, actual);
	}

}
