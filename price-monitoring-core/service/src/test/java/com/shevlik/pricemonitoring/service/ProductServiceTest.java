package com.shevlik.pricemonitoring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.shevlik.pricemonitoring.api.dao.IGoodsCategoryDao;
import com.shevlik.pricemonitoring.api.dao.IGoodsSectionDao;
import com.shevlik.pricemonitoring.api.dao.IMeasureUnitDao;
import com.shevlik.pricemonitoring.api.dao.IOriginCountryDao;
import com.shevlik.pricemonitoring.api.dao.IProductDao;
import com.shevlik.pricemonitoring.api.service.IProductService;
import com.shevlik.pricemonitoring.model.GoodsCategory;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.MeasureUnit;
import com.shevlik.pricemonitoring.model.OriginCountry;
import com.shevlik.pricemonitoring.model.Product;
import com.shevlik.pricemonitoring.model.dto.GoodsCategoryDto;
import com.shevlik.pricemonitoring.model.dto.GoodsSectionLightDto;
import com.shevlik.pricemonitoring.model.dto.MeasureUnitDto;
import com.shevlik.pricemonitoring.model.dto.OriginCountryDto;
import com.shevlik.pricemonitoring.model.dto.ProductDto;
import com.shevlik.pricemonitoring.service.impldao.GoodsCategoryDaoT;
import com.shevlik.pricemonitoring.service.impldao.GoodsSectionDaoT;
import com.shevlik.pricemonitoring.service.impldao.MeasureUnitDaoT;
import com.shevlik.pricemonitoring.service.impldao.OriginCountryDaoT;
import com.shevlik.pricemonitoring.service.impldao.ProductDaoT;

class ProductServiceTest {
	IProductDao productDao;
	IMeasureUnitDao unitDao;
	IGoodsSectionDao sectionDao;
	IGoodsCategoryDao categoryDao;
	IOriginCountryDao originDao;
	
	IProductService productService;

	@BeforeEach
	void setUp() throws Exception {
		Map<Long, Product> productMap = new HashMap<Long, Product>();
		this.productDao = new ProductDaoT(productMap);
		Map<Long, MeasureUnit> unitMap = new HashMap<Long, MeasureUnit>();
		this.unitDao = new MeasureUnitDaoT(unitMap);
		Map<Long, GoodsSection> sectionMap = new HashMap<Long, GoodsSection>();
		this.sectionDao = new GoodsSectionDaoT(sectionMap);
		Map<Long, GoodsCategory> categoryMap = new HashMap<Long, GoodsCategory>();
		this.categoryDao = new GoodsCategoryDaoT(categoryMap);
		Map<Long, OriginCountry> originMap = new HashMap<Long, OriginCountry>();
		this.originDao = new OriginCountryDaoT(originMap);
		
		this.productService = new ProductService(productDao, unitDao, sectionDao, categoryDao, originDao, new ModelMapper());
		
		unitDao.save(new MeasureUnit (0L, "Unti0",null));
		categoryDao.save(new GoodsCategory(0L, "Category0", new GoodsSection(0L,"Section0", null), null));
		originDao.save(new OriginCountry(0L, "Origin0", null));
	}

