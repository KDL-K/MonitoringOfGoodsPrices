package com.shevlik.pricemonitoring.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shevlik.pricemonitoring.api.dao.IGoodsCategoryDao;
import com.shevlik.pricemonitoring.api.dao.IGoodsSectionDao;
import com.shevlik.pricemonitoring.api.service.IGoodsCategoryService;
import com.shevlik.pricemonitoring.model.GoodsCategory;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.dto.GoodsCategoryDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class GoodsCategoryService implements IGoodsCategoryService{
	private final IGoodsCategoryDao categoryDao;
	private final IGoodsSectionDao sectionDao;
	private final ModelMapper mapper;

	@Override
	public void save(GoodsCategoryDto entity) {
		log.info ("GoodsCategoryService. IN save");
		GoodsCategory category = mapper.map(entity, GoodsCategory.class);
		
		GoodsSection section = sectionDao.getById(entity.getGoodsSection().getId());
		
		category.setGoodsSection(section);
		categoryDao.save(category);
	}

	@Override
	public GoodsCategoryDto getById(Long id) {
		log.info ("GoodsCategoryService. IN getById - category id: {}", id);
		GoodsCategory category = categoryDao.getById(id);
		GoodsCategoryDto dto = mapper.map(category, GoodsCategoryDto.class);
		return dto;
	}

	@Override
	public void update(GoodsCategoryDto entity) {
		log.info ("GoodsCategoryService. IN update - category id: {}", entity.getId());
		GoodsCategory category = categoryDao.getById(entity.getId());
		mapper.map(entity, category);
		categoryDao.update(category);	
	}

	@Override
	public void delete(Long id) {
		log.info ("GoodsCategoryService. IN delete - category id: {}", id);
		GoodsCategory category = categoryDao.getById(id);
		categoryDao.delete(category);		
	}

	@Override
	public List<GoodsCategoryDto> getAll() {
		log.info ("GoodsCategoryService. IN getAll");
		List<GoodsCategory> categories = categoryDao.getAll();
		Type listType = new TypeToken<List<GoodsCategoryDto>>() {}.getType();
		List<GoodsCategoryDto> dtoList = mapper.map(categories, listType);
		return dtoList;
	}
}
