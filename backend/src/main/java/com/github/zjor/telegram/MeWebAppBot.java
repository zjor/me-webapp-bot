package com.github.zjor.telegram;

import lombok.extern.slf4j.Slf4j;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
public class MeWebAppBot extends AbilityBot {

    private final String webAppUrl;

    public MeWebAppBot(String botToken,
                       String botUsername,
                       String webAppUrl) {
        super(botToken, botUsername);
        this.webAppUrl = webAppUrl;
    }

    @Override
    public long creatorId() {
        return 0;
    }

    @SuppressWarnings("unused")
    public Ability startAbility() {
        return Ability.builder()
                .name("start")
                .info("starts interaction with a bot")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    try {
                        var message = SendMessage.builder()
                                .chatId(ctx.chatId())
                                .text("Hello, " + ctx.user().getFirstName())
                                .replyMarkup(InlineKeyboardMarkup.builder()
                                        .keyboardRow(List.of(InlineKeyboardButton.builder()
                                                .text("Open profile")
                                                .webApp(WebAppInfo.builder()
                                                        .url(webAppUrl)
                                                        .build())
                                                .build()))
                                        .build())
                                .build();
                        sender.execute(message);
                    } catch (TelegramApiException e) {
                        log.error("Failed to send message: " + e.getMessage(), e);
                        throw new RuntimeException(e);
                    }
                })
                .build();
    }

}
