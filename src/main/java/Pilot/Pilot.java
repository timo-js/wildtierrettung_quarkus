package Pilot;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Pilot extends PanacheEntity
{
	@NotEmpty
	@Column(length = 50)
	public String vorname;

	@NotEmpty
	@Column(length = 50)
	public String nachname;
}
