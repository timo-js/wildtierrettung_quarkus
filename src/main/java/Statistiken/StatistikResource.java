package Statistiken;

import Flugmissionen.Flugmission;
import Wild.Gelege;
import Wild.GelegeStatistikNachWildart;
import Wild.Wildart;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.*;
import java.util.stream.Collectors;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/statistiken")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class StatistikResource
{
	@GET
	@RolesAllowed({ "admin", "pilot", "zuschauer" })
	public Response holeStatistikDaten(
		@QueryParam("jahr") Integer jahr,
		@QueryParam("revierId") UUID revierId
	)
	{
		List<Flugmission> missionen;

		if (revierId != null && jahr != null)
		{
			missionen = Flugmission.list("revier.id = ?1 and YEAR(datum) = ?2", revierId, jahr);
		}
		else
			if (revierId != null)
			{
				missionen = Flugmission.list("revier.id = ?1", revierId);
			}
			else
				if (jahr != null)
				{
					missionen = Flugmission.list("YEAR(datum) = ?1", jahr);
				}
				else
				{
					missionen = Flugmission.listAll();
				}

		List<Gelege> alleGelege = sammelAlleGelegeAusDatensatz(missionen);

		// Hier werden alle für die Statistik benötigten Daten aggregiert
		Integer anzahlKitze = missionen.stream().mapToInt(fm -> fm.anzahlKitze != null ? fm.anzahlKitze : 0).sum();
		Integer anzahlHasen = missionen.stream().mapToInt(fm -> fm.anzahlHasen != null ? fm.anzahlHasen : 0).sum();
		Integer anzahlGelege = alleGelege.size();
		Double abgesuchteHektar =
			missionen.stream().mapToDouble(fm -> fm.abgesuchteHektar != null ? fm.abgesuchteHektar : 0).sum();
		Integer anzahlFlugmissionen = missionen.size();
		Integer anzahlTageMitFluegen =
			missionen.stream().map(fm -> fm.datum).filter(Objects::nonNull).collect(Collectors.toSet()).size();
		List<GelegeStatistikNachWildart> gelegeStatistikNachWildartList = aggregiereGelegeNachWildart(alleGelege);

		StatistikDaten statistikDaten = new StatistikDaten();
		statistikDaten.setAnzahlKitze(anzahlKitze);
		statistikDaten.setAnzahlHasen(anzahlHasen);
		statistikDaten.setAnzahlGelege(anzahlGelege);
		statistikDaten.setAbgesuchteHektar(abgesuchteHektar);
		statistikDaten.setAnzahlFlugmissionen(anzahlFlugmissionen);
		statistikDaten.setAnzahlTageMitFluegen(anzahlTageMitFluegen);
		statistikDaten.setGelegeStatistikNachWildart(gelegeStatistikNachWildartList);

		return Response.ok(statistikDaten).build();
	}

	private List<Gelege> sammelAlleGelegeAusDatensatz(List<Flugmission> missionen)
	{
		return missionen.stream()
			.filter(fm -> fm.gelegeListe != null)
			.flatMap(fm -> fm.gelegeListe.stream())
			.toList();
	}

	private List<GelegeStatistikNachWildart> aggregiereGelegeNachWildart(List<Gelege> gelegeListe)
	{
		Map<Wildart, GelegeStatistikNachWildart> aggregatMap = new HashMap<>();

		for (Gelege gelege : gelegeListe)
		{
			if (gelege.getWildart() == null)
				continue;

			aggregatMap
				.computeIfAbsent(gelege.getWildart(), GelegeStatistikNachWildart::new)
				.addGelege(gelege);
		}

		return new ArrayList<>(aggregatMap.values());
	}

}
