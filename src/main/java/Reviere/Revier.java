package Reviere;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Revier  extends PanacheEntity
{
	@NotEmpty
	@Column(length = 50)
	public String name;

	@NotEmpty
	@Column(length = 50)
	@ElementCollection
	public List<String> ansprechpartner = new ArrayList<String>();
}
