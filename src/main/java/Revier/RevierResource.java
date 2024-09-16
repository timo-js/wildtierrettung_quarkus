package Revier;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/reviere")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class RevierResource
{
	@Inject
	RevierService service;

	@Inject
	UriInfo uriInfo;

	@GET
	public Response holeReviere() {
		List<Revier> reviere = service.findeAlle();

		if(reviere.isEmpty())
			return Response.noContent().build();

		return Response.ok(reviere).build();
	}

	@POST
	public Response legeRevierAn(Revier revier) {
		revier = service.legeAn(revier);
		URI erstelltesRevier = uriInfo.getAbsolutePathBuilder().path(Long.toString(revier.getId())).build();
		return Response.created(erstelltesRevier).entity(revier).build();
	}


	@PUT
	@Path("/{id}")
	public Response aktualisiereRevier(@PathParam("id") @Min(1) Long id, Revier revier) {
		Optional<Revier> updatedRevier = service.aktualisiere(id, revier);
		return updatedRevier.map(r -> Response.ok(r).build())
			.orElse(Response.status(Response.Status.NOT_FOUND).build());
	}

	@DELETE
	@Path("/{id}")
	public Response loescheRevier(@PathParam("id") @Min(1) Long id) {
		service.loesche(id);
		return Response.noContent().build();
	}
}
