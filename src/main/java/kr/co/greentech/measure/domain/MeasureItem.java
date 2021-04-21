package kr.co.greentech.measure.domain;

import javax.persistence.*;

@Entity
@Table(name = "MEASURE_ITEM")
public class MeasureItem {
    @Id
    @GeneratedValue
    @Column(name = "MEASURE_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Measure measure;

    private int count;

    private String data;

    private int measureCount;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getMeasureCount() {
        return measureCount;
    }

    public void setMeasureCount(int measureCount) {
        this.measureCount = measureCount;
    }
}
