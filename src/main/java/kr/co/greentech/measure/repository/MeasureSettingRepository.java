package kr.co.greentech.measure.repository;

import kr.co.greentech.measure.domain.MeasureSetting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class MeasureSettingRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(MeasureSetting measureSetting) {
        measureSetting.setTime((new Date()).getTime());
        em.persist(measureSetting);
    }

    public MeasureSetting update(MeasureSetting measureSetting) {
        List<MeasureSetting> items = findAll();

        if (items.isEmpty()) return null;

        MeasureSetting item = items.stream().findFirst().get();

        item.setAccel(measureSetting.getAccel());
        item.setSlope(measureSetting.getSlope());
        item.setTriggerLevel(measureSetting.getTriggerLevel());
        item.setStandardTime(measureSetting.getStandardTime());
        item.setRequest(measureSetting.getRequest());

        item.setTime((new Date()).getTime());

        return item;
    }


    public List<MeasureSetting> findAll() {
        return em
                .createQuery(
                        "select ms from MeasureSetting ms",
                        MeasureSetting.class
                )
                .getResultList();
    }
}
