package ru.sindenis.mailoyalty.common.model

import jakarta.persistence.*
import ru.sindenis.mailoyalty.personal_account.user.CompanyUser

@Entity
@Table(name = "promotion")
data class Promotion(
    @Id
    @SequenceGenerator(name = "promotion_id_seq", sequenceName = "promotion_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_id_seq")
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "company_id")
    val companyId: Long = 0,

    @Column(name = "title")
    val title: String = "",

    @Column(name = "description")
    val description: String = "",

    @Column(name = "category")
    val category: String = ""
) {
    constructor() : this(null)

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
    val company: CompanyUser? = null
}
