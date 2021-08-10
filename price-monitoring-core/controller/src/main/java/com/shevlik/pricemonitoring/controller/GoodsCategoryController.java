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

import com.shevlik.pricemonitoring.api.service.IGoodsCategoryService;
import com.shevlik.pricemonitoring.model.dto.GoodsCategoryDto;

import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class GoodsCategoryController {
	
	private final IGoodsCategoryService categoryService;
	
	
	@GetMapping
	public ResponseEntity<List<GoodsCategoryDto>> getAllCategories(){
		List<GoodsCategoryDto> dtoList = categoryService.getAll();
		return ResponseEntity.ok(dtoList);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<GoodsCategoryDto> getCategoryById(@PathVariable (name = "id") Long id){
		GoodsCategoryDto dto = categoryService.getById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping ("/mod")
	public ResponseEntity<Void> addNewCategory(@RequestBody GoodsCategoryDto category){
		categoryService.save(category);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping ("/mod")
	public ResponseEntity<Void> updateCategory (@RequestBody GoodsCategoryDto category){
		categoryService.update(category);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping ("/mod/{id}")
	public ResponseEntity<Void> deleteCategory (@PathVariable (name = "id") Long id){
		categoryService.delete(id);
		return ResponseEntity.ok(null);
	}

}
