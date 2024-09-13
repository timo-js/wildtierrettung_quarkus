import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Pilot
{
	@Id @GeneratedValue
	private Long id;

	@NotEmpty
	@Column(length = 50)
	private String vorname;

	@NotEmpty
	@Column(length = 50)
	private String nachname;

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

	public String getVorname()
	{
		return vorname;
	}

	public String getNachname()
	{
		return nachname;
	}

	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}

	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}
}
