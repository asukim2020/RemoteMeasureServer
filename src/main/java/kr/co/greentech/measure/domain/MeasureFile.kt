package kr.co.greentech.measure.domain

import javax.persistence.*

@Entity
@Table(name = "MEASURE_FILE")
class MeasureFile {
    @Id
    @GeneratedValue
    @Column(name = "MEASURE_ID")
    var measureId: Long? = null
    var type: String? = null
    var url: String? = null
    var time: Long? = null
}