package kr.co.greentech.measure.service;

import kr.co.greentech.measure.domain.Measure;
import kr.co.greentech.measure.domain.MeasureItem;
import kr.co.greentech.measure.repository.MeasureItemRepository;
import kr.co.greentech.measure.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MeasureService {

    @Autowired MeasureItemRepository measureItemRepository;
    @Autowired MeasureRepository measureRepository;


    public Measure measureStart(String name, String mode) {
        Measure measure = Measure.createMeasure(name, mode);
        measureRepository.save(measure);
        return measure;
    }

    public Measure addMeasureItems(
            Long id,
            List<MeasureItem> items
    ) {

        return measureRepository.addMeasureItems(id, items);
    }

    public Measure findOne(Long id) {
        return measureRepository.findOne(id);
    }

    public List<MeasureItem> findByMeasureId(Long id) {
        return measureItemRepository.findByMeasureItems(id);
    }

    public List<MeasureItem> findByCountUpMeasureItems(Long id, Integer count) {
        return measureItemRepository.findByCountUpMeasureItems(id, count);
    }

    public List<Measure> findByName(String name) {
        return measureRepository.findByName(name);
    }

    // TODO:- findAfter 만들기 - count 를 인자로 받아 이 후 리스트를 가져오는 로직

    public List<Measure> findAll() {
        return measureRepository.findAll();
    }
}
