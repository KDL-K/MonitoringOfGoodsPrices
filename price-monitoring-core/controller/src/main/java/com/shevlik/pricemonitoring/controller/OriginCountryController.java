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

import com.shevlik.pricemonitoring.api.service.IOriginCountryService;
import com.shevlik.pricemonitoring.model.dto.OriginCountryDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/origin")
@RequiredArgsConstructor
public class OriginCountryController {
	
	private final IOriginCountryService originService;
	
	@GetMapping
	public ResponseEntity<List<OriginCountryDto>> getAllOrigin(){
		List<OriginCountryDto> dtoList = originService.getAll();
		return ResponseEntity.ok(dtoList);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<OriginCountryDto> getOriginById(@PathVariable (name = "id") Long id){
		OriginCountryDto dto = originService.getById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping ("/mod")
	public ResponseEntity<Void> addNewOrigin(@RequestBody OriginCountryDto origin){
		originService.save(origin);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping ("/mod")
	public ResponseEntity<Void> updateOrigin (@RequestBody OriginCountryDto origin){
		originService.update(origin);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping ("/mod/{id}")
	public ResponseEntity<Void> deleteOrigin (@PathVariable (name = "id") Long id){
		originService.delete(id);
		return ResponseEntity.ok(null);
	}

}
