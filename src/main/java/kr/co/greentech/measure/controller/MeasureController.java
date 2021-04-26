package kr.co.greentech.measure.controller;

import kr.co.greentech.measure.domain.Measure;
import kr.co.greentech.measure.domain.MeasureItem;
import kr.co.greentech.measure.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MeasureController {

    @Autowired MeasureService measureService;

//    @GetMapping("/measure")
//    @ResponseBody
//    public Measure measure() {
//        Long id = measureService.measureStart("asu");
//        return measureService.findOne(id);
//    }


    @PostMapping("/measure/post/start")
    @ResponseBody
    public Measure measureStart(
            @RequestParam("name") String name,
            @RequestParam("mode") String mode
    ) {
        return measureService.measureStart(name, mode);
    }

    @PostMapping("/measure/post/items")
    @ResponseBody
    public Long addMeasureItems(
            @RequestParam("id") Long id,
            @RequestBody List<MeasureItem> items
    ) {

        Measure measure = measureService.addMeasureItems(id, items);
        return measure.getId();
    }

    @GetMapping("/measure/get/items/count")
    @ResponseBody
    public List<MeasureItem> getMeasureItems(
            @RequestParam("id") Long id,
            @RequestParam("count") Integer count
    ) {
        return measureService.findByCountUpMeasureItems(id, count);
    }

    @PostMapping("/measure/set/status")
    @ResponseBody
    public Long setMeasureStatus(
            @RequestParam("id") Long id,
            @RequestParam("status") String status
    ) {
        return measureService.updateMeasureStatus(id, status).getId();
    }

    // TODO: - get measure list
    @GetMapping("/measure/get/list")
    @ResponseBody
    public List<Measure> getMeasureList(@RequestParam("name") String name) {
        return measureService.findByName(name);
    }

    // TODO: - get measure id
    @GetMapping("/measure/get")
    @ResponseBody
    public Measure getMeasure(@RequestParam("id") Long id) {
        return measureService.findOne(id);
    }

}
