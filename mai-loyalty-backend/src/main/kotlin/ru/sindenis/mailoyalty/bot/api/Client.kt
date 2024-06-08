package ru.sindenis.mailoyalty.bot.api

import jakarta.persistence.*

@Entity
@Table(name = "client")
data class Client(
    @Id
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "tg_username")
    val tgUsername: String = "",

    @Column(name = "age")
    val age: Int? = null,

    @Column(name = "region_code")
    val regionCode: Int? = null,

    @Column(name = "sex")
    val sex: String? = null
)
