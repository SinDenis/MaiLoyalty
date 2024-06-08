package ru.sindenis.mailoyalty.personal_account.user

import jakarta.persistence.*

@Entity
@Table(name = "company_user")
data class CompanyUser(
    @Id
    @SequenceGenerator(name = "company_user_id_seq", sequenceName = "company_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_user_id_seq")
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "username")
    val username: String? = null,

    @Column(name = "password")
    val password: String? = null,

    @Column(name = "mail")
    val mail: String? = null,

    @Column(name = "company")
    val company: String? = null,
)
