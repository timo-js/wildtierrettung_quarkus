package Flugmissionen;

import Wild.Gelege;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

import Piloten.Pilot;
import Reviere.Revier;
import jakarta.validation.constraints.NotNull;

@Entity
public class Flugmission extends PanacheEntity
{
	@NotNull
	@Temporal(TemporalType.DATE)
	public LocalDate datum;

	@NotEmpty
	@ManyToMany
	public List<Pilot> piloten;

	public Integer anzahlKitze;

	public Integer anzahlHasen;

	@Column(length = 100)
	public String sonstigeWildtiere;

	public String kommentar;

	public Integer abgesuchteHektar;

	@Embedded
	public Koordinaten koordinaten;

	@ElementCollection
	@CollectionTable(name = "flugmission_gelege", joinColumns = @JoinColumn(name = "flugmission_id"))
	public List<Gelege> gelegeListe;

	@ManyToOne
	@JoinColumn(name = "revier_id")
	private Revier revier;

	// für Hibernate
	public Revier getRevier() {
		return revier;
	}

	// für Hibernate
	public void setRevier(Revier revier) {
		this.revier = revier;
	}
}
