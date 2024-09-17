package Revier;

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

@Path("/api/reviere")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class RevierResource
{
	@Inject
	UriInfo uriInfo;

	@GET
	public Response holeReviere() {
		List<Revier> reviere = Revier.listAll();

		if(reviere.isEmpty())
			return Response.noContent().build();

		return Response.ok(reviere).build();
	}

	@POST
	@Transactional(REQUIRED)
	public Response legeRevierAn(Revier revier) {
		revier.persist();
		if (revier.isPersistent()) {
			URI erstelltesRevier = uriInfo.getAbsolutePathBuilder().path(Long.toString(revier.id)).build();
			return Response.created(erstelltesRevier).entity(revier).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}


	@PUT
	@Path("/{id}")
	@Transactional(REQUIRED)
	public Response aktualisiereRevier(@PathParam("id") @Min(1) Long id, Revier revier) {
		Revier existierendesRevier = Revier.findById(id);
		if (existierendesRevier != null) {
			existierendesRevier.name = revier.name;
			existierendesRevier.ansprechpartner = revier.ansprechpartner;
			return Response.ok(existierendesRevier).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response loescheRevier(@PathParam("id") @Min(1) Long id) {
		boolean isDeleted = Revier.deleteById(id);
		if(isDeleted) {
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
