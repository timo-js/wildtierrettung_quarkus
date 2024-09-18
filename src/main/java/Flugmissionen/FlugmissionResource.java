package Flugmissionen;

import Reviere.Revier;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.apache.commons.beanutils.BeanUtils;

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

	@PUT
	@Path("/{id}")
	@Transactional(REQUIRED)
	public Response aktualisiereFlugmission(@PathParam("id") @Min(1) Long id, Flugmission flugmission) {
		Flugmission existierendeFlugmission = Flugmission.findById(id);

		if(existierendeFlugmission == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		try {
			BeanUtils.copyProperties(existierendeFlugmission, flugmission);
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Fehler beim aktualisieren der Flugmission").build();
		}

		return Response.ok(existierendeFlugmission).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response loescheFlugmission(@PathParam("id") @Min(1) Long id) {
		boolean isDeleted = Flugmission.deleteById(id);
		if(isDeleted) {
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
