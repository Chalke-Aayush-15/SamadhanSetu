package com.example.SamadhanSetu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor

public class GeoCodeService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAddressFromCoordinates(double latitude, double longitude) {
        String url = String.format(
                "https://nominatim.openstreetmap.org/reverse?format=json&lat=%f&lon=%f",
                latitude, longitude
        );

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> body = response.getBody();
                return (String) body.get("display_name"); // Full address
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Unknown Location";
    }

}
