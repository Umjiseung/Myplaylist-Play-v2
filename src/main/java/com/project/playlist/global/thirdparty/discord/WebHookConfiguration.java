package com.project.playlist.global.thirdparty.discord;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebHookConfiguration {
    @Bean
    public WebHookUtil webHookUtil() {
        return new WebHookUtil();
    }
}
