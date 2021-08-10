package com.shevlik.pricemonitoring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.shevlik.pricemonitoring.api.dao.IMeasureUnitDao;
import com.shevlik.pricemonitoring.api.service.IMeasureUnitService;
import com.shevlik.pricemonitoring.model.MeasureUnit;
import com.shevlik.pricemonitoring.model.dto.MeasureUnitDto;
import com.shevlik.pricemonitoring.service.impldao.MeasureUnitDaoT;

class MeasureUnitServiceTest {
	IMeasureUnitDao unitDao;
	
	IMeasureUnitService unitService;

	@BeforeEach
	void setUp() throws Exception {
		Map<Long, MeasureUnit> unitMap = new HashMap<Long, MeasureUnit>();
		this.unitDao = new MeasureUnitDaoT(unitMap);
		
		this.unitService = new MeasureUnitService(unitDao, new ModelMapper());
	}

	@Test
	void testSave() {
		MeasureUnitDto dto = new MeasureUnitDto (null, "Unit0");
		unitService.save(dto);
		MeasureUnit actual = unitDao.getById(0L);
		MeasureUnit expected = new MeasureUnit (0L,"Unit0", null);
		assertEquals(expected, actual);
	}

	@Test
	void testGetById() {
        unitDao.save(new MeasureUnit (0L,"Unit0", null));
		
        MeasureUnitDto actual = unitService.getById(0L);
        MeasureUnitDto expected = new MeasureUnitDto (0L, "Unit0");
		assertEquals(expected, actual);
	}

	@Test
	void testUpdate() {
        unitDao.save(new MeasureUnit (0L,"Unit0", null));

	    unitService.update(new MeasureUnitDto (0L, "Unit100"));
	    MeasureUnit actual = unitDao.getById(0L);
	    MeasureUnit expected = new MeasureUnit (0L,"Unit100", null);
		assertEquals(expected, actual);
	}

	@Test
	void testDelete() {
        unitDao.save(new MeasureUnit (0L,"Unit0", null));

		unitService.delete(0L);
		assertNull(unitDao.getById(0L));
	}

	@Test
	void testGetAll() {
        unitDao.save(new MeasureUnit (0L,"Unit0", null));
        unitDao.save(new MeasureUnit (1L,"Unit1", null));
        unitDao.save(new MeasureUnit (2L,"Unit2", null));
		
		List<MeasureUnitDto> actual = unitService.getAll();
		
		List<MeasureUnitDto> expected = new ArrayList<MeasureUnitDto>();
		expected.add(new MeasureUnitDto (0L, "Unit0"));
		expected.add(new MeasureUnitDto (1L, "Unit1"));
		expected.add(new MeasureUnitDto (2L, "Unit2"));
		
		assertEquals(expected, actual);
	}

}
