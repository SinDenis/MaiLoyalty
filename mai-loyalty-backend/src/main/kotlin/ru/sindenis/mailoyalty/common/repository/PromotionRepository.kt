package ru.sindenis.mailoyalty.common.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.sindenis.mailoyalty.common.model.Promotion

interface PromotionRepository : JpaRepository<Promotion, Long> {

    @Query("select p from Promotion p where p.companyId = :companyUserId")
    fun findCompanyUserPromotions(@Param("companyUserId") companyUserId: Long): List<Promotion>

}
