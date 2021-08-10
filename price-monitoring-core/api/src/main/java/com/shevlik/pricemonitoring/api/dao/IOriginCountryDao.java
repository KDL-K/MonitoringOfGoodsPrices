package com.shevlik.pricemonitoring.api.dao;

import java.util.List;

import com.shevlik.pricemonitoring.model.OriginCountry;

public interface IOriginCountryDao extends GenericDao<OriginCountry>{

	List<OriginCountry> getAllByIdSet(List<Long> originIdList);

}
