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

import com.shevlik.pricemonitoring.api.service.ITradeStoreService;
import com.shevlik.pricemonitoring.model.dto.TradeStoreDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/stores")
@RequiredArgsConstructor
public class TradeStoreController {
	private final ITradeStoreService storeService;
	
	@GetMapping
	public ResponseEntity<List<TradeStoreDto>> getAllStores(){
		List<TradeStoreDto> stores = storeService.getAll();
		return ResponseEntity.ok(stores);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<TradeStoreDto> getStoreById(@PathVariable (name = "id") Long id){
		TradeStoreDto dto = storeService.getById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping ("/mod")
	public ResponseEntity<Void> addNewStore(@RequestBody TradeStoreDto store){
		storeService.save(store);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping ("/mod")
	public ResponseEntity<Void> updateStore (@RequestBody TradeStoreDto store){
		storeService.update(store);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping ("/mod/{id}")
	public ResponseEntity<Void> deleteStore (@PathVariable (name = "id") Long id){
		storeService.delete(id);
		return ResponseEntity.ok(null);
	}

}
