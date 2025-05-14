package Flugmissionen;

import Piloten.Pilot;
import Reviere.Revier;
import Wild.Gelege;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Flugmission extends PanacheEntityBase
{
	@Id
	@GeneratedValue(generator = "UUID")
	public UUID id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonbDateFormat("dd.MM.yyyy")
	public LocalDate datum;

	@NotEmpty
	@ManyToMany
	public List<Pilot> piloten;

	@ManyToOne
	@JoinColumn(name = "revier_id")
	@NotNull
	private Revier revier;

	public Integer anzahlKitze;

	public Integer anzahlHasen;

	@ElementCollection
	@CollectionTable(name = "flugmission_gelege", joinColumns = @JoinColumn(name = "flugmission_id"))
	@Valid
	public List<Gelege> gelegeListe;

	@Column(length = 100)
	public String sonstigeWildtiere;

	public String kommentar;

	public Double abgesuchteHektar;

	@Embedded
	public Koordinaten koordinaten;

	// Getter und Setter für Hibernate
	public Revier getRevier()
	{
		return revier;
	}

	public void setRevier(Revier revier)
	{
		this.revier = revier;
	}
}
