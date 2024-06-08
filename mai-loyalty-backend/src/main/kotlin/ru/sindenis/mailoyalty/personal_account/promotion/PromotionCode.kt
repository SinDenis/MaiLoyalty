package ru.sindenis.mailoyalty.personal_account.promotion

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "promotion_code")
data class PromotionCode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "promotion_id")
    val promotionId: Long = 0,

    @Column(name = "code")
    val code: Long = 0,

    @Column(name = "client_id")
    val clientId: Long = 0,

    @Column(name = "date_expiration")
    val dateExpiration: Instant = Instant.now(),
)
