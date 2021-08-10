package com.shevlik.pricemonitoring.model.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TradeStoreDto {
	private Long id;
	@NotBlank (message = "'name' cannot be blank")
	private String name;
	@NotBlank (message = "'phone' cannot be blank")
	private String phone;
	@NotNull (message = "'address' cannot be null")
	private AddressDto address;

}
