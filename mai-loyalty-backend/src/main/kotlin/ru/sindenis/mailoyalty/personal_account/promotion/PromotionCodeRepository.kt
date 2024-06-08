package ru.sindenis.mailoyalty.personal_account.promotion

import org.springframework.data.jpa.repository.JpaRepository

interface PromotionCodeRepository: JpaRepository<PromotionCode, Long> {

    fun findByCode(promotionCode: Long): PromotionCode?

}
