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

    private DefaultBotOptions.ProxyType proxyType = DefaultBotOptions.ProxyType.SOCKS5;

    @Bean
    public DefaultBotOptions defaultBotOptionsConfigure() {
        DefaultBotOptions options =
                ApiContext.getInstance(DefaultBotOptions.class);
        String proxyHost = "localhost";
//        options.setProxyHost(proxyHost);
//        int proxyPort = 9150;
//        options.setProxyPort(proxyPort);
//        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

        return options;
    }
}
