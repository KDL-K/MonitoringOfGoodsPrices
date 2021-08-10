package com.shevlik.pricemonitoring.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shevlik.pricemonitoring.api.dao.IGoodsCategoryDao;
import com.shevlik.pricemonitoring.api.dao.IGoodsSectionDao;
import com.shevlik.pricemonitoring.api.dao.IMeasureUnitDao;
import com.shevlik.pricemonitoring.api.dao.IOriginCountryDao;
import com.shevlik.pricemonitoring.api.dao.IProductDao;
import com.shevlik.pricemonitoring.api.service.IProductService;
import com.shevlik.pricemonitoring.api.service.ReadWriteDataFileException;
import com.shevlik.pricemonitoring.model.GoodsCategory;
import com.shevlik.pricemonitoring.model.GoodsSection;
import com.shevlik.pricemonitoring.model.MeasureUnit;
import com.shevlik.pricemonitoring.model.OriginCountry;
import com.shevlik.pricemonitoring.model.Product;
import com.shevlik.pricemonitoring.model.dto.OriginCountryDto;
import com.shevlik.pricemonitoring.model.dto.ProductDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductService implements IProductService{
	private final IProductDao productDao;
	private final IMeasureUnitDao unitDao;
	private final IGoodsSectionDao sectionDao;
	private final IGoodsCategoryDao categoryDao;
	private final IOriginCountryDao originDao;
	private final ModelMapper mapper;

	@Override
	public void save(ProductDto entity) {
		log.info ("ProductService. IN save - product: {}", entity.getName());
		Product product = mapper.map(entity, Product.class);
		
		log.info ("ProductService. IN save - getting measureUnit");
		MeasureUnit unit = unitDao.getById(product.getMeasureUnit().getId());
		log.info ("ProductService. IN save - getting goodsCategory");
		GoodsCategory category = categoryDao.getById(product.getGoodsCategory().getId());
		log.info ("ProductService. IN save - getting originCountry");
		OriginCountry origin = originDao.getById(product.getOriginCountry().getId());
		
		product.setMeasureUnit(unit);
		product.setGoodsCategory(category);
		product.setOriginCountry(origin);
		
		log.info ("ProductService. IN save - saving product");
		productDao.save(product);
	}

	@Override
	public ProductDto getById(Long id) {
		log.info ("ProductService. IN getById - id: {}", id);
		Product product = productDao.getById(id);
		ProductDto productDto = mapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public void update(ProductDto entity) {
		log.info ("ProductService. IN update - id: {}", entity.getId());
		Product product = productDao.getById(entity.getId());
		mapper.map(entity, product);
		productDao.update(product);
	}

	@Override
	public void delete(Long id) {
		log.info ("ProductService. IN delete - id: {}", id);
		Product product = productDao.getById(id);
		productDao.delete(product);
	}

	@Override
	public List<ProductDto> getAll() {
		log.info ("ProductService. IN getAll");
		List<Product> products = productDao.getAll();
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		List<ProductDto> productsDto = mapper.map(products, listType);
		return productsDto;
	}

	@Override
	public List<ProductDto> getAllBySection(Long sectionId) {
		log.info ("ProductService. IN getAllBySection - section id: {}", sectionId);
		GoodsSection section = sectionDao.getById(sectionId);
		List<Product> products = productDao.getAllBySection(section);
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		List<ProductDto> productsDto = mapper.map(products, listType);
		return productsDto;
	}
	
	@Override
	public List<ProductDto> getAllByCategory(Long categoryId) {
		log.info ("ProductService. IN getAllByCategory - category id: {}", categoryId);
		GoodsCategory category = categoryDao.getById(categoryId);
		List<Product> products = productDao.getAllByCategory(category);
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		List<ProductDto> productsDto = mapper.map(products, listType);
		return productsDto;
	}

	@Override
	public List<ProductDto> getByNameStartsWith(String name) {
		log.info ("ProductService. IN getByNameStartsWith - name for search: {}", name);
		List<Product> products = productDao.getByNameStartsWith(name);
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		List<ProductDto> productsDto = mapper.map(products, listType);
		return productsDto;
	}

	@Override
	public List<ProductDto> getByOrigin(Long categoryId, List<OriginCountryDto> dtoList) {
		log.info ("ProductService. IN getByOrigin");
		GoodsCategory category = categoryDao.getById(categoryId);
		
		List<Long> originIdList = dtoList.stream().map(country -> country.getId()).collect(Collectors.toList());
		
		log.info ("ProductService. IN getByOrigin - getting originList");
		List<OriginCountry> originList = originDao.getAllByIdSet(originIdList);
		
		log.info ("ProductService. IN getByOrigin - getting necessary products");
		List<Product> products = productDao.getByOrigin(category, originList);
		Type listType = new TypeToken<List<ProductDto>>() {}.getType();
		List<ProductDto> productsDto = mapper.map(products, listType);
		return productsDto;
	}

	@Override
	public void createProductFile(String fileName) {
		final String FILENAME_EXSTENSION = "xls";
		final String FILE_OUT_NAME = String.join(".", fileName, FILENAME_EXSTENSION);
		
		log.info ("ProductService. IN createProductFile - getting products");
		List<Product> products = productDao.getAll();
		
		log.info ("ProductService. IN createProductFile - write products in WorkBook: {}", FILE_OUT_NAME);
		
		try (FileOutputStream fileOut = new FileOutputStream(FILE_OUT_NAME); HSSFWorkbook workBook = new HSSFWorkbook()){
			formDataForWorkBook(products, workBook);	
			workBook.write(fileOut);
		} catch (FileNotFoundException e) {
			log.warn("ProductService. IN createProductFile - filename: {}. File cannot be created or opened, or it's a directory.", FILE_OUT_NAME);
			throw new ReadWriteDataFileException (e);
		} catch (IOException e) {
			log.warn("AssortmentService. IN createProductFile - filename: {}. Cannot write in file.", FILE_OUT_NAME);
			throw new ReadWriteDataFileException (e);
		}
		
	}
	
	private void formDataForWorkBook(List<Product> products, HSSFWorkbook workBook) {
		log.info ("ProductService. IN private formDataForWorkBook");
		final String TABLE_NAME = "PRODUCT LIST";
		final String PRODUCT_CATEGORY_ID = "Category ID";
		final String PRODUCT_CATEGORY = "Category";
		final String PRODUCT_NAME = "Title";
		final String PRODUCT_ID = "Product ID";
		final String MEASURE_UNIT_ID = "Unit ID";
		final String MEASURE_UNIT = "Unit";
		final String ORIGIN_COUNTRY_ID = "Origin ID";
		final String ORIGIN_COUNTRY = "Origin";
		
		HSSFSheet sheet= workBook.createSheet();
		
		int productIndex = 0;
		for(Product product : products) {
			if(productIndex==0) {
				log.info ("ProductService. IN private formDataForWorkBook - create cell with table name: {}", TABLE_NAME);
				HSSFRow row=sheet.createRow(productIndex);
				row.createCell(0).setCellValue(TABLE_NAME);
				productIndex++;
			}
			if(productIndex==1) {
				log.info ("ProductService. IN private formDataForWorkBook - create cells with names of table columns");
				HSSFRow row=sheet.createRow(productIndex);
				row.createCell(0).setCellValue(PRODUCT_CATEGORY_ID);
				row.createCell(1).setCellValue(PRODUCT_CATEGORY);
				row.createCell(2).setCellValue(PRODUCT_NAME);
				row.createCell(3).setCellValue(PRODUCT_ID);
				row.createCell(4).setCellValue(MEASURE_UNIT_ID);
				row.createCell(5).setCellValue(MEASURE_UNIT);
				row.createCell(6).setCellValue(ORIGIN_COUNTRY_ID);
				row.createCell(7).setCellValue(ORIGIN_COUNTRY);
				productIndex++;
			}	
			log.info ("ProductService. IN private formDataForWorkBook - fill in cells");
			HSSFRow row=sheet.createRow(productIndex);
			row.createCell(0).setCellValue(product.getGoodsCategory().getId());
			row.createCell(1).setCellValue(product.getGoodsCategory().getTitle());
			row.createCell(2).setCellValue(product.getName());
			row.createCell(3).setCellValue(product.getId());
			row.createCell(4).setCellValue(product.getMeasureUnit().getId());
			row.createCell(5).setCellValue(product.getMeasureUnit().getTitle());
			row.createCell(6).setCellValue(product.getOriginCountry().getId());
			row.createCell(7).setCellValue(product.getOriginCountry().getTitle());
			productIndex++;
		}	
	}

	@Override
	public void loadProductsToDB(String fileName) {
		final String FILENAME_EXSTENSION = "xls";
		final String FILE_IN_NAME = String.join(".", fileName, FILENAME_EXSTENSION);
		
		try (FileInputStream fileIn = new FileInputStream(FILE_IN_NAME); 
				POIFSFileSystem fileSystem = new POIFSFileSystem(fileIn); 
				HSSFWorkbook workBook=new HSSFWorkbook(fileSystem)){
			readWorkBookAndWriteToDB(FILE_IN_NAME, workBook);
		} catch (FileNotFoundException e) {
			log.warn("ProductService. IN loadProductsToDB - filename: {}. File does not exist or cannot be opened, or it's a directory.", FILE_IN_NAME);
			throw new ReadWriteDataFileException (e);
		} catch (IOException e) {
			log.warn("ProductService. IN loadProductsToDB - filename: {}. Working with workbook exception", FILE_IN_NAME);
			throw new ReadWriteDataFileException (e);
		}
		
	}
	
	private void readWorkBookAndWriteToDB(String fileInName, HSSFWorkbook workBook) {
		HSSFSheet sheet= workBook.getSheetAt(0);
		Iterator<Row> rowIter = sheet.rowIterator();
		int productIndex = 0;
		while (rowIter.hasNext()) {
			if (productIndex > 1) {
				log.info("ProductService. IN private readWorkBookAndWriteToDB - getting cells");
				HSSFRow row = (HSSFRow) rowIter.next();
				HSSFCell categoryIdCell = row.getCell(0);
				HSSFCell categoryNameCell = row.getCell(1);
				HSSFCell productNameCell = row.getCell(2);
				HSSFCell unitIdCell = row.getCell(4);
				HSSFCell unitNameCell = row.getCell(5);
				HSSFCell originIdCell = row.getCell(6);
				HSSFCell originNameCell = row.getCell(7);
				
				if(categoryIdCell==null || categoryNameCell==null || productNameCell==null 
						|| unitIdCell==null || unitNameCell==null 
						|| originIdCell==null || originNameCell==null) {
					log.warn("ProductService. IN private readWorkBookAndWriteToDB - one or more cells of row: {} are empty.",(productIndex+1));
					throw new ReadWriteDataFileException("One or more cells of row #" + (productIndex+1)+ " are empty.");
				}
				
				log.info("ProductService. IN private readWorkBookAndWriteToDB - getting values of cells");
				Long categoryId = Double.valueOf(categoryIdCell.getNumericCellValue()).longValue();
				String categoryName = categoryNameCell.getStringCellValue();
				String productName = productNameCell.getStringCellValue();
				Long unitId = Double.valueOf(unitIdCell.getNumericCellValue()).longValue();
				String unitName = unitNameCell.getStringCellValue();
				Long originId = Double.valueOf(originIdCell.getNumericCellValue()).longValue();
				String originName = originNameCell.getStringCellValue();
				
				GoodsCategory category = categoryDao.getById(categoryId);
				MeasureUnit unit = unitDao.getById(unitId);
				OriginCountry origin = originDao.getById(originId);
				
				if(!category.getTitle().equalsIgnoreCase(categoryName)) {
					log.warn("ProductService. IN private readWorkBookAndWriteToDB - category name of product: {} is not valid.", productName);
					throw new ReadWriteDataFileException("Category name of product: " + productName + " is not valid.");
				}
				if(!unit.getTitle().equalsIgnoreCase(unitName)) {
					log.warn("ProductService. IN private readWorkBookAndWriteToDB - unit name of product: {} is not valid.", productName);
					throw new ReadWriteDataFileException("Unit name of product: " + productName + " is not valid.");
				}
				if(!origin.getTitle().equalsIgnoreCase(originName)) {
					log.warn("ProductService. IN private readWorkBookAndWriteToDB - origin name of product: {} is not valid.", productName);
					throw new ReadWriteDataFileException("Origin name of product: " + productName + " is not valid.");
				}
				
				Product product = new Product();
				product.setName(productName);
				product.setGoodsCategory(category);
				product.setMeasureUnit(unit);
				product.setOriginCountry(origin);
				
				log.info("ProductService. IN private readWorkBookAndWriteToDB - saving product: {}", productName);
				productDao.save(product);
			
				productIndex++;
			}else {
				rowIter.next();
				productIndex++;
			}
		}
		
	}
	
}
