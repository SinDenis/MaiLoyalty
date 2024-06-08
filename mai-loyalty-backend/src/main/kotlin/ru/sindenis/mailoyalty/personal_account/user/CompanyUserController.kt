package ru.sindenis.mailoyalty.personal_account.user

import com.toxicbakery.bcrypt.Bcrypt
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ru.sindenis.mailoyalty.restapi.PersonalAccountUsersApi
import ru.sindenis.mailoyalty.restapi.dto.LoginUserRequest
import ru.sindenis.mailoyalty.restapi.dto.RegisterUserRequest

@RestController
class CompanyUserController(private val companyUserRepository: CompanyUserRepository) : PersonalAccountUsersApi {

    override fun apiPersonalAccountLoginPost(loginUserRequest: LoginUserRequest): ResponseEntity<Unit> {
        val user = companyUserRepository.findByUsername(loginUserRequest.username) ?: return ResponseEntity.notFound().build()
        val jwt = generateJwt(user)
        val headers = HttpHeaders().apply { set(HttpHeaders.SET_COOKIE, "jwt=$jwt") }
        return ResponseEntity.ok().headers(headers).body(Unit)
    }

    override fun apiPersonalAccountRegisterPost(registerUserRequest: RegisterUserRequest): ResponseEntity<Unit> {
        val companyUser = CompanyUser(
            username = registerUserRequest.username,
            password = Bcrypt.hash(registerUserRequest.password, 4).toString(),
            mail = registerUserRequest.mail,
            company = registerUserRequest.company,
        )
        companyUserRepository.save(companyUser)
        return ResponseEntity.ok().build()
    }

}
