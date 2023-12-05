package com.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.logs.LogsClient;
import software.amazon.awssdk.services.logs.model.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CloudWatchService {

    private final LogsClient logsClient;
    private final Region region;

    @Timed(value = "cloudwatch.logs.fetch", description = "Time taken to fetch CloudWatch logs")
    public CloudWatchService() {
        this.logsClient = LogsClient.builder().region(Region.US_EAST_1).build(); // Sebas ajustar la region de aws
        this.region = Region.US_EAST_1;
    }

    public List<String> getCloudWatchLogs(String logGroupName, String startTime, String endTime) {
        try {
            GetLogEventsRequest getLogEventsRequest = GetLogEventsRequest.builder()
                    .logGroupName(logGroupName)
                    .startTime(Long.parseLong(startTime))
                    .endTime(Long.parseLong(endTime))
                    .build();

            GetLogEventsResponse response = logsClient.getLogEvents(getLogEventsRequest);

            return response.events().stream()
                    .map(OutputLogEvent::message)
                    .collect(Collectors.toList());

        } catch (LogsException e) {
            e.printStackTrace(); // sebas manejar las excepction de la integracion
            return List.of(); // retorna una lista vacia en caso de error
        }
    }

    // cerrar la conexion
    public void closeLogsClient() {
        logsClient.close();
    }
}
