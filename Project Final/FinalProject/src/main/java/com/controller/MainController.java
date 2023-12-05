package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.ChatGpt3Service;
import com.service.CloudWatchService;

@RestController
public class MainController {

    @Autowired
    private CloudWatchService cloudWatchService;

    @Autowired
    private ChatGpt3Service chatGpt3Service;

    @GetMapping("/process-logs")
    public ResponseEntity<String> processLogs() {
        // Lógica para obtener logs de CloudWatch
        String logGroupName = "your-log-group-name";
        String startTime = "startTime";
        String endTime = "endTime";
        var cloudWatchLogs = cloudWatchService.getCloudWatchLogs(logGroupName, startTime, endTime);

        // Concatenar logs y enviar a ChatGPT-3
        StringBuilder concatenatedLogs = new StringBuilder();
        for (String log : cloudWatchLogs) {
            concatenatedLogs.append(log).append(" ");
        }

        // Lógica para obtener respuesta de ChatGPT-3
        String chatGpt3Response = chatGpt3Service.getChatGpt3Response(concatenatedLogs.toString());

        // ... (lógica para métricas con Prometheus)

        return ResponseEntity.ok(chatGpt3Response);
    }
}
