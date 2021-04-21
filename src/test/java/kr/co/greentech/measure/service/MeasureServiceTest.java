package kr.co.greentech.measure.service;

import kr.co.greentech.measure.domain.Measure;
import kr.co.greentech.measure.repository.MeasureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
class MeasureServiceTest {

    @PersistenceContext
    private EntityManager em;

    MeasureService measureService = new MeasureService();
    MeasureRepository measureRepository = new MeasureRepository();

    @Test
    void measureStart() {
        Measure measure = Measure.createMeasure("asu", "step");
        em.persist(measure);
    }
}