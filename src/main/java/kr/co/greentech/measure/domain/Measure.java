package kr.co.greentech.measure.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MEASURE")
public class Measure {
    @Id
    @GeneratedValue
    @Column(name = "MEASURE_ID")
    private Long id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Enumerated(EnumType.STRING)
    private MeasureMode mode;


    public static Measure createMeasure(String name, String mode) {
        Measure measure = new Measure();
        measure.setName(name);

        if (mode.equals("step")) {
            measure.setMode(MeasureMode.STEP);
        } else {
            measure.setMode(MeasureMode.INTERVAL);
        }

        measure.setCreateTime(new Date(System.currentTimeMillis()));

        return measure;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public MeasureMode getMode() {
        return mode;
    }

    public void setMode(MeasureMode mode) {
        this.mode = mode;
    }
}
