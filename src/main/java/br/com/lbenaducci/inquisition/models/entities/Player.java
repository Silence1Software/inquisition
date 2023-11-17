package br.com.lbenaducci.inquisition.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Valid
@Entity
@Getter
@Setter
public class Player extends AbstractEntity {

	@NotNull(message="Name must not be null")
	@NotBlank(message="Name must not be blank")
	@Size(min=3, max=50, message="Name must be between 3 and 50 characters")
	@Column(columnDefinition="VARCHAR(50)")
	private String name;
}
