package kr.co.greentech.measure.repository;

import kr.co.greentech.measure.domain.Measure;
import kr.co.greentech.measure.domain.SensorItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class SensorItemRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Measure setSensorSetting(
            Long id,
            List<SensorItem> items
    ) {
        Measure measure = findOne(id);
        List<SensorItem> prevSensorItems = getSensorItems(id);

        for(SensorItem item: items) {
            Optional<SensorItem> sensorItem = prevSensorItems.stream().filter(pitem -> pitem.getNumber().equals(item.getNumber())).findFirst();
            if (sensorItem.isPresent()) {
                // not null -> update
                SensorItem i = sensorItem.get();
                i.setOn(item.isOn());
                i.setType(item.getType());
                i.setAmpGain(item.getAmpGain());
                i.setApplyV(item.getApplyV());
                i.setFilter(item.getFilter());
            } else {
                // null -> insert
                item.setMeasure(measure);
                em.persist(item);
            }
        }

        return measure;
    }

    public Measure findOne(Long id) {
        return em.find(Measure.class, id);
    }

    public List<SensorItem> getSensorItems(
            Long id
    ) {
        return em
                .createQuery(
                        "select distinct s " +
                                "from SensorItem s " +
                                "inner join s.measure m " +
                                "where m.id = :id " +
                                "order by s.number",
                        SensorItem.class
                ).setParameter("id", id)
                .getResultList();
    }
}
