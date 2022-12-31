package com.github.zjor.config;

import com.github.zjor.telegram.MeWebAppBot;
import com.github.zjor.ext.spring.PropertyLogger;
import com.github.zjor.telegram.ProfilePhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PropertyLogger propertyLogger() {
        return new PropertyLogger();
    }

    @Bean
    public MeWebAppBot meWebAppBot(
            @Value("${telegram.bot.token}") String botToken,
            @Value("${telegram.bot.name}") String botUsername,
            @Value("${telegram.webApp.url}") String webAppUrl
    ) {
        return new MeWebAppBot(botToken, botUsername, webAppUrl);
    }

    @Bean
    public ProfilePhotoService profilePhotoService(MeWebAppBot bot) {
        return new ProfilePhotoService(bot);
    }

}
