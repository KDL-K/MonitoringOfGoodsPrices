package com.shevlik.pricemonitoring.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AddressDto {
	private Long id;
	@NotBlank (message = "'country' cannot be blank")
	private String country;
	@NotBlank (message = "'city' cannot be blank")
	private String city;
	@NotBlank (message = "'street' cannot be blank")
	private String street;
	@NotBlank (message = "'building' cannot be blank")
	private String building;
}
