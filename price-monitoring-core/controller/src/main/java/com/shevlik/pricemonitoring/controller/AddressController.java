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

import com.shevlik.pricemonitoring.api.service.IAddressService;
import com.shevlik.pricemonitoring.controller.util.ModelValidator;
import com.shevlik.pricemonitoring.model.dto.AddressDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/addresses")
@RequiredArgsConstructor
public class AddressController {
	
	private final IAddressService addressService;
	private final ModelValidator validator;
	
	
	@GetMapping
	public ResponseEntity<List<AddressDto>> getAllAddresses(){
		List<AddressDto> dtoList = addressService.getAll();
		return ResponseEntity.ok(dtoList);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<AddressDto> getAddressById(@PathVariable (name = "id") Long id){
		AddressDto dto = addressService.getById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping ("/mod")
	public ResponseEntity<Void> addNewAddress(@RequestBody AddressDto dto){
		validator.validate(dto);
		addressService.save(dto);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping ("/mod")
	public ResponseEntity<Void> updateAddress (@RequestBody AddressDto dto){
		validator.validate(dto);
		addressService.update(dto);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping ("/mod/{id}")
	public ResponseEntity<Void> deleteAddress (@PathVariable (name = "id") Long id){
		addressService.delete(id);
		return ResponseEntity.ok(null);
	}

}
