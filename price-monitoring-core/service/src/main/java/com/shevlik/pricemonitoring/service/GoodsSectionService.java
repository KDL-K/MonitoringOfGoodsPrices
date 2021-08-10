package com.shevlik.pricemonitoring.service;


import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shevlik.pricemonitoring.api.dao.IGoodsSectionDao;
import com.shevlik.pricemonitoring.api.service.IGoodsSectionService;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.dto.GoodsSectionDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class GoodsSectionService implements IGoodsSectionService{
	private final IGoodsSectionDao goodsSectionDao;
	private final ModelMapper mapper;

	@Override
	public void save(GoodsSectionDto entity) {
		log.info ("GoodsSectionService. IN save");
		GoodsSection section = mapper.map(entity, GoodsSection.class);
		goodsSectionDao.save(section);
	}

	@Override
	public GoodsSectionDto getById(Long id) {
		log.info ("GoodsSectionService. IN getById - id: {}", id);
		GoodsSection section = goodsSectionDao.getById(id);
		GoodsSectionDto dto = mapper.map(section, GoodsSectionDto.class);
		return dto;
	}

	@Override
	public void update(GoodsSectionDto entity) {
		log.info ("GoodsSectionService. IN update");
		GoodsSection section = goodsSectionDao.getById(entity.getId());
	    mapper.map(entity, section);
		goodsSectionDao.update(section);
	}

	@Override
	public void delete(Long id) {
		log.info ("GoodsSectionService. IN delete - id: {}", id);
		GoodsSection section = goodsSectionDao.getById(id);
		goodsSectionDao.delete(section);
	}

	@Override
	public List<GoodsSectionDto> getAll() {
		log.info ("GoodsSectionService. IN getAll");
		List<GoodsSection> goodsSections = goodsSectionDao.getAll();
		Type listType = new TypeToken<List<GoodsSectionDto>>() {}.getType();
		List<GoodsSectionDto> goodsSectionDtoList = mapper.map(goodsSections, listType);
		return goodsSectionDtoList;
	}

}
