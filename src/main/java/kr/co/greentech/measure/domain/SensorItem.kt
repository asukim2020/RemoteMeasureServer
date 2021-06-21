package kr.co.greentech.measure.domain

import javax.persistence.*

@Entity
@Table(name = "SENSOR_ITEM")
class SensorItem {
    @Id
    @GeneratedValue
    @Column(name = "SENSOR_ITEM_ID")
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "MEASURE_ID")
    var measure: Measure? = null

    var number: String? = null
    var isOn: Boolean? = null
    var type: String? = null
    var ampGain: String? = null
    var applyV: String? = null
    var filter: String? = null
}