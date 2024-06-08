package ru.sindenis.mailoyalty.bot.api.commands

import ru.sindenis.mailoyalty.bot.api.Client
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.sindenis.mailoyalty.bot.api.ClientRepository
import ru.sindenis.mailoyalty.bot.api.buttons.WebAppButton

private const val RESPONSE_TEXT = "Перейдите в приложение"

@Component
class StartCommand(
    private val webAppButton: WebAppButton,
    private val clientRepository: ClientRepository,
) : BotCommand(LoyaltyBotCommand.START.text, LoyaltyBotCommand.START.description) {
    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        clientRepository.save(Client(tgUsername = user.userName))
        absSender.execute(
            SendMessage(chat.id.toString(), RESPONSE_TEXT)
                .apply { replyMarkup = InlineKeyboardMarkup(listOf(listOf(webAppButton))) }
        )
    }
}
