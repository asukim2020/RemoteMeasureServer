package kr.co.greentech.measure.domain

import javax.persistence.*

@Entity
@Table(name = "MEASURE")
class Measure {
    @Id
    @GeneratedValue
    @Column(name = "MEASURE_ID")
    var measureId: Long? = null
    var name: String? = null
    var time: Long? = null

    @Enumerated(EnumType.STRING)
    var status: MeasureStatus? = null

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    var company: Company? = null

    companion object {
        @JvmStatic
        fun create(name: String): Measure {
            val measure = Measure()
            measure.name = name
            measure.status = MeasureStatus.ING
            measure.time = System.currentTimeMillis()

            return measure
        }
    }
}