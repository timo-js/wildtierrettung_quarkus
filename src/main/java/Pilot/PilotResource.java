package Pilot;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

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
	public Response holePilot(@PathParam("id") @Min(1) Long id) {
		Pilot pilot = Pilot.findById(id);

		if(pilot == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		return Response.ok(pilot).build();
	}

	@POST
	@Transactional(REQUIRED)
	public Response legePilotAn(Pilot pilot){
		pilot.persist();
		if(pilot.isPersistent()) {
			URI erstellterPilot = uriInfo.getAbsolutePathBuilder().path(Long.toString(pilot.id)).build();
			return Response.created(erstellterPilot).entity(pilot).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Response aktualisierePilot(@PathParam("id") @Min(1) Long id, Pilot aktualisierterPilot) {
		Pilot existierenderPilot = Pilot.findById(id);
		if (existierenderPilot != null) {
			existierenderPilot.vorname = aktualisierterPilot.vorname;
			existierenderPilot.nachname = aktualisierterPilot.nachname;
			return Response.ok(existierenderPilot).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response loeschePilot(@PathParam("id") @Min(1) Long id) {
		boolean isDeleted = Pilot.deleteById(id);
		if(isDeleted) {
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
