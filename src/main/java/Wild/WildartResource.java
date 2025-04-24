package Wild;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/wildarten")
@Produces(APPLICATION_JSON)
public class WildartResource
{
	@GET
	@RolesAllowed({"admin", "pilot", "zuschauer"})
	public Response holeWildarten() {
		List<String> alleWildarten = Stream.of(Wildart.values())
			.map(Enum::name)
			.collect(Collectors.toList());

		return Response.ok(alleWildarten).build();
	}
}
