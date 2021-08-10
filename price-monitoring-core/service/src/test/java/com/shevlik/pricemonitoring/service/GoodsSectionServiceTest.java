package com.shevlik.pricemonitoring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.shevlik.pricemonitoring.api.dao.IGoodsSectionDao;
import com.shevlik.pricemonitoring.api.service.IGoodsSectionService;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.dto.GoodsSectionDto;
import com.shevlik.pricemonitoring.service.impldao.GoodsSectionDaoT;

class GoodsSectionServiceTest {
    IGoodsSectionDao sectionDao;
	
	IGoodsSectionService sectionService;

	@BeforeEach
	void setUp() throws Exception {
		Map<Long, GoodsSection> sectionMap = new HashMap<Long, GoodsSection>();
		this.sectionDao = new GoodsSectionDaoT(sectionMap);
		
		this.sectionService = new GoodsSectionService(sectionDao, new ModelMapper());
	}

	@Test
	void testSave() {
		GoodsSectionDto dto = new GoodsSectionDto (null, "Section0", null);
		sectionService.save(dto);
		GoodsSection actual = sectionDao.getById(0L);
		GoodsSection expected = new GoodsSection (0L,"Section0", null);
		assertEquals(expected, actual);
	}

	@Test
	void testGetById() {
        sectionDao.save(new GoodsSection (0L,"Section0", null));
		
        GoodsSectionDto actual = sectionService.getById(0L);
        GoodsSectionDto expected = new GoodsSectionDto (0L, "Section0", null);
		assertEquals(expected, actual);
	}

	@Test
	void testUpdate() {
        sectionDao.save(new GoodsSection (0L,"Section0", null));

	    sectionService.update(new GoodsSectionDto (0L, "Section100", null));
	    GoodsSection actual = sectionDao.getById(0L);
	    GoodsSection expected = new GoodsSection (0L,"Section100", null);
		assertEquals(expected, actual);
	}

	@Test
	void testDelete() {
        sectionDao.save(new GoodsSection (0L,"Section0", null));

		sectionService.delete(0L);
		assertNull(sectionDao.getById(0L));
	}

	@Test
	void testGetAll() {
        sectionDao.save(new GoodsSection (0L,"Section0", null));
        sectionDao.save(new GoodsSection (1L,"Section1", null));
        sectionDao.save(new GoodsSection (2L,"Section2", null));
		
		List<GoodsSectionDto> actual = sectionService.getAll();
		
		List<GoodsSectionDto> expected = new ArrayList<GoodsSectionDto>();
		expected.add(new GoodsSectionDto (0L, "Section0", null));
		expected.add(new GoodsSectionDto (1L, "Section1", null));
		expected.add(new GoodsSectionDto (2L, "Section2", null));
		
		assertEquals(expected, actual);
	}

}
