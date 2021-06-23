package kr.co.greentech.measure.domain

import javax.persistence.*

@Entity
@Table(name = "COMPANY")
class Company {
    @Id
    @GeneratedValue
    @Column(name = "COMPANY_ID")
    var id: Long? = null

    @Column(unique = true)
    var name: String? = null
    var time: Long? = null

    @Column(nullable = false)
    var ip: String? = null

    companion object {
        @JvmStatic
        fun create(name: String, ip: String): Company {
            val company = Company()
            company.name = name
            company.time = System.currentTimeMillis()
            company.ip = ip

            return company
        }
    }
}