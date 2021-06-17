package kr.co.greentech.measure.domain

import javax.persistence.*

@Entity
@Table(name = "MEASURE_ITEM")
class MeasureItem {
    @Id
    @GeneratedValue
    @Column(name = "MEASURE_ITEM_ID")
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "MEASURE_ID")
    var measure: Measure? = null

    @Column(nullable = false)
    var time: Long? = null

    @Column(nullable = false)
    var data: String? = null
}