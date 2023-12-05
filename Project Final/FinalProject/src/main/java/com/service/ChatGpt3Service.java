package com.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.micrometer.core.annotation.Timed;

import java.util.Collections;
import java.util.Map;

@Service
public class ChatGpt3Service {

    @Value("${chatgpt3.api.endpoint}")
    private String chatGpt3ApiEndpoint;

    //cuando te compile te paso el api key que ya tengo
    @Value("${chatgpt3.api.key}")
    private String chatGpt3ApiKey;

    private final RestTemplate restTemplate;

    public ChatGpt3Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Timed(value = "chatgpt3.api.request", description = "Time taken to make a request to ChatGPT-3 API")
    public String getChatGpt3Response(String inputText) {
        // Configura las cabeceras de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + chatGpt3ApiKey);
        headers.set("Content-Type", "application/json");

        // Configura la solicitud
        Map<String, String> requestBody = Collections.singletonMap("text", inputText);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Realiza la llamada a la API de ChatGPT-3
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(chatGpt3ApiEndpoint, requestEntity, Map.class);

        // Maneja la respuesta de la API
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Map<String, String> responseBody = responseEntity.getBody();
            return responseBody.get("response"); // Ajusta esto según la estructura real de la respuesta de la API
        } else {
            // Maneja el error según lo que necesitemos
            throw new RuntimeException("Error al llamar a la API de ChatGPT-3. Código de estado: " + responseEntity.getStatusCodeValue());
        }
    }
}
