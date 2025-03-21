package Piloten;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@Entity
public class Pilot extends PanacheEntityBase
{
	@Id
	@GeneratedValue(generator = "UUID")
	public UUID id;

	@NotEmpty
	@Column(length = 50)
	public String vorname;

	@NotEmpty
	@Column(length = 50)
	public String nachname;
}
