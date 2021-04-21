package kr.co.greentech.measure.repository;

import kr.co.greentech.measure.domain.MeasureItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MeasureItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(MeasureItem data) {
        em.persist(data);
    }

    public List<MeasureItem> findAll() {
        return em
                .createQuery(
                        "select mi from MeasureItem mi",
                        MeasureItem.class
                )
                .getResultList();
    }

    public List<MeasureItem> findByMeasureItems(Long id) {
        return em
                .createQuery(
                        "select i " +
                                "from MeasureItem i " +
                                "inner join i.measure m " +
                                "where m.id = :id",
                        MeasureItem.class
                ).setParameter("id", id)
                .getResultList();
    }

    public List<MeasureItem> findByCountUpMeasureItems(Long id, Integer count) {
        return em
                .createQuery(
                        "select distinct i " +
                                "from MeasureItem i " +
                                "inner join i.measure m " +
                                "where m.id = :id and i.count >= :count",
                        MeasureItem.class
                ).setParameter("count", count)
                .setParameter("id", id)
                .getResultList();
    }

}
