package com.shevlik.pricemonitoring.dao;

import java.util.List;

import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.api.dao.IProductDao;
import com.shevlik.pricemonitoring.model.GoodsCategory;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.OriginCountry;
import com.shevlik.pricemonitoring.model.Product;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class ProductDao extends AbstractDao<Product> implements IProductDao{
	

	@Override
	protected Class<Product> getClazz() {
		return Product.class;
	}

	@Override
	public List<Product> getAllBySection(GoodsSection section) {
		log.info ("ProductDao. IN getAllBySection - section: {}", section.getTitle());
		
		final String QUERY_SELECT_PRODUCTS_BY_SECTION = "SELECT p FROM Product p "
				+ "WHERE p.goodsCategory.goodsSection=:section ORDER BY p.name ASC";
		
		TypedQuery<Product> query = entityManager.createQuery(QUERY_SELECT_PRODUCTS_BY_SECTION, Product.class);
		query.setParameter("section", section);
		List<Product> products = query.getResultList();
		
		log.info ("ProductDao. IN getAllBySection - result received");

		return products;
	}
	
	@Override
	public List<Product> getAllByCategory(GoodsCategory category) {
		log.info ("ProductDao. IN getAllByCategory - category: {}", category.getTitle());

		final String QUERY_SELECT_PRODUCTS_BY_CATEGORY = "SELECT p FROM Product p "
				+ "WHERE goodsCategory=:category ORDER BY p.name ASC";
		
		TypedQuery<Product> query = entityManager.createQuery(QUERY_SELECT_PRODUCTS_BY_CATEGORY, Product.class);
		query.setParameter("category", category);
		List<Product> products = query.getResultList();
		
		log.info ("ProductDao. IN getAllByCategory - result received");

		return products;
	}

	@Override
	public List<Product> getByNameStartsWith(String name) {
		log.info ("ProductDao. IN getByNameStartsWith - name for search: {}", name);

		final String QUERY_SELECT_PRODUCTS_BY_NAME = "SELECT p FROM Product p "
				+ "WHERE upper(p.name) LIKE concat('%', upper(?1), '%')";
	
		TypedQuery<Product> query = entityManager.createQuery(QUERY_SELECT_PRODUCTS_BY_NAME, Product.class);
		query.setParameter(1, name);
		List<Product> products = query.getResultList();
		
		log.info ("ProductDao. IN getByNameStartsWith - result received");

		return products;
	}

	@Override
	public List<Product> getByOrigin(GoodsCategory category, List<OriginCountry> originList) {
		log.info ("ProductDao. IN getByOrigin - category: {}", category.getTitle());
		final String PARAM_NAME = "origin";

		final String QUERY_SELECT_PRODUCTS_BY_ORIGIN = takeQueryString(PARAM_NAME, originList.size());
		
		TypedQuery<Product> query = entityManager.createQuery(QUERY_SELECT_PRODUCTS_BY_ORIGIN, Product.class);
		
		query.setParameter("category", category);
		int i = 1;
		for (OriginCountry origin : originList){
			query.setParameter(PARAM_NAME+ i++, origin);
		}
	
		List<Product> products = query.getResultList();
		
		log.info ("ProductDao. IN getByOrigin - result received");

		return products;
	}
	
	private String takeQueryString(final String PARAM_NAME, int size) {
		String str = "";
		String subStr = "";
		String token = ":";

		for(int i=0; i<size; i++) {
			if(str.equals("")) {
				str = token+ PARAM_NAME + (i+1);
				continue;
			}
			subStr = String.join(",",str, token+PARAM_NAME+(i+1));
			str = subStr;
		}
		final String queryStr = String.join("","SELECT p FROM Product p WHERE p.goodsCategory=:category AND p.originCountry IN (",str,")");
		
		return queryStr;
	}
	
}
