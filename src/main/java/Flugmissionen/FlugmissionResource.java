package Flugmissionen;

import Reviere.Revier;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.apache.commons.beanutils.BeanUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
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
	public Response holeFlugmissionen(
		@QueryParam("sortierenach") @DefaultValue("datum") String sortiereNach,
		@QueryParam("sortierreihenfolge") @DefaultValue("asc") String sortierReihenfolge) {
		Sort sortierung = erzeugeSortierung(sortiereNach, Arrays.asList("datum", "revier"), sortierReihenfolge);

		List<Flugmission> flugmissionen = Flugmission.listAll(sortierung);

		if(flugmissionen.isEmpty())
			return Response.noContent().build();

		return Response.ok(flugmissionen).build();
	}

	private Sort erzeugeSortierung(String sortiereNach, List<String> moeglicheSortierOptionen, String sortierReihenfolge) {
		Sort sortierung = Sort.empty();
		sortiereNach = sortiereNach.toLowerCase();

		if (moeglicheSortierOptionen.contains(sortiereNach)) {
			sortierung = Sort.by(sortiereNach);
		}
		sortierung.direction(sortierReihenfolge.equals("desc") ? Sort.Direction.Descending : Sort.Direction.Ascending);

		return sortierung;
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
