package com.shevlik.pricemonitoring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.shevlik.pricemonitoring.api.dao.GenericDao;

public abstract class AbstractDao<T> implements GenericDao<T>{
	
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	protected EntityManager entityManager;
	
	public void save (T entity) {
		entityManager.persist(entity);
	}
	
	public T getById (Long id) {
		T object = entityManager.find(getClazz(), id);
		return object;
	}
	
	public void update (T entity) {
		entityManager.merge(entity);
	}
	
	public void delete (T entity) {
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity));
	}
	
	public List<T> getAll (){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(getClazz());
		Root<T> root = query.from(getClazz());
		query.select(root);
		List<T> tList = entityManager.createQuery(query).getResultList();
	
		return tList;
	}
	
	protected abstract Class<T> getClazz();

}
