package br.com.lbenaducci.inquisition.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
public class User extends AbstractEntity {

	@NotNull(message="Username must not be null")
	@NotBlank(message="Username must not be blank")
	@Size(min=3, max=50, message="Username must be between 3 and 50 characters")
	@Column(columnDefinition="VARCHAR(50)")
	private String username;

	@NotNull(message="Email must not be null")
	@NotBlank(message="Email must not be blank")
	@Size(min=3, max=50, message="Email must be between 3 and 50 characters")
	@Column(columnDefinition="VARCHAR(50)")
	private String email;

	@NotNull(message="Password must not be null")
	@NotBlank(message="Password must not be blank")
	@Size(min=3, max=255, message="Password must be between 3 and 255 characters")
	@Column(columnDefinition="VARCHAR(255)")
	private String password;

	private String avatar;
	private boolean active;

	@OneToOne
	private Player player;
}
