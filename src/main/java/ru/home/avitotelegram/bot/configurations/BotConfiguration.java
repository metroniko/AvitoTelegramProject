package ru.home.avitotelegram.bot.configurations;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix ="telegrambot")
public class BotConfiguration {

    private String proxyHost;
    private int proxyPort;
    private DefaultBotOptions.ProxyType proxyType;

    @Bean
    public DefaultBotOptions defaultBotOptionsConfigure() {
        DefaultBotOptions options =
                ApiContext.getInstance(DefaultBotOptions.class);
        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);

        return options;
    }
}
