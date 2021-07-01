package kr.co.greentech.measure.domain

import javax.persistence.*

@Entity
@Table(name = "MEASURE_SETTING")
class MeasureSetting {
    @Id
    @GeneratedValue
    @Column(name = "MEASURE_SETTING_ID")
    var id: Long? = null
    var accel: String? = null
    var slope: String? = null
    var triggerLevel: String? = null
    var standardTime: String? = null
    var request: String? = null
    var time: Long? = null
}