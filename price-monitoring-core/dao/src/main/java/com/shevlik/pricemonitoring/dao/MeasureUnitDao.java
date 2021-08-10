package com.shevlik.pricemonitoring.dao;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.api.dao.IMeasureUnitDao;
import com.shevlik.pricemonitoring.model.MeasureUnit;

@Repository
public class MeasureUnitDao extends AbstractDao<MeasureUnit> implements IMeasureUnitDao{

	@Override
	protected Class<MeasureUnit> getClazz() {
		return MeasureUnit.class;
	}

}
