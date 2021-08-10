package com.shevlik.pricemonitoring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.shevlik.pricemonitoring.api.dao.IGoodsCategoryDao;
import com.shevlik.pricemonitoring.api.dao.IGoodsSectionDao;
import com.shevlik.pricemonitoring.api.service.IGoodsCategoryService;
import com.shevlik.pricemonitoring.model.GoodsCategory;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.dto.GoodsCategoryDto;
import com.shevlik.pricemonitoring.model.dto.GoodsSectionLightDto;
import com.shevlik.pricemonitoring.service.impldao.GoodsCategoryDaoT;
import com.shevlik.pricemonitoring.service.impldao.GoodsSectionDaoT;

class GoodsCategoryServiceTest {
	IGoodsCategoryDao categoryDao;
	IGoodsSectionDao sectionDao;
	
	IGoodsCategoryService categoryService;

	@BeforeEach
	void setUp() throws Exception {
		Map<Long, GoodsCategory> categoryMap = new HashMap<Long, GoodsCategory>();
		Map<Long, GoodsSection> sectionMap = new HashMap<Long, GoodsSection>();
		this.categoryDao = new GoodsCategoryDaoT(categoryMap);
		this.sectionDao = new GoodsSectionDaoT(sectionMap);
		
		this.categoryService = new GoodsCategoryService(categoryDao, sectionDao, new ModelMapper());
	}

	@Test
	void testSave() {
		sectionDao.save(new GoodsSection(null, "Section0", null));
		GoodsCategoryDto dto = new GoodsCategoryDto (null, "Category0", new GoodsSectionLightDto(0L,"Section0"));
		categoryService.save(dto);
		
		GoodsCategory actual = categoryDao.getById(0L);
		GoodsCategory expected = new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null);
		
		assertEquals(expected, actual);
	}

	@Test
	void testGetById() {
		categoryDao.save(new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null));
		
		GoodsCategoryDto actual = categoryService.getById(0L);
		GoodsCategoryDto expected = new GoodsCategoryDto (0L, "Category0", new GoodsSectionLightDto(0L,"Section0"));
		assertEquals(expected, actual);
	}

	@Test
	void testUpdate() {
		categoryDao.save(new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null));

	    categoryService.update(new GoodsCategoryDto (0L, "Category100", new GoodsSectionLightDto(0L,"Section0")));
	    GoodsCategory actual = categoryDao.getById(0L);
	    GoodsCategory expected = new GoodsCategory (0L,"Category100", new GoodsSection(0L, "Section0", null), null);
		assertEquals(expected, actual);
	}

	@Test
	void testDelete() {
		categoryDao.save(new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null));

		categoryService.delete(0L);
		assertNull(categoryDao.getById(0L));
	}

	@Test
	void testGetAll() {
		categoryDao.save(new GoodsCategory (0L,"Category0", new GoodsSection(0L, "Section0", null), null));
		categoryDao.save(new GoodsCategory (1L,"Category1", new GoodsSection(1L, "Section1", null), null));
		categoryDao.save(new GoodsCategory (2L,"Category2", new GoodsSection(2L, "Section2", null), null));
		
		List<GoodsCategoryDto> actual = categoryService.getAll();
		
		List<GoodsCategoryDto> expected = new ArrayList<GoodsCategoryDto>();
		expected.add(new GoodsCategoryDto (0L, "Category0", new GoodsSectionLightDto(0L,"Section0")));
		expected.add(new GoodsCategoryDto (1L, "Category1", new GoodsSectionLightDto(1L,"Section1")));
		expected.add(new GoodsCategoryDto (2L, "Category2", new GoodsSectionLightDto(2L,"Section2")));
		
		assertEquals(expected, actual);
	}

}
