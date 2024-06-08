package ru.sindenis.mailoyalty.personal_account.promotion

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ru.sindenis.mailoyalty.common.model.Promotion
import ru.sindenis.mailoyalty.common.repository.PromotionRepository
import ru.sindenis.mailoyalty.personal_account.user.getUserId
import ru.sindenis.mailoyalty.restapi.PersonalAccountPromotionsApi
import ru.sindenis.mailoyalty.restapi.dto.*
import java.time.Instant

private val logger = KotlinLogging.logger {}

@RestController
class PersonalAccountPromotionController(
    private val promotionRepository: PromotionRepository,
    private val promotionCodeRepository: PromotionCodeRepository,
) : PersonalAccountPromotionsApi {

    override fun apiPersonalAccountPromotionsGet(jwt: String): ResponseEntity<GetPromotionsResponse> {
        val userId = getUserId(jwt) ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        logger.info { "Get personal account promotions for user $userId" }
        return ResponseEntity.ok(
            GetPromotionsResponse(
                promotionRepository.findCompanyUserPromotions(userId)
                    .map { PromotionResponse(it.id!!, it.title, it.description, it.category) })
        )
    }

    override fun apiPersonalAccountPromotionsIdValidatePost(
        jwt: String,
        validateCodeRequest: ValidateCodeRequest
    ): ResponseEntity<Unit> {
        val userId = getUserId(jwt) ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        logger.info { "Validate code for company = $userId $validateCodeRequest" }
        val promotionCode = promotionCodeRepository.findByCode(validateCodeRequest.code.toLong())
            ?: return ResponseEntity.badRequest().build()
        if (promotionCode.dateExpiration.isBefore(Instant.now())) {
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity.ok().build()
    }

    override fun apiPersonalAccountPromotionsPost(
        jwt: String,
        createPromotionRequest: CreatePromotionRequest
    ): ResponseEntity<CreatePromotionResponse> {
        val companyUserId = getUserId(jwt) ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        val promotion = promotionRepository.save(
            Promotion(
                title = createPromotionRequest.title,
                description = createPromotionRequest.description,
                category = createPromotionRequest.category,
                companyId = companyUserId,
            )
        )
        logger.info { "Saved promotion for user = $companyUserId: $promotion" }
        return ResponseEntity.ok(
            CreatePromotionResponse(
                promotion.id!!,
                promotion.title,
                promotion.description,
                promotion.category
            )
        )
    }

}
