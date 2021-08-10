package com.shevlik.pricemonitoring.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import com.shevlik.pricemonitoring.api.dao.IAssortmentDao;
import com.shevlik.pricemonitoring.api.dao.IPriceHistoryDao;
import com.shevlik.pricemonitoring.api.dao.IProductDao;
import com.shevlik.pricemonitoring.api.dao.ITradeStoreDao;
import com.shevlik.pricemonitoring.api.service.IAssortmentService;
import com.shevlik.pricemonitoring.api.service.ReadWriteDataFileException;
import com.shevlik.pricemonitoring.model.Assortment;
import com.shevlik.pricemonitoring.model.PriceHistory;
import com.shevlik.pricemonitoring.model.Product;
import com.shevlik.pricemonitoring.model.TradeStore;
import com.shevlik.pricemonitoring.model.dto.AssortmentDto;
import com.shevlik.pricemonitoring.model.dto.PriceCompareDto;
import com.shevlik.pricemonitoring.model.dto.ProductDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class AssortmentService implements IAssortmentService{
	
	private final IAssortmentDao assortmentDao;
	private final IPriceHistoryDao historyDao;
	private final IProductDao productDao;
	private final ITradeStoreDao storeDao;
	private final ModelMapper mapper;

	@Override
	public void setNewPrice(Long id, Double price) {
		log.info ("AssortmentService. IN setNewPrice - assortment id: {}, price: {}", id, price);
		Assortment assortment = assortmentDao.getById(id);
		assortment.setPrice(price);
		LocalDate priceSettingDate = LocalDate.now();
		assortment.setUpdateDate(priceSettingDate);
		assortmentDao.update(assortment);
		
		Product product = assortment.getProduct();
		
		createHistory(product, priceSettingDate, price);
		log.info ("AssortmentService. IN setNewPrice - new price: {}", assortment.getPrice());
	}

	private void createHistory(Product product, LocalDate priceSettingDate, Double price) {
		log.info ("AssortmentService. IN private createHistory"); 
		PriceHistory history = new PriceHistory();
		history.setPriceDate(priceSettingDate);
		history.setProduct(product);
		history.setPrice(price);
		historyDao.save(history);
	}

	@Override
	public void save(AssortmentDto entity) {
		log.info ("AssortmentService. IN save");

		Assortment assortment = mapper.map(entity, Assortment.class);
		
		Product product = productDao.getById(assortment.getProduct().getId());
		TradeStore store = storeDao.getById(assortment.getTradeStore().getId());
		
		LocalDate priceSettingDate = LocalDate.now();
		
		assortment.setUpdateDate(priceSettingDate);
		assortment.setProduct(product);
		assortment.setTradeStore(store);
		
		assortmentDao.save(assortment);
		
		createHistory(product, priceSettingDate, assortment.getPrice());
		log.info ("AssortmentService. IN save - id of new assortment: {}", assortment.getId());
	}

	@Override
	public AssortmentDto getById(Long id) {
		log.info ("AssortmentService. IN getById - assortment id: {}", id);

		Assortment assortment = assortmentDao.getById(id);
		AssortmentDto assortmentDto = mapper.map(assortment, AssortmentDto.class);
		
		log.info ("AssortmentService. IN getById - result received");
		return assortmentDto;
	}

	@Override
	public void delete(Long id) {
		log.info ("AssortmentService. IN delete - assortment id: {}", id);

		Assortment assortment = assortmentDao.getById(id);
		assortmentDao.delete(assortment);
		
		log.info ("AssortmentService. IN delete - assortment id: {} has deleted", id);

	}

	@Override
	public List<AssortmentDto> getAll() {
		log.info ("AssortmentService. IN getAll");

		List<Assortment> assortmentList = assortmentDao.getAll();
		Type listType = new TypeToken<List<AssortmentDto>>() {}.getType();
		List<AssortmentDto> assortmentDtoList = mapper.map(assortmentList, listType);
		
		log.info ("AssortmentService. IN getAll - result received");
		return assortmentDtoList;
	}

	@Override
	public List<PriceCompareDto> getAssortmentPriceCompare(List<Long> storeIdList) {
		log.info ("AssortmentService. IN getAssortmentPriceCompare - getting stores by ids: {}", storeIdList);
		List<TradeStore> stores = storeDao.getAllByIdSet(storeIdList);
		
		log.info ("AssortmentService. IN getAssortmentPriceCompare - getting assortment by stores");
		List<Assortment> assortmentByStores = assortmentDao.getAssortmentByStores(stores);
		
		Type listType = new TypeToken<List<AssortmentDto>>() {}.getType();
		List<AssortmentDto> assortmentDtoByStores = mapper.map(assortmentByStores, listType);
		
		Map<ProductDto, List<AssortmentDto>> assortmentDtoByStoresMap = assortmentDtoByStores.stream()
				.collect(Collectors.groupingBy(AssortmentDto::getProduct));
		
		log.info ("AssortmentService. IN getAssortmentPriceCompare - getting price compare List");
		List<PriceCompareDto> priceCompareList = getPriceCompareList(assortmentDtoByStoresMap);
		
		return priceCompareList;
	}

	private List<PriceCompareDto> getPriceCompareList(Map<ProductDto, List<AssortmentDto>> assortmentDtoByStoresMap) {
		List<PriceCompareDto> priceCompareList = new ArrayList<PriceCompareDto>();
		
		for(Map.Entry<ProductDto, List<AssortmentDto>> item : assortmentDtoByStoresMap.entrySet()) {
			PriceCompareDto priceCompare = new PriceCompareDto();
			priceCompare.setProduct(item.getKey());
			
			List<AssortmentDto> assortmentList = new ArrayList<AssortmentDto>();
			
			for(AssortmentDto assortmentDto : item.getValue()) {
				assortmentList.add(assortmentDto);
			}
			
			priceCompare.setAssortmentList(assortmentList);
			
			priceCompareList.add(priceCompare);
		}
		return priceCompareList;
	}

	@Override
	public void createAssortmentFile(String fileName, Long storeId) {
		final String FILENAME_EXSTENSION = "xls";
		final String FILE_OUT_NAME = String.join(".", fileName, FILENAME_EXSTENSION);
		
		log.info ("AssortmentService. IN createAssortmentFile - getting store by id: {}", storeId);
		TradeStore store = storeDao.getById(storeId);
		
		log.info ("AssortmentService. IN createAssortmentFile - getting assortment list of store");
		List<Assortment> assortmentList = assortmentDao.getByStore(store);
		
		log.info ("AssortmentService. IN createAssortmentFile - write asssortment in WorkBook: {}", FILE_OUT_NAME);
		
		try (FileOutputStream fileOut = new FileOutputStream(FILE_OUT_NAME); HSSFWorkbook workBook = new HSSFWorkbook()){
			formDataForWorkBook(assortmentList, workBook);	
			workBook.write(fileOut);
		} catch (FileNotFoundException e) {
			log.warn("AssortmentService. IN createAssortmentFile - filename: {}. File cannot be created or opened, or it's a directory.", FILE_OUT_NAME);
			throw new ReadWriteDataFileException (e);
		} catch (IOException e) {
			log.warn("AssortmentService. IN createAssortmentFile - filename: {}. Cannot write in file.", FILE_OUT_NAME);
			throw new ReadWriteDataFileException (e);
		}
	}

	private void formDataForWorkBook(List<Assortment> assortmentList, HSSFWorkbook workBook) {
		log.info ("AssortmentService. IN private formDataForWorkBook");
		final String ASSORTMENT_ID = "ID";
		final String PRODUCT_CATEGORY = "Category";
		final String PRODUCT_NAME = "Name";
		final String PRODUCT_ID = "ID of product";
		final String PRODUCT_PRICE = "Price";
		
		HSSFSheet sheet= workBook.createSheet();
		
		int assortmentIndex = 0;
		for(Assortment assortment : assortmentList) {
			if(assortmentIndex==0) {
				log.info ("AssortmentService. IN private formDataForWorkBook - create cell with name of store");
				HSSFRow row=sheet.createRow(assortmentIndex);
				row.createCell(0).setCellValue(assortment.getTradeStore().getName());
				assortmentIndex++;
			}
			if(assortmentIndex==1) {
				log.info ("AssortmentService. IN private formDataForWorkBook - create cell with address of store");
				HSSFRow row=sheet.createRow(assortmentIndex);
				row.createCell(0).setCellValue(assortment.getTradeStore().getAddress().toString());
				assortmentIndex++;
			}
			if(assortmentIndex==2) {
				log.info ("AssortmentService. IN private formDataForWorkBook - create cells with names of table columns");
				HSSFRow row=sheet.createRow(assortmentIndex);
				row.createCell(0).setCellValue(ASSORTMENT_ID);
				row.createCell(1).setCellValue(PRODUCT_CATEGORY);
				row.createCell(2).setCellValue(PRODUCT_NAME);
				row.createCell(3).setCellValue(PRODUCT_ID);
				row.createCell(4).setCellValue(PRODUCT_PRICE);
				assortmentIndex++;
			}	
			log.info ("AssortmentService. IN private formDataForWorkBook - fill in cells");
			HSSFRow row=sheet.createRow(assortmentIndex);
			row.createCell(0).setCellValue(assortment.getId());
			row.createCell(1).setCellValue(assortment.getProduct().getGoodsCategory().getTitle());
			row.createCell(2).setCellValue(assortment.getProduct().getName());
			row.createCell(3).setCellValue(assortment.getProduct().getId());
			row.createCell(4).setCellValue(assortment.getPrice());
			assortmentIndex++;
		}	
	}

	@Override
	public void loadAssortmentToDB(String fileName, Long storeId) {
		final String FILENAME_EXSTENSION = "xls";
		final String FILE_IN_NAME = String.join(".", fileName, FILENAME_EXSTENSION);
		
		try (FileInputStream fileIn = new FileInputStream(FILE_IN_NAME); POIFSFileSystem fileSystem = new POIFSFileSystem(fileIn); HSSFWorkbook workBook=new HSSFWorkbook(fileSystem)){
			readWorkBookAndWriteToDB(FILE_IN_NAME, workBook, storeId);
		} catch (FileNotFoundException e) {
			log.warn("AssortmentService. IN loadAssortmentToDB - filename: {}. File does not exist or cannot be opened, or it's a directory.", FILE_IN_NAME);
			throw new ReadWriteDataFileException (e);
		} catch (IOException e) {
			log.warn("AssortmentService. IN loadAssortmentToDB - filename: {}. Working with workbook exception", FILE_IN_NAME);
			throw new ReadWriteDataFileException (e);
		}
	}

	private void readWorkBookAndWriteToDB(String fileInName, HSSFWorkbook workBook, Long storeId) {
		HSSFSheet sheet= workBook.getSheetAt(0);
		Iterator<Row> rowIter = sheet.rowIterator();
		int assortmentIndex = 0;
		while (rowIter.hasNext()) {
			if (assortmentIndex > 2) {
				log.info("AssortmentService. IN private readWorkBookAndWriteToDB - getting cells");
				HSSFRow row = (HSSFRow) rowIter.next();
				HSSFCell assortmentIdCell = row.getCell(0);
				HSSFCell productNameCell = row.getCell(2);
				HSSFCell productIdCell = row.getCell(3);
				HSSFCell priceCell = row.getCell(4);
				
				if(productIdCell==null || productNameCell==null || priceCell==null ) {
					log.warn("AssortmentService. IN private readWorkBookAndWriteToDB - one or more cells of row: {} are empty.",(assortmentIndex+1));
					throw new ReadWriteDataFileException("One or more cells of row #" + (assortmentIndex+1)+ " are empty.");
				}
				
				if(assortmentIdCell!=null) {
					Long assortmentId = Double.valueOf(assortmentIdCell.getNumericCellValue()).longValue();
					Double newPrice = priceCell.getNumericCellValue();
					
					log.info ("AssortmentService. IN private readWorkBookAndWriteToDB - assortment - id: {}, setting new price: {}", assortmentId, newPrice);
					Assortment assortment = assortmentDao.getById(assortmentId);
					assortment.setPrice(newPrice);
					assortmentDao.update(assortment);
				} else {
					Long productId = Double.valueOf(productIdCell.getNumericCellValue()).longValue();
					String productName = productNameCell.getStringCellValue();
					
					log.info ("AssortmentService. IN private readWorkBookAndWriteToDB - creating new assortment - product id: {}", productId);
					Assortment assortment = new Assortment();
					Product product = productDao.getById(productId);
					
					if(!product.getName().equalsIgnoreCase(productName)) {
						log.warn("AssortmentService. IN private readWorkBookAndWriteToDB - name of product - id: {} is not valid.", productId);
						throw new ReadWriteDataFileException("Name of product - id: " + productId + " is not valid.");
					}
					
					TradeStore store = storeDao.getById(storeId);
					
					LocalDate priceSettingDate = LocalDate.now();
					
					assortment.setPrice(priceCell.getNumericCellValue());
					assortment.setUpdateDate(priceSettingDate);
					assortment.setProduct(product);
					assortment.setTradeStore(store);
					
					assortmentDao.save(assortment);
					
					createHistory(product, priceSettingDate, assortment.getPrice());
				}
				assortmentIndex++;
			}else {
				rowIter.next();
				assortmentIndex++;
			}
		}
		
	}

}
