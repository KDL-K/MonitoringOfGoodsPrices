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

import com.shevlik.pricemonitoring.api.service.IAssortmentService;
import com.shevlik.pricemonitoring.model.dto.AssortmentDto;
import com.shevlik.pricemonitoring.model.dto.PriceCompareDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/assortment")
@RequiredArgsConstructor
public class AssortmentController {
	
	private final IAssortmentService assortmentService;

	
	@GetMapping("/{id}")
	public ResponseEntity<AssortmentDto> getAssortmentById(@PathVariable(name = "id") Long id){
		AssortmentDto assortment = assortmentService.getById(id);
		return ResponseEntity.ok(assortment);
	}
	
	@GetMapping("/compare")
	public ResponseEntity<List<PriceCompareDto>> getAssortmentPriceCompare(@RequestBody List<Long> storeIdList){
		List<PriceCompareDto> priceCompareList = assortmentService.getAssortmentPriceCompare(storeIdList);
		return ResponseEntity.ok(priceCompareList);
	}
	
	@PutMapping ("/mod/{id}/new-price/{price}")
	public ResponseEntity<Void> setNewPrice (@PathVariable (name ="id") Long id, @PathVariable (name = "price") Double price){
		assortmentService.setNewPrice(id, price);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping ("/mod")
	public ResponseEntity<Void> createAssortment(@RequestBody AssortmentDto assortment){
		assortmentService.save(assortment);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping ("/mod/{id}")
	public ResponseEntity<Void> deleteAssortment(@PathVariable (name = "id") Long id){
		assortmentService.delete(id);
		return ResponseEntity.ok(null);
	}

}
