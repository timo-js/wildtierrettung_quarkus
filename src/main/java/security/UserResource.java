package security;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/users")
@ApplicationScoped
public class UserResource {

	@GET
	@RolesAllowed({"admin"})
	public Response holeAlleUser(){
		List<User> users = User.listAll();

		if(users.isEmpty())
			return Response.noContent().build();

		List<UserDTO> userDTOS = users.stream().map(this::mappeZuDto).toList();
		return Response.ok(userDTOS).build();
	}

	@Path("/new")
	@POST
	@RolesAllowed({"admin"})
	@Transactional
	public Response userAnlegen(@Valid User newUser) {
		if (User.findByUsername(newUser.username) != null) {
			return Response.status(Response.Status.CONFLICT)
				.entity("Nutzer mit dem angegebenen Nutzernamen existiert bereits.")
				.build();
		}

		newUser.password = BcryptUtil.bcryptHash(newUser.password);

		newUser.persist();
		if (newUser.isPersistent()) {
			return Response.status(Response.Status.OK).entity(mappeZuDto(newUser)).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	private UserDTO mappeZuDto(User user) {
		return new UserDTO(user.id, user.username, user.roles);
	}
}
