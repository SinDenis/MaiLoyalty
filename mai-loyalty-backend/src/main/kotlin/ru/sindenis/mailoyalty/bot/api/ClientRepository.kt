package ru.sindenis.mailoyalty.bot.api

import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository: JpaRepository<Client, Long> {

    fun findByTgUsername(tgUsername: String): Client?

}
