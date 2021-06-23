package kr.co.greentech.measure.controller;

import kr.co.greentech.measure.domain.Company;
import kr.co.greentech.measure.domain.Measure;
import kr.co.greentech.measure.domain.MeasureItem;
import kr.co.greentech.measure.domain.SensorItem;
import kr.co.greentech.measure.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MeasureController {

    @Autowired MeasureService measureService;


    @PostMapping("/measure/post/company")
    @ResponseBody
    public Company joinCompany(
            @RequestParam("name") String name,
            @RequestParam("ip") String ip
    ) {
        return measureService.joinCompany(name, ip);
    }


    @GetMapping("/measure/get/company/id")
    @ResponseBody
    public Company findCompany(
            @RequestParam("id") Long id
    ) {
        return measureService.findCompany(id);
    }


    @GetMapping("/measure/get/company/name")
    @ResponseBody
    public Company findCompany(
            @RequestParam("name") String name
    ) {
        return measureService.findCompany(name);
    }


    @PostMapping("/measure/post/start")
    @ResponseBody
    public Measure measureStart(
            @RequestParam("id") Long id,
            @RequestParam("name") String name
    ) {
        return measureService.measureStart(id, name);
    }


    @PostMapping("/measure/post/items")
    @ResponseBody
    public Long addMeasureItems(
            @RequestParam("id") Long id,
            @RequestBody List<MeasureItem> items
    ) {

        Measure measure = measureService.addMeasureItems(id, items);
        return measure.getMeasureId();
    }


    @GetMapping("/measure/get/items/time")
    @ResponseBody
    public List<MeasureItem> getMeasureItems(
            @RequestParam("id") Long id,
            @RequestParam("startTime") Long startTime,
            @RequestParam("endTime") Long endTime,
            @RequestParam("afterId") Long afterId
    ) {
        return measureService.findByTimeMeasureItems(id, startTime, endTime, afterId);
    }


    @PostMapping("/measure/set/status")
    @ResponseBody
    public Long setMeasureStatus(
            @RequestParam("id") Long id,
            @RequestParam("status") String status
    ) {
        return measureService.updateMeasureStatus(id, status).getMeasureId();
    }


    @GetMapping("/measure/get/list")
    @ResponseBody
    public List<Measure> getMeasureList(@RequestParam("name") String name) {
        return measureService.findByName(name);
    }


    @GetMapping("/measure/get")
    @ResponseBody
    public Measure getMeasure(@RequestParam("id") Long id) {
        return measureService.findOne(id);
    }


    @GetMapping("/measure/sensor/get/list")
    @ResponseBody
    public List<SensorItem> getSensorItems(@RequestParam("id") Long id) {
        return measureService.getSensorItems(id);
    }


    @PostMapping("/measure/sensor/set/list")
    @ResponseBody
    public Measure setMeasureStatus(
            @RequestParam("id") Long id,
            @RequestBody List<SensorItem> items
    ) {
        return measureService.setSensorSetting(id, items);
    }
}
