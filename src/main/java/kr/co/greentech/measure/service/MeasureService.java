package kr.co.greentech.measure.service;

import kr.co.greentech.measure.domain.Company;
import kr.co.greentech.measure.domain.Measure;
import kr.co.greentech.measure.domain.MeasureItem;
import kr.co.greentech.measure.domain.SensorItem;
import kr.co.greentech.measure.repository.CompanyRepository;
import kr.co.greentech.measure.repository.MeasureItemRepository;
import kr.co.greentech.measure.repository.MeasureRepository;
import kr.co.greentech.measure.repository.SensorItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeasureService {

    @Autowired CompanyRepository companyRepository;
    @Autowired MeasureRepository measureRepository;
    @Autowired MeasureItemRepository measureItemRepository;
    @Autowired SensorItemRepository sensorItemRepository;


    public Company joinCompany(
            String name,
            String ip
    ) {
        Company item = companyRepository.findOne(name);
        if (item != null) {
            return companyRepository.updateIp(name, ip);
        } else {
            Company company = Company.create(name, ip);
            companyRepository.save(company);
            return company;
        }
    }


    public Company findCompany(Long id) {
        return companyRepository.findOne(id);
    }


    public Company findCompany(String name) {
        return companyRepository.findOne(name);
    }


    public Measure measureStart(
            Long id,
            String name
    ) {
        Company company = companyRepository.findOne(id);
        Measure measure = Measure.create(name);
        measure.setCompany(company);
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

    public List<MeasureItem> findByTimeMeasureItems(
            Long id,
            Long startTime,
            Long endTime,
            Long afterId
    ) {
        return measureItemRepository.findByTimeMeasureItems(id, startTime, endTime, afterId);
    }

    public List<Measure> findByName(String name) {
        return measureRepository.findByName(name);
    }

    public Measure updateMeasureStatus(
            Long id,
            String status
    ) {
        return measureRepository.updateStatus(id, status);
    }

    public List<Measure> findAll() {
        return measureRepository.findAll();
    }

    public List<SensorItem> getSensorItems(Long id) {
        return sensorItemRepository.getSensorItems(id);
    }

    public Measure setSensorSetting(
            Long id,
            List<SensorItem> items
    ) {
        return sensorItemRepository.setSensorSetting(id, items);
    }
}
