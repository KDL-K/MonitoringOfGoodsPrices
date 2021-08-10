package com.shevlik.pricemonitoring.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shevlik.pricemonitoring.api.dao.IOriginCountryDao;
import com.shevlik.pricemonitoring.api.service.IOriginCountryService;
import com.shevlik.pricemonitoring.model.OriginCountry;
import com.shevlik.pricemonitoring.model.dto.OriginCountryDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class OriginCountryService implements IOriginCountryService{

	private final IOriginCountryDao originDao;
	private final ModelMapper mapper;
	
	@Override
	public void save(OriginCountryDto entity) {
		log.info ("OriginCountryService. IN save");
		OriginCountry origin = mapper.map(entity, OriginCountry.class);
		originDao.save(origin);
	}

	@Override
	public OriginCountryDto getById(Long id) {
		log.info ("OriginCountryService. IN getById - origin id: {}", id);
		OriginCountry origin = originDao.getById(id);
		OriginCountryDto dto = mapper.map(origin, OriginCountryDto.class);
		return dto;
	}

	@Override
	public void update(OriginCountryDto entity) {
		log.info ("OriginCountryService. IN update - origin id: {}", entity.getId());
		OriginCountry origin = originDao.getById(entity.getId());
		mapper.map(entity, origin);
		originDao.update(origin);	
	}

	@Override
	public void delete(Long id) {
		log.info ("OriginCountryService. IN delete - origin id: {}", id);
		OriginCountry origin = originDao.getById(id);
		originDao.delete(origin);		
	}

	@Override
	public List<OriginCountryDto> getAll() {
		log.info ("OriginCountryService. IN getAll");
		List<OriginCountry> originList = originDao.getAll();
		Type listType = new TypeToken<List<OriginCountryDto>>() {}.getType();
		List<OriginCountryDto> dtoList = mapper.map(originList, listType);
		return dtoList;
	}

}
