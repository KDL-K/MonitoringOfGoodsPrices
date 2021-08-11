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

import com.shevlik.pricemonitoring.api.service.IGoodsSectionService;
import com.shevlik.pricemonitoring.controller.util.ModelValidator;
import com.shevlik.pricemonitoring.model.dto.GoodsSectionDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sections")
@RequiredArgsConstructor
public class GoodsSectionController {
	
	private final IGoodsSectionService sectionService;
	private final ModelValidator validator;
	
	@GetMapping
	public ResponseEntity<List<GoodsSectionDto>> getAllSections(){
		List<GoodsSectionDto> dtoList = sectionService.getAll();
		return ResponseEntity.ok(dtoList);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<GoodsSectionDto> getSectionById(@PathVariable (name = "id") Long id){
		GoodsSectionDto dto = sectionService.getById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping ("/mod")
	public ResponseEntity<Void> addNewSection(@RequestBody GoodsSectionDto dto){
		validator.validate(dto);
		sectionService.save(dto);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping ("/mod")
	public ResponseEntity<Void> updateSection (@RequestBody GoodsSectionDto dto){
		validator.validate(dto);
		sectionService.update(dto);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping ("/mod/{id}")
	public ResponseEntity<Void> deleteSection (@PathVariable (name = "id") Long id){
		sectionService.delete(id);
		return ResponseEntity.ok(null);
	}

}
