package Piloten;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/piloten")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PilotResource
{
	@Inject
	UriInfo uriInfo;

	@GET
	public Response holePiloten() {
		List<Pilot> piloten = Pilot.listAll();

		if(piloten.isEmpty())
			return Response.noContent().build();

		return Response.ok(piloten).build();
	}

	@GET
	@Path("/{id}")
	public Response holePilot(@PathParam("id") UUID id) {
		Pilot pilot = Pilot.findById(id);

		if(pilot == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		return Response.ok(pilot).build();
	}

	@POST
	@Transactional(REQUIRED)
	public Response legePilotAn(@Valid Pilot pilot){
		pilot.persist();
		if(pilot.isPersistent()) {
			URI erstellterPilot = uriInfo.getAbsolutePathBuilder().path(String.valueOf(pilot.id)).build();
			return Response.created(erstellterPilot).entity(pilot).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response loeschePilot(@PathParam("id") UUID id) {
		boolean isDeleted = Pilot.deleteById(id);
		if(isDeleted) {
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
