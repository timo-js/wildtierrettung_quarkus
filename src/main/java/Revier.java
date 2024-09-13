import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Revier
{
	@Id @GeneratedValue
	private Long id;

	@NotEmpty
	@Column(length = 50)
	private String name;

	@NotEmpty
	@Column(length = 50)
	@ElementCollection
	private List<String> ansprechpartner = new ArrayList<String>();

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public @NotEmpty String getName()
	{
		return name;
	}

	public void setName(@NotEmpty String name)
	{
		this.name = name;
	}

	public @NotEmpty List<String> getAnsprechpartner()
	{
		return ansprechpartner;
	}

	public void setAnsprechpartner(@NotEmpty List<String> ansprechpartner)
	{
		this.ansprechpartner = ansprechpartner;
	}
}
