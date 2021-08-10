package com.shevlik.pricemonitoring.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shevlik.pricemonitoring.api.service.IProductService;
import com.shevlik.pricemonitoring.model.dto.OriginCountryDto;
import com.shevlik.pricemonitoring.model.dto.ProductDto;
import com.shevlik.pricemonitoring.model.dto.SearchingProductNameDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/products")
@RequiredArgsConstructor
public class ProductController {
	private final IProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> products = productService.getAll();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping ("/sections/{id}")
	public ResponseEntity<List<ProductDto>> getProductBySection(@PathVariable (name = "id") Long id){
		List<ProductDto> products = productService.getAllBySection(id);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping ("/categories/{id}")
	public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable (name = "id") Long id){
		List<ProductDto> products = productService.getAllByCategory(id);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping ("/by-name")
	public ResponseEntity<List<ProductDto>> getProductsByNameStartsWith (@RequestBody SearchingProductNameDto dto){
		List<ProductDto> products = productService.getByNameStartsWith(dto.getSearchingName());
		return ResponseEntity.ok(products);
	}
	
	@GetMapping ("/categories/{id}/by-origin")
	public ResponseEntity<List<ProductDto>> getProductsByOrigin (@PathVariable (name = "id") Long categoryId, @RequestBody List<OriginCountryDto> dtoList){
		List<ProductDto> products = productService.getByOrigin(categoryId, dtoList);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable (name = "id") Long id){
		ProductDto product = productService.getById(id);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping ("/mod")
	public ResponseEntity<Void> addNewProduct(@RequestBody ProductDto product){
		productService.save(product);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping ("/mod")
	public ResponseEntity<Void> updateProduct (@RequestBody ProductDto product){
		productService.update(product);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping ("/mod/{id}")
	public ResponseEntity<Void> removeProduct (@PathVariable (name = "id") Long id){
		productService.delete(id);
		return ResponseEntity.ok(null);
	}

}
