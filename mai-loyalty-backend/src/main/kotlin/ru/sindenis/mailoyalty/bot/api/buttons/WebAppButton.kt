package ru.sindenis.mailoyalty.bot.api.buttons

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo

@Component
class WebAppButton(
    @Value("\${telegram.webapp.url}") private val webAppUrl: String,
) : InlineKeyboardButton("Получить акцию") {
    override fun getWebApp() = WebAppInfo(webAppUrl)
}
