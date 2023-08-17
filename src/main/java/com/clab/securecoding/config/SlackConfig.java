package com.clab.securecoding.config;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfig {

    @Value("${slack.webhook-uri}")
    private String slackToken;

    @Bean
    public SlackApi slackApi() {
        return new SlackApi(slackToken);
    }

    @Bean
    public SlackAttachment slackAttachment() {
        SlackAttachment attachment = new SlackAttachment();
        attachment.setFallback("Error");
        attachment.setColor("danger");
        attachment.setTitle("Error Directory");
        return attachment;
    }

    @Bean
    public SlackMessage slackMessage() {
        SlackMessage message = new SlackMessage();
        message.setIcon(":ghost:");
        message.setText("Error Detected");
        message.setUsername("TODA Error Catcher");
        return message;
    }

}