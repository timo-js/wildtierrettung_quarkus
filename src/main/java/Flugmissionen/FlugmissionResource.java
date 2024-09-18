package Flugmissionen;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

import static jakarta.transaction.Transactional.TxType.REQUIRED;

@Path("/api/flugmissionen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlugmissionResource
{
	@Inject
	UriInfo uriInfo;
	@GET
	public Response holeFlugmissionen() {
		List<Flugmission> flugmissionen = Flugmission.listAll();

		if(flugmissionen.isEmpty())
			return Response.noContent().build();

		return Response.ok(flugmissionen).build();
	}

	@POST
	@Transactional(REQUIRED)
	public Response legeFlugmissionAn(Flugmission flugmission) {
		flugmission.persist();

		if(flugmission.isPersistent()) {
			URI erstellteFlugmission = uriInfo.getAbsolutePathBuilder().path(Long.toString(flugmission.id)).build();
			return Response.created(erstellteFlugmission).entity(flugmission).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
