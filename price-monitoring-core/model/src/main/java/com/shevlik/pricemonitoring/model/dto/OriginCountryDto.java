package com.shevlik.pricemonitoring.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class OriginCountryDto {
	@NotEmpty (message = "'id' cannot be empty")
	@Positive (message = "'id' has to be positive")
	private Long id;
	@NotBlank (message = "'title' cannot be blank")
	private String title;
}
