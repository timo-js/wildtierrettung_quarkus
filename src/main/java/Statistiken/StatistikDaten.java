package Statistiken;

import Wild.GelegeStatistikNachWildart;

import java.util.List;

public class StatistikDaten
{
	private Integer anzahlKitze;
	private Integer anzahlHasen;
	private Integer anzahlGelege;
	private Double abgesuchteHektar;
	private List<GelegeStatistikNachWildart> gelegeStatistikNachWildart;
	private Integer anzahlFlugmissionen;
	private Integer anzahlTageMitFluegen;

	public StatistikDaten()
	{
	}

	public StatistikDaten(Integer anzahlKitze, Integer anzahlHasen, Integer anzahlGelege, Double abgesuchteHektar,
		List<GelegeStatistikNachWildart> statistischeGelegeMap, Integer anzahlFlugmissionen,
		Integer anzahlTageMitFluegen)
	{
		this.anzahlKitze = anzahlKitze;
		this.anzahlHasen = anzahlHasen;
		this.anzahlGelege = anzahlGelege;
		this.abgesuchteHektar = abgesuchteHektar;
		this.gelegeStatistikNachWildart = statistischeGelegeMap;
		this.anzahlFlugmissionen = anzahlFlugmissionen;
		this.anzahlTageMitFluegen = anzahlTageMitFluegen;
	}

	public Integer getAnzahlGelege()
	{
		return anzahlGelege;
	}

	public void setAnzahlGelege(Integer anzahlGelege)
	{
		this.anzahlGelege = anzahlGelege;
	}

	public Integer getAnzahlKitze()
	{
		return anzahlKitze;
	}

	public void setAnzahlKitze(Integer anzahlKitze)
	{
		this.anzahlKitze = anzahlKitze;
	}

	public Integer getAnzahlHasen()
	{
		return anzahlHasen;
	}

	public void setAnzahlHasen(Integer anzahlHasen)
	{
		this.anzahlHasen = anzahlHasen;
	}

	public Double getAbgesuchteHektar()
	{
		return abgesuchteHektar;
	}

	public void setAbgesuchteHektar(Double abgesuchteHektar)
	{
		this.abgesuchteHektar = abgesuchteHektar;
	}

	public List<GelegeStatistikNachWildart> getGelegeStatistikNachWildart()
	{
		return gelegeStatistikNachWildart;
	}

	public void setGelegeStatistikNachWildart(List<GelegeStatistikNachWildart> gelegeStatistikNachWildart)
	{
		this.gelegeStatistikNachWildart = gelegeStatistikNachWildart;
	}

	public Integer getAnzahlFlugmissionen()
	{
		return anzahlFlugmissionen;
	}

	public void setAnzahlFlugmissionen(Integer anzahlFlugmissionen)
	{
		this.anzahlFlugmissionen = anzahlFlugmissionen;
	}

	public Integer getAnzahlTageMitFluegen()
	{
		return anzahlTageMitFluegen;
	}

	public void setAnzahlTageMitFluegen(Integer anzahlTageMitFluegen)
	{
		this.anzahlTageMitFluegen = anzahlTageMitFluegen;
	}
}
