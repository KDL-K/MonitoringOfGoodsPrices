package com.shevlik.pricemonitoring.dao;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.api.dao.IAddressDao;
import com.shevlik.pricemonitoring.model.Address;

@Repository
public class AddressDao extends AbstractDao<Address> implements IAddressDao{

	@Override
	protected Class<Address> getClazz() {
		return Address.class;
	}
}
