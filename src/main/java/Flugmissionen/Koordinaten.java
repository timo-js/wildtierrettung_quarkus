package Flugmissionen;

import jakarta.persistence.Embeddable;

@Embeddable
public class Koordinaten
{
	public Double latitude;
	public Double longitude;
	public Koordinaten() {}

	public Koordinaten(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
