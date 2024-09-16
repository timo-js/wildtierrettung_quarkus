package Pilot;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
public class PilotService
{
	@Inject
	EntityManager em;

	@Transactional(SUPPORTS)
	public Pilot finde(Long id) {
		return em.find(Pilot.class, id);
	}

	@Transactional(SUPPORTS)
	public List<Pilot> findeAlle() {
		TypedQuery<Pilot> query = em.createQuery(
			"SELECT p FROM Pilot.Pilot p ORDER BY p.nachname ASC", Pilot.class
		);
		return query.getResultList();
	}

	@Transactional(REQUIRED)
	public Pilot legeAn(Pilot pilot) {
		em.persist(pilot);
		return pilot;
	}

	@Transactional(REQUIRED)
	public Pilot aktualisiere(Long id) {
		Pilot pilot = em.getReference(Pilot.class, id);
		em.persist(pilot);
		return pilot;
	}

	@Transactional(REQUIRED)
	public void loesche(Long id) {
		em.remove(em.getReference(Pilot.class, id));
	}


}
