package com.shevlik.pricemonitoring.dao;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.api.dao.IGoodsCategoryDao;
import com.shevlik.pricemonitoring.model.GoodsCategory;

@Repository
public class GoodsCategoryDao extends AbstractDao<GoodsCategory> implements IGoodsCategoryDao{

	@Override
	protected Class<GoodsCategory> getClazz() {
		return GoodsCategory.class;
	}
	

}
