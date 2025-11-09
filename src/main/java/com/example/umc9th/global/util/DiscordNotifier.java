package com.example.umc9th.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DiscordNotifier {

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    @Value("${discord.webhook.url:}")
    private String webhookUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendErrorMessage(Exception ex, String requestUri) {
        // 로컬 환경에서는 전송하지 않음
        if ("local".equalsIgnoreCase(activeProfile)) {
            log.info("[DiscordNotifier] local 환경 - 알림 전송 생략");
            return;
        }

        // Webhook URL 없으면 전송 불가
        if (webhookUrl == null || webhookUrl.isBlank()) {
            log.warn("[DiscordNotifier] Webhook URL 미설정 - 알림 전송 생략");
            return;
        }

        // 보낼 메시지 내용
        String content = String.format(
                "⚠️ **500 에러 발생**\n" +
                        "**환경:** %s\n" +
                        "**시간:** %s\n" +
                        "**요청 URL:** %s\n" +
                        "**예외 타입:** %s\n" +
                        "**메시지:** %s",
                activeProfile,
                LocalDateTime.now(),
                requestUri,
                ex.getClass().getSimpleName(),
                safeMessage(ex.getMessage())
        );

        try {
            // JSON body를 안전하게 생성
            Map<String, String> body = new HashMap<>();
            body.put("content", content);

            String jsonPayload = objectMapper.writeValueAsString(body);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            restTemplate.postForEntity(webhookUrl, new HttpEntity<>(jsonPayload, headers), String.class);
            log.info("[DiscordNotifier] 에러 알림 전송 완료");
        } catch (Exception e) {
            log.error("[DiscordNotifier] 에러 알림 전송 실패: {}", e.getMessage());
        }
    }

    private String safeMessage(String msg) {
        return (msg == null || msg.isBlank()) ? "(no message)" : msg;
    }
}
