package Pilot;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/piloten")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PilotResource
{
	@Inject
	PilotService service;

	@Inject
	UriInfo uriInfo;

	@GET
	public Response holePiloten() {
		List<Pilot> piloten = service.findeAlle();

		if(piloten.isEmpty())
			return Response.noContent().build();

		return Response.ok(piloten).build();
	}

	@GET
	@Path("/{id}")
	public Response holePilot(@PathParam("id") @Min(1) Long id) {
		Pilot pilot = service.finde(id);

		if(pilot == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		return Response.ok(pilot).build();
	}

	@POST
	public Response legePilotAn(Pilot pilot){
		pilot = service.legeAn(pilot);
		URI erstellteURI = uriInfo.getAbsolutePathBuilder().path(Long.toString(pilot.getId())).build();
		return Response.created(erstellteURI).entity(pilot).build();
	}

	@PUT
	@Path("/{id}")
	public Response aktualisierePilot(@PathParam("id") @Min(1) Long id) {
		Pilot pilot = service.aktualisiere(id);
		return Response.ok(pilot).build();
	}

	@DELETE
	@Path("/{id}")
	public Response loeschePilot(@PathParam("id") @Min(1) Long id) {
		service.loesche(id);
		return Response.noContent().build();
	}
}
