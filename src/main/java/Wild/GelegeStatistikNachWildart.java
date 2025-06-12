package Wild;

public class GelegeStatistikNachWildart
{
	private Wildart wildart;
	private int anzahlGelege;
	private int anzahlEier;
	private int anzahlKuecken;

	public GelegeStatistikNachWildart(Wildart wildart)
	{
		this.wildart = wildart;
	}

	public void addGelege(Gelege gelege)
	{
		anzahlGelege++;
		if (gelege.getAnzahlEier() != null)
		{
			anzahlEier += gelege.getAnzahlEier();
		}
		if (gelege.getAnzahlKuecken() != null)
		{
			anzahlKuecken += gelege.getAnzahlKuecken();
		}
	}

	public Wildart getWildart()
	{
		return wildart;
	}

	public int getAnzahlGelege()
	{
		return anzahlGelege;
	}

	public int getAnzahlEier()
	{
		return anzahlEier;
	}

	public int getAnzahlKuecken()
	{
		return anzahlKuecken;
	}
}
