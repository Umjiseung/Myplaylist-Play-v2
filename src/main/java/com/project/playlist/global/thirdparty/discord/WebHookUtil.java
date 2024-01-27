package com.project.playlist.global.thirdparty.discord;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class WebHookUtil {
    @Value("${discord.webhook}")
    private String url;

    public void callEvent(String email, String username) {
        JSONObject data = new JSONObject();

        try {
            JSONObject embed = new JSONObject();
            embed.put("title", "회원가입 알림 확인하기");
            embed.put("description", "새로운 회원가입 요청이 왔어요!");
            embed.put("color", 48127);

            JSONObject fields = new JSONObject();
            fields.put("name", "정보");
            fields.put("value", "학생 이름: " + username + "\n이메일: " + email);

            JSONArray fieldsArray = new JSONArray();
            fieldsArray.put(fields);

            embed.put("fields", fieldsArray);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", "## [알림] 새로운 회원가입 요청!");

            JSONArray embedsArray = new JSONArray();
            embedsArray.put(embed);

            jsonObject.put("embeds", embedsArray);

            data.put("embeds", embedsArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        send(data);
    }

    private void send(JSONObject data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(data.toString(), headers);
        restTemplate.postForObject(url, entity, String.class);
    }

}
