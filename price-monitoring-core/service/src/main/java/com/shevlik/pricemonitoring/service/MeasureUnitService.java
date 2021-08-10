package com.shevlik.pricemonitoring.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shevlik.pricemonitoring.api.dao.IMeasureUnitDao;
import com.shevlik.pricemonitoring.api.service.IMeasureUnitService;
import com.shevlik.pricemonitoring.model.MeasureUnit;
import com.shevlik.pricemonitoring.model.dto.MeasureUnitDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MeasureUnitService implements IMeasureUnitService{
	private final IMeasureUnitDao unitDao;
	private final ModelMapper mapper;
	
	@Override
	public void save(MeasureUnitDto entity) {
		log.info ("MeasureUnitService. IN save");
		MeasureUnit unit = mapper.map(entity, MeasureUnit.class);
		unitDao.save(unit);
	}

	@Override
	public MeasureUnitDto getById(Long id) {
		log.info ("MeasureUnitService. IN getById - unit id: {}", id);
		MeasureUnit unit = unitDao.getById(id);
		MeasureUnitDto dto = mapper.map(unit, MeasureUnitDto.class);
		return dto;
	}

	@Override
	public void update(MeasureUnitDto entity) {
		log.info ("MeasureUnitService. IN update - unit id: {}", entity.getId());
		MeasureUnit unit = unitDao.getById(entity.getId());
		mapper.map(entity, unit);
		unitDao.update(unit);	
	}

	@Override
	public void delete(Long id) {
		log.info ("MeasureUnitService. IN delete - unit id: {}", id);
		MeasureUnit unit = unitDao.getById(id);
		unitDao.delete(unit);		
	}

	@Override
	public List<MeasureUnitDto> getAll() {
		log.info ("MeasureUnitService. IN getAll");
		List<MeasureUnit> unitList = unitDao.getAll();
		Type listType = new TypeToken<List<MeasureUnitDto>>() {}.getType();
		List<MeasureUnitDto> dtoList = mapper.map(unitList, listType);
		return dtoList;
	}

}
