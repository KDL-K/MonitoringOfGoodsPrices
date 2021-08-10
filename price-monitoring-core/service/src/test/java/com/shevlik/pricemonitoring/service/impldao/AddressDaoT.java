package com.shevlik.pricemonitoring.service.impldao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shevlik.pricemonitoring.api.dao.IAddressDao;
import com.shevlik.pricemonitoring.model.Address;

public class AddressDaoT implements IAddressDao{
	private Map<Long, Address> addressMap;
	
	public AddressDaoT(Map<Long, Address> addressMap) {
		this.addressMap = addressMap;
	}

	@Override
	public void save(Address entity) {
		if (entity.getId() == null) {
			entity.setId(0L);
			addressMap.put(0L, entity);
		}else{
			addressMap.put(entity.getId(), entity);
		}
	}

	@Override
	public Address getById(Long id) {
		return addressMap.get(id);
	}

	@Override
	public void update(Address entity) {
		addressMap.put(entity.getId(), entity);
		
	}

	@Override
	public void delete(Address entity) {
		addressMap.remove(entity.getId());
		
	}

	@Override
	public List<Address> getAll() {
		List<Address> list = addressMap.entrySet()
				.stream().map(a ->a.getValue())
				.collect(Collectors.toList());
		return list;
	}

}
