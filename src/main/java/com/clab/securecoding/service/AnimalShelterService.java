package com.clab.securecoding.service;

import com.clab.securecoding.exception.ApiRequestFailedException;
import com.clab.securecoding.mapper.AnimalShelterMapper;
import com.clab.securecoding.repository.AnimalShelterRepository;
import com.clab.securecoding.type.dto.AnimalShelterDto;
import com.clab.securecoding.type.entity.AnimalShelter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimalShelterService {

    private final AnimalShelterRepository animalShelterRepository;

    private final AnimalShelterMapper animalShelterMapper;

    @Value("${data.api.key}")
    private String apiKey;

    private static final String SUCCESS_CODE = "00";

    public void retrieveShelters() throws IOException, ParseException {
        String apiUrl = "http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
        RestTemplate restTemplate = new RestTemplate();

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(apiKey, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

        String responseBody = restTemplate.getForObject(urlBuilder.toString(), String.class);

        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = (JSONObject) parser.parse(new StringReader(responseBody));
        JSONObject response = (JSONObject) jsonResponse.get("response");

        JSONObject header = (JSONObject) response.get("header");
        String resultCode = (String) header.get("resultCode");
        if (!SUCCESS_CODE.equals(resultCode)) {
            String errorMsg = (String) header.get("resultMsg");
            throw new ApiRequestFailedException();
        }
        else {
        }

        JSONObject body = (JSONObject) response.get("body");
        JSONObject items = (JSONObject) body.get("items");
        JSONArray itemList = (JSONArray) items.get("item");

        for (Object item : itemList) {
            JSONObject itemObject = (JSONObject) item;
            AnimalShelterDto dto = animalShelterMapper.mapJsonToDto(itemObject);
            AnimalShelter shelter = animalShelterMapper.mapDtoToEntity(dto);
            animalShelterRepository.save(shelter);
        }
    }

    public List<AnimalShelterDto> getAllAnimalShelters () {
        List<AnimalShelter> animalShelters = animalShelterRepository.findAll();
        List<AnimalShelterDto> animalShelterDtos = new ArrayList<>();
        for (AnimalShelter animalShelter : animalShelters) {
            AnimalShelterDto animalShelterDto = animalShelterMapper.mapEntityToDto(animalShelter);
            animalShelterDtos.add(animalShelterDto);
        }
        return animalShelterDtos;
    }

}
