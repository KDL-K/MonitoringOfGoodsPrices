package com.shevlik.pricemonitoring.dao;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.api.dao.IGoodsSectionDao;
import com.shevlik.pricemonitoring.model.GoodsSection;

@Repository
public class GoodsSectionDao extends AbstractDao<GoodsSection> implements IGoodsSectionDao{

	@Override
	protected Class<GoodsSection> getClazz() {
		return GoodsSection.class;
	}

}
