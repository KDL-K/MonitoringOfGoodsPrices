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

import com.shevlik.pricemonitoring.api.service.IMeasureUnitService;
import com.shevlik.pricemonitoring.model.dto.MeasureUnitDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/units")
@RequiredArgsConstructor
public class MeasureUnitController {
	
	private final IMeasureUnitService unitService;
	
	@GetMapping
	public ResponseEntity<List<MeasureUnitDto>> getAllUnits(){
		List<MeasureUnitDto> dtoList = unitService.getAll();
		return ResponseEntity.ok(dtoList);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<MeasureUnitDto> getUnitById(@PathVariable (name = "id") Long id){
		MeasureUnitDto dto = unitService.getById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping ("/mod")
	public ResponseEntity<Void> addNewUnit(@RequestBody MeasureUnitDto unit){
		unitService.save(unit);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping ("/mod")
	public ResponseEntity<Void> updateUnit (@RequestBody MeasureUnitDto unit){
		unitService.update(unit);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping ("/mod/{id}")
	public ResponseEntity<Void> deleteUnit (@PathVariable (name = "id") Long id){
		unitService.delete(id);
		return ResponseEntity.ok(null);
	}

}
