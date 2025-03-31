package security;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/api/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource
{

	@GET
	@RolesAllowed({"admin", "pilot", "zuschauer"})
	public Response login(@Context SecurityContext securityContext) {
		Principal userPrincipal = securityContext.getUserPrincipal();
		if (userPrincipal == null) {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
		}

		String roles = Set.of("admin", "pilot", "zuschauer").stream()
			.filter(securityContext::isUserInRole)
			.collect(Collectors.joining(","));

		return Response.ok(new AuthResponse(userPrincipal.getName(), roles)).build();
	}

	public static class AuthResponse {
		public String username;
		public String roles;

		public AuthResponse(String username, String roles) {
			this.username = username;
			this.roles = roles;
		}
	}
}
