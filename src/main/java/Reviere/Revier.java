package Reviere;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Revier  extends PanacheEntityBase
{
	@Id
	@GeneratedValue(generator = "UUID")
	public UUID id;

	@NotEmpty
	@Column(length = 50)
	public String name;

	@NotEmpty
	@Column(length = 50)
	@ElementCollection
	public List<String> ansprechpartner = new ArrayList<String>();
}
