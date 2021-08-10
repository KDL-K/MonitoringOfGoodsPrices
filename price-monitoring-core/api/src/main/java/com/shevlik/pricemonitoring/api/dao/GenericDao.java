package com.shevlik.pricemonitoring.api.dao;

import java.util.List;

public interface GenericDao<T> {
	void save (T entity);
	T getById (Long id);
	void update (T entity);
	void delete (T entity);
	List<T> getAll ();
}
