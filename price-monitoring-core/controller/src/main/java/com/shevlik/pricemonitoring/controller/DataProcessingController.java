package com.shevlik.pricemonitoring.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shevlik.pricemonitoring.api.service.IAssortmentService;
import com.shevlik.pricemonitoring.api.service.IProductService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping ("/data-processing")
public class DataProcessingController {
	private final IAssortmentService assortmentService;
	private final IProductService productService;
	
	@GetMapping("/assortment/{storeId}/{file-name}")
	public ResponseEntity<Void> getAssortmentFile(@PathVariable (name = "storeId") Long storeId, @PathVariable (name = "file-name") String fileName){
		assortmentService.createAssortmentFile(fileName, storeId);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/assortment/{storeId}/{file-name}")
	public ResponseEntity<Void> loadAssortmentToBD(@PathVariable (name = "storeId") Long storeId, @PathVariable (name = "file-name") String fileName){
		assortmentService.loadAssortmentToDB(fileName, storeId);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/products/{file-name}")
	public ResponseEntity<Void> getProductFile(@PathVariable (name = "file-name") String fileName){
		productService.createProductFile(fileName);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/products/{file-name}")
	public ResponseEntity<Void> loadProductsToBD(@PathVariable (name = "file-name") String fileName){
		productService.loadProductsToDB(fileName);
		return ResponseEntity.ok(null);
	}

}
