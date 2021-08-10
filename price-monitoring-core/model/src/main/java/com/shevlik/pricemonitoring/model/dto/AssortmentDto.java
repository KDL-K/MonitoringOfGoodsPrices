package com.shevlik.pricemonitoring.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AssortmentDto {
	private Long id;
	@NotEmpty (message = "'price' cannot be empty")
	@Positive (message = "'price' has to be positive")
	private Double price;
	private LocalDate updateDate;
	@NotNull (message = "'product' cannot be null")
	private ProductDto product;
	@NotNull (message = "'tradeStore' cannot be null")
	private TradeStoreDto tradeStore;

}
