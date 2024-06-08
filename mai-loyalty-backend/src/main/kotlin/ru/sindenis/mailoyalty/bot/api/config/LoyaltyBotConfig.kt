package ru.sindenis.mailoyalty.bot.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@Configuration
class LoyaltyBotConfig {

    @Bean
    fun telegramBotsApi(bot: LoyaltyBot) = TelegramBotsApi(DefaultBotSession::class.java).apply { registerBot(bot) }

}
