package Wild;

public enum Wildart
{
	FASAN("Fasan"),
	REBHUHN("Rebhuhn"),
	ENTE("Ente"),
	SCHNEPFE("Schnepfe"),
	KIEBITZ("Kiebitz");

	private final String bezeichnung;

	Wildart(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}
}