	@Test
	void testSave() {
		ProductDto dto = new ProductDto (null, "Product0", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0"));
		productService.save(dto);
		Product actual = productDao.getById(0L);
		Product expected = new Product (0L,"Product0", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null);
		assertEquals(expected, actual);
	}

	@Test
	void testGetById() {
        productDao.save(new Product (0L,"Product0", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
		
        ProductDto actual = productService.getById(0L);
        ProductDto expected = new ProductDto (0L, "Product0", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0"));
		assertEquals(expected, actual);
	}

	@Test
	void testUpdate() {
        productDao.save(new Product (0L,"Product0", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));

	    productService.update(new ProductDto (0L, "Product100", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
	    Product actual = productDao.getById(0L);
	    Product expected = new Product (0L,"Product100", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null);
		assertEquals(expected, actual);
	}

	@Test
	void testDelete() {
        productDao.save(new Product (0L,"Product0", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));

		productService.delete(0L);
		assertNull(productDao.getById(0L));
	}

	@Test
	void testGetAll() {
        productDao.save(new Product (0L,"Product0", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
        productDao.save(new Product (1L,"Product1", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
        productDao.save(new Product (2L,"Product2", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
		
		List<ProductDto> actual = productService.getAll();
		
		List<ProductDto> expected = new ArrayList<ProductDto>();
		expected.add(new ProductDto (0L, "Product0", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		expected.add(new ProductDto (1L, "Product1", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		expected.add(new ProductDto (2L, "Product2", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		
		assertEquals(expected, actual);
	}

	@Test
	void testGetAllBySection() {
		sectionDao.save(new GoodsSection(0L, "Section0", null));
		sectionDao.save(new GoodsSection(01L, "Section1", null));
		categoryDao.save(new GoodsCategory(1L, "Category1", new GoodsSection(1L,"Section1", null), null));
		productDao.save(new Product (0L,"Product0", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
        productDao.save(new Product (1L,"Product1", unitDao.getById(0L), categoryDao.getById(1L), originDao.getById(0L), null));
        productDao.save(new Product (2L,"Product2", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
		
        List<ProductDto> actual = productService.getAllBySection(0L);
        List<ProductDto> expected = new ArrayList<ProductDto>();
		expected.add(new ProductDto (0L, "Product0", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		expected.add(new ProductDto (2L, "Product2", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		assertEquals(expected, actual);

	}

	@Test
	void testGetAllByCategory() {
		categoryDao.save(new GoodsCategory(1L, "Category1", new GoodsSection(1L,"Section1", null), null));
		productDao.save(new Product (0L,"Product0", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
        productDao.save(new Product (1L,"Product1", unitDao.getById(0L), categoryDao.getById(1L), originDao.getById(0L), null));
        productDao.save(new Product (2L,"Product2", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
		
        List<ProductDto> actual = productService.getAllByCategory(0L);
        List<ProductDto> expected = new ArrayList<ProductDto>();
		expected.add(new ProductDto (0L, "Product0", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		expected.add(new ProductDto (2L, "Product2", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		assertEquals(expected, actual);
	}

	@Test
	void testGetByNameStartsWith() {
		productDao.save(new Product (0L,"Reggia", unitDao.getById(0L), categoryDao.getById(0L),  originDao.getById(0L), null));
        productDao.save(new Product (1L,"Barilla", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
        productDao.save(new Product (2L,"Pasta Zara", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
        productDao.save(new Product (3L,"Barilla2", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
        final String START_OF_SEARCHING_NAME = "ba";
        
        List<ProductDto> actual = productService.getByNameStartsWith(START_OF_SEARCHING_NAME);
        List<ProductDto> expected = new ArrayList<ProductDto>();
		expected.add(new ProductDto (1L, "Barilla", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		expected.add(new ProductDto (3L, "Barilla2", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		assertEquals(expected, actual);
	}

	@Test
	void testGetByOrigin() {
		originDao.save(new OriginCountry(1L, "Origin1", null));
		originDao.save(new OriginCountry(2L, "Origin2", null));
		categoryDao.save(new GoodsCategory(1L, "Category1", new GoodsSection(1L,"Section1", null), null));
		productDao.save(new Product (0L,"Product0", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(0L), null));
        productDao.save(new Product (1L,"Product1", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(1L), null));
        productDao.save(new Product (2L,"Product2", unitDao.getById(0L), categoryDao.getById(1L), originDao.getById(0L), null));
        productDao.save(new Product (3L,"Product3", unitDao.getById(0L), categoryDao.getById(0L), originDao.getById(2L), null));
		
        
        List<OriginCountryDto> dtoList = new ArrayList<OriginCountryDto>();
        dtoList.add(new OriginCountryDto(0L, "Origin0"));
        dtoList.add(new OriginCountryDto(2L, "Origin2"));
        
        List<ProductDto> actual = productService.getByOrigin(0L, dtoList);
        List<ProductDto> expected = new ArrayList<ProductDto>();
		expected.add(new ProductDto (0L, "Product0", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(0L, "Origin0")));
		expected.add(new ProductDto (3L, "Product3", 
				new MeasureUnitDto (0L, "Unti0"), 
				new GoodsCategoryDto(0L, "Category0", new GoodsSectionLightDto(0L,"Section0")), 
				new OriginCountryDto(2L, "Origin2")));
		assertEquals(expected, actual);
	}

}
