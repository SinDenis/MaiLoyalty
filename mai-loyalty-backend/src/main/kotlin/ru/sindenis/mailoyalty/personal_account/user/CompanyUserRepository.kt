package ru.sindenis.mailoyalty.personal_account.user

import org.springframework.data.jpa.repository.JpaRepository

interface CompanyUserRepository: JpaRepository<CompanyUser, Long> {

    fun findByUsername(username: String): CompanyUser?

}
