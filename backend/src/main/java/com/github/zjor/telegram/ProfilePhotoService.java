package com.github.zjor.telegram;

import com.github.zjor.ext.spring.aop.Log;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.abilitybots.api.sender.DefaultSender;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos;

import java.util.Optional;

@Slf4j
public class ProfilePhotoService {

    private final MessageSender messageSender;
    private final String botToken;

    public ProfilePhotoService(DefaultAbsSender bot) {
        messageSender = new DefaultSender(bot);
        botToken = bot.getBotToken();
    }

    @Log
    @SneakyThrows
    public Optional<String> getProfilePhotoFileId(Long userId) {
        var res = messageSender.execute(GetUserProfilePhotos.builder()
                .userId(userId)
                .build());

        if (res.getTotalCount() == 0) {
            return Optional.empty();
        }

        var sizes = res.getPhotos().get(0);
        return Optional.of(sizes.get(sizes.size() / 2).getFileId());
    }

    @Log
    @SneakyThrows
    public String getFileUrl(String fileId) {
        var res = messageSender.execute(GetFile.builder()
                .fileId(fileId)
                .build());
        return res.getFileUrl(botToken);
    }
}
