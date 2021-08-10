package com.shevlik.pricemonitoring.api.dao;

import java.util.List;

import com.shevlik.pricemonitoring.model.GoodsCategory;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.OriginCountry;
import com.shevlik.pricemonitoring.model.Product;

public interface IProductDao extends GenericDao<Product>{

	List<Product> getAllByCategory(GoodsCategory category);

	List<Product> getByNameStartsWith(String name);

	List<Product> getAllBySection(GoodsSection section);

	List<Product> getByOrigin(GoodsCategory category, List<OriginCountry> originList);

}
