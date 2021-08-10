package com.shevlik.pricemonitoring.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

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
public class HistoryPeriodDto {
	@NotNull (message = "'productId' cannot be null")
	@PositiveOrZero
	private Long productId;
	@Past
	private LocalDate startDate;
	@PastOrPresent
	private LocalDate endDate;
}
