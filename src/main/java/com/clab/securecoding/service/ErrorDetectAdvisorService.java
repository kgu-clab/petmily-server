package com.clab.securecoding.service;

import lombok.RequiredArgsConstructor;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorDetectAdvisorService {

    @Autowired
    private SlackApi slackApi;

    @Autowired
    private SlackAttachment slackAttachment;

    @Autowired
    private SlackMessage slackMessage;

    @Qualifier("taskExecutor")
    @Autowired
    private TaskExecutor taskExecutor;

    public void handleException(HttpServletRequest request, Exception e) {
        slackAttachment.setTitle("Error: " + e.getClass().getSimpleName());
        slackAttachment.setTitleLink(request.getContextPath());
        slackAttachment.setText("[Exception Stack Trace]\n" + Arrays.toString(e.getStackTrace()));
        slackAttachment.setFields(
                List.of(
                        new SlackField().setTitle("Request URL").setValue(String.valueOf(request.getRequestURL())),
                        new SlackField().setTitle("Request Method").setValue(request.getMethod()),
                        new SlackField().setTitle("Request Time").setValue(new Date().toString()),
                        new SlackField().setTitle("Request IP").setValue(request.getRemoteAddr()),
                        new SlackField().setTitle("Request User-Agent").setValue(request.getHeader("User-Agent"))
                )
        );
        slackMessage.setAttachments(Collections.singletonList(slackAttachment));
        taskExecutor.execute(() -> slackApi.call(slackMessage));
    }
}