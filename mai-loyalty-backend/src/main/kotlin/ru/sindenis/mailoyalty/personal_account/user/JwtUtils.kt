package ru.sindenis.mailoyalty.personal_account.user

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.SecretKey

private const val jwtSecret =
    "ajshdfhkasdfhasdkfhjasdkfhdaskfjadshfkasdjfksadfjsldfjsaldjflsdkfjlkdsafjaldskjflksdfjskdlfjslkdfjklsadfjsdlkfjklsdjfkldsjfklsdajfklsdajfklsdjfklsadjfklsjflsdjkfj"
private val jwtKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))

fun generateJwt(user: CompanyUser): String {
    return Jwts.builder()
        .signWith(jwtKey)
        .claim("companyId", user.id)
        .claim("username", user.username)
        .expiration(Date.from(Instant.now().plus(15, ChronoUnit.DAYS)))
        .compact()
}

fun getUserId(jwt: String): Long? = runCatching {
    Jwts.parser()
        .verifyWith(jwtKey)
        .build()
        .parseSignedClaims(jwt)
        .payload
        .get("companyId", java.lang.Integer::class.java).toLong()
}.getOrNull()
