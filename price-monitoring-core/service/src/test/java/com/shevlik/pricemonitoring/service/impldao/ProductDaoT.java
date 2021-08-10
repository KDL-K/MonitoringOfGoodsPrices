package com.shevlik.pricemonitoring.service.impldao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shevlik.pricemonitoring.api.dao.IProductDao;
import com.shevlik.pricemonitoring.model.GoodsCategory;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.OriginCountry;
import com.shevlik.pricemonitoring.model.Product;

public class ProductDaoT implements IProductDao{
	private Map<Long, Product> productMap;
	
	public ProductDaoT(Map<Long, Product> productMap) {
		this.productMap = productMap;
	}

	@Override
	public void save(Product entity) {
		if (entity.getId() == null) {
			entity.setId(0L);
			productMap.put(0L, entity);
		}else{
			productMap.put(entity.getId(), entity);
		}
		
	}

	@Override
	public Product getById(Long id) {
		return productMap.get(id);
	}

	@Override
	public void update(Product entity) {
		productMap.put(entity.getId(), entity);
		
	}

	@Override
	public void delete(Product entity) {
		productMap.remove(entity.getId());
		
	}

	@Override
	public List<Product> getAll() {
		List<Product> list = productMap.entrySet()
				.stream().map(a ->a.getValue())
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<Product> getByNameStartsWith(String name) {
		List<Product> products = productMap.entrySet().stream()
				.filter(p->p.getValue().getName().toUpperCase().startsWith(name.toUpperCase()))
				.map(p->p.getValue()).collect(Collectors.toList());
	    return products;
	}

	@Override
	public List<Product> getAllByCategory(GoodsCategory category) {
		List<Product> products = productMap.entrySet().stream()
				.filter(p->p.getValue().getGoodsCategory().equals(category))
				.map(p->p.getValue()).collect(Collectors.toList());
	    return products;
	}

	@Override
	public List<Product> getAllBySection(GoodsSection section) {
		List<Product> products = productMap.entrySet().stream()
				.filter(p->p.getValue().getGoodsCategory().getGoodsSection().equals(section))
				.map(p->p.getValue()).collect(Collectors.toList());
	    return products;
	}

	@Override
	public List<Product> getByOrigin(GoodsCategory category, List<OriginCountry> originList) {
		List<Product> products = productMap.entrySet().stream()
				.filter(p->p.getValue().getGoodsCategory().equals(category))
				.filter(p->originList.stream().anyMatch(o->o.equals(p.getValue().getOriginCountry())))
				.map(p->p.getValue()).collect(Collectors.toList());
	    return products;
	}
}
