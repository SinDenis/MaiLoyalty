package ru.sindenis.mailoyalty.bot.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LoyaltyBot(
    @Value("\${telegram.token}") private val token: String,
    @Value("\${telegram.username}") private val username: String,
    commands: Set<BotCommand>,
) : TelegramLongPollingCommandBot(token) {

    init {
        registerAll(*commands.toTypedArray())
    }

    override fun getBotUsername(): String = username

    override fun processNonCommandUpdate(update: Update) {}

}


