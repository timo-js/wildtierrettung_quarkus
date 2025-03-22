package Reviere;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/reviere")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class RevierResource
{
	@Inject
	UriInfo uriInfo;

	@GET
	@RolesAllowed({"admin", "pilot", "zuschauer"})
	public Response holeReviere() {
		List<Revier> reviere = Revier.listAll();

		if(reviere.isEmpty())
			return Response.noContent().build();

		return Response.ok(reviere).build();
	}

	@POST
	@RolesAllowed({"admin"})
	@Transactional(REQUIRED)
	public Response legeRevierAn(@Valid Revier revier) {
		revier.persist();
		if (revier.isPersistent()) {
			URI erstelltesRevier = uriInfo.getAbsolutePathBuilder().path(String.valueOf(revier.id)).build();
			return Response.created(erstelltesRevier).entity(revier).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@RolesAllowed({"admin"})
	@Path("/{id}")
	@Transactional
	public Response loescheRevier(@PathParam("id") UUID id) {
		boolean isDeleted = Revier.deleteById(id);
		if(isDeleted) {
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
