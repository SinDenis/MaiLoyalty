package ru.sindenis.mailoyalty.bot.webapp.controller

import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.sindenis.mailoyalty.bot.api.ClientRepository
import ru.sindenis.mailoyalty.common.repository.PromotionRepository
import ru.sindenis.mailoyalty.personal_account.promotion.PromotionCode
import ru.sindenis.mailoyalty.personal_account.promotion.PromotionCodeRepository
import ru.sindenis.mailoyalty.restapi.WebAppPromotionsApi
import ru.sindenis.mailoyalty.restapi.dto.ApplyPromotionResponse
import ru.sindenis.mailoyalty.restapi.dto.PromotionResponse
import ru.sindenis.mailoyalty.restapi.dto.PromotionsPageResponse
import kotlin.random.Random

@RestController
class PromotionController(
    private val promotionRepository: PromotionRepository,
    private val promotionCodeRepository: PromotionCodeRepository,
    private val clientRepository: ClientRepository,
) : WebAppPromotionsApi {

    override fun apiWebAppPromotionsAppyPost(
        promotionId: Int,
        username: String
    ): ResponseEntity<ApplyPromotionResponse> {
        val user = clientRepository.findByTgUsername(username)
        val code = promotionCodeRepository.save(
            PromotionCode(
                promotionId = promotionId.toLong(),
                code = Random(1323).hashCode().toLong(),
                clientId = user?.id!!
            )
        )
        return ResponseEntity.ok(ApplyPromotionResponse(code.code.toInt()))
    }

    override fun apiWebAppPromotionsGet(
        @Valid
        @Parameter(description = "Поисковый запрос")
        @RequestParam(required = false, value = "searchQuery")
        searchQuery: String?
    ): ResponseEntity<PromotionsPageResponse> {
        return ResponseEntity.ok(
            PromotionsPageResponse(
                promotionRepository.findAll()
                    .map { PromotionResponse(it.id!!, it.title, it.description, it.category) }
                    .filter {
                        searchQuery == null
                                || it.title.contains(searchQuery, ignoreCase = true)
                                || it.description.contains(searchQuery, ignoreCase = true)
                    }
            )
        )
    }
}
