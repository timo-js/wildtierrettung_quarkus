package security;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@UserDefinition
public class User extends PanacheEntityBase
{
	@Id
	@GeneratedValue(generator = "UUID")
	public UUID id;

	@Username
	@NotEmpty(message = "Nutzername darf nicht leer sein.")
	public String username;

//	@NotEmpty(message = "Passwort darf nicht leer sein.")
//	@Length(min = 8, message = "Passwort muss mindestens 8 Stellen lang sein.")
	@Password
	public String password;

	@Roles
	public String roles;

	public static User findByUsername(String username) {
		return find("username", username).firstResult();
	}
}



