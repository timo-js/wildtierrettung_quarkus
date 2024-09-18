package Wild;

import jakarta.persistence.Embeddable;

@Embeddable
public class Gelege
{
//	@Transient
	private Wildart wildart;

	private Integer anzahlKuecken;
	private Integer anzahlEier;

	public Gelege() {}

	public Gelege(Wildart wildart, Integer anzahlKuecken, Integer anzahlEier) {
		this.wildart = wildart;
		this.anzahlKuecken = anzahlKuecken;
		this.anzahlEier = anzahlEier;
	}

//	@Transient
	public Wildart getWildart() {
		return wildart;
	}

	public void setWildart(Wildart wildart) {
		this.wildart = wildart;
	}

	public Integer getAnzahlKuecken() {
		return anzahlKuecken;
	}

	public void setAnzahlKuecken(Integer anzahlKuecken) {
		this.anzahlKuecken = anzahlKuecken;
	}

	public Integer getAnzahlEier() {
		return anzahlEier;
	}

	public void setAnzahlEier(Integer anzahlEier) {
		this.anzahlEier = anzahlEier;
	}
}
