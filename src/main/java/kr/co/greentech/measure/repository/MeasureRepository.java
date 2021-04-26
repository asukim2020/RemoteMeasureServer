package kr.co.greentech.measure.repository;

import kr.co.greentech.measure.domain.Measure;
import kr.co.greentech.measure.domain.MeasureItem;
import kr.co.greentech.measure.domain.MeasureStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MeasureRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Measure measure) {
        em.persist(measure);
    }

    public Measure addMeasureItems(
            Long id,
            List<MeasureItem> items
    ) {
        Measure measure = findOne(id);
        for(MeasureItem item: items) {
            item.setMeasure(measure);
            em.persist(item);
        }

        return measure;
    }

    public Measure updateStatus(Long id, String status) {
        Measure measure = findOne(id);
        if (status.equals("end")) {
            measure.setStatus(MeasureStatus.END);
        } else if (status.equals("ing")) {
            measure.setStatus(MeasureStatus.ING);
        }

        return measure;
    }

    public Measure findOne(Long id) {
        return em.find(Measure.class, id);
    }

    public List<Measure> findByName(String name) {
        return em
                .createQuery(
                        "select m " +
                                "from Measure m " +
                                "where m.name = :name",
                        Measure.class
                ).setParameter("name", name)
                .getResultList();
    }

    public List<Measure> findAll() {
        return em
                .createQuery(
                        "select m from Measure m",
                        Measure.class
                )
                .getResultList();
    }
}
