package Flugmissionen;

import Piloten.Pilot;
import Reviere.Revier;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.REQUIRED;

@Path("/api/flugmissionen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlugmissionResource
{
	@Inject
	UriInfo uriInfo;

	@GET
	@RolesAllowed({"admin", "pilot", "zuschauer"})
	public Response holeFlugmissionen(
		@QueryParam("sortierenach") @DefaultValue("datum") String sortiereNach,
		@QueryParam("sortierreihenfolge") @DefaultValue("asc") String sortierReihenfolge) {
		Sort sortierung = erzeugeSortierung(sortiereNach, Arrays.asList("datum", "revier"), sortierReihenfolge);

		List<Flugmission> flugmissionen = Flugmission.listAll(sortierung);

		if(flugmissionen.isEmpty())
			return Response.noContent().build();

		return Response.ok(flugmissionen).build();
	}

	@POST
	@Transactional(REQUIRED)
	@RolesAllowed({"admin", "pilot"})
	public Response legeFlugmissionAn(@Valid Flugmission flugmission) {
		for(Pilot pilot : flugmission.piloten) {
			if (pilot.id == null) {
				return Response.status(Response.Status.BAD_REQUEST)
					.entity("Pilot darf nicht leer sein.")
					.build();
			}
			if (Pilot.findById(pilot.id) == null) {
				return Response.status(Response.Status.NOT_FOUND)
					.entity("Pilot mit ID existiert nicht: " + pilot.id)
					.build();
			}
		}

		if (flugmission.getRevier() == null) {
			return Response.status(Response.Status.BAD_REQUEST)
				.entity("Revier darf nicht leer sein.")
				.build();
		}
		if (Revier.findById(flugmission.getRevier().id) == null) {
			return Response.status(Response.Status.NOT_FOUND)
				.entity("Revier mit ID existiert nicht: " + flugmission.getRevier().id)
				.build();
		}

		flugmission.persistAndFlush();

		if(flugmission.isPersistent()) {
			URI erstellteFlugmission = uriInfo.getAbsolutePathBuilder().path(String.valueOf(flugmission.id)).build();
			return Response.created(erstellteFlugmission).entity(flugmission).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@RolesAllowed({"admin", "pilot"})
	public Response loescheFlugmission(@PathParam("id") UUID id) {
		boolean isDeleted = Flugmission.deleteById(id);
		if(isDeleted) {
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
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
}
