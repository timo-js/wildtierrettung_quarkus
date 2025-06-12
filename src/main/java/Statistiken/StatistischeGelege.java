package Statistiken;

import Wild.Wildart;

public class StatistischeGelege
{
	private Wildart wildart;
	private Integer anzahlGelege;
	private Integer anzahlEier;
	private Integer anzahlKuecken;

	public StatistischeGelege(Wildart wildart, Integer anzahlGelege, Integer anzahlEier, Integer anzahlKuecken)
	{
		this.wildart = wildart;
		this.anzahlGelege = anzahlGelege;
		this.anzahlEier = anzahlEier;
		this.anzahlKuecken = anzahlKuecken;
	}

	public Wildart getWildart()
	{
		return wildart;
	}

	public Integer getAnzahlGelege()
	{
		return anzahlGelege;
	}

	public Integer getAnzahlEier()
	{
		return anzahlEier;
	}

	public Integer getAnzahlKuecken()
	{
		return anzahlKuecken;
	}
}
