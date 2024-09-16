package Revier;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;

import java.util.List;
import java.util.Optional;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
public class RevierService
{
	@Inject
	EntityManager em;

	@Transactional(SUPPORTS)
	public List<Revier> findeAlle() {
		TypedQuery<Revier> query = em.createQuery(
			"SELECT r FROM Revier.Revier r ORDER BY r.name ASC", Revier.class
		);
		return query.getResultList();
	}

	@Transactional(REQUIRED)
	public Optional<Revier> aktualisiere(Long id, Revier updatedRevier) {
		Revier revier = em.getReference(Revier.class, id);
		if (revier != null) {
			revier.setName(updatedRevier.getName());
			revier.setAnsprechpartner(updatedRevier.getAnsprechpartner());
			return Optional.of(revier);
		}
		return Optional.empty();
	}

	@Transactional(REQUIRED)
	public Revier legeAn(Revier revier) {
		em.persist(revier);
		return revier;
	}

	@Transactional(REQUIRED)
	public void loesche(@Min(1) Long id) {
		em.remove(em.getReference(Revier.class, id));
	}
}
