//package com.sparta.week04.utils;
//
//import com.sparta.week04.dto.ItemDto;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.http.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component //스프링이 자동으로 사용할 수 있는 클래스 목록애 들어간다??
//public class NaverShopSearch {
//    public String search( String query) {
//        RestTemplate rest = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Naver-Client-Id", "ewIt3Y_S4JQZEksLlacs");
//        headers.add("X-Naver-Client-Secret", "gDV7ccFsl5");
//        headers.add("Content-Type","application/json");
//        String body = "";
//
//        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
//        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query="+query, HttpMethod.GET, requestEntity, String.class);
//        HttpStatus httpStatus = responseEntity.getStatusCode();
//        int status = httpStatus.value();
//        String response = responseEntity.getBody();
////        System.out.println("Response status: " + status);
////        System.out.println(response);
//        return response;
//    }
//
//    public List<ItemDto> fromJSONtoItems(String result) {
//
//        JSONObject rjson = new JSONObject(result); //문자열(resurt)을 내가 JOSON Object로 만들어줄게!
//        JSONArray items = rjson.getJSONArray("items");
//
//        List<ItemDto> itemDtoList = new ArrayList<>();
//
//        for (int i = 0; i < items.length(); i++) {
//            JSONObject itemJson = items.getJSONObject(i);
//
//            ItemDto itemDto = new ItemDto(itemJson);
//            itemDtoList.add(itemDto);
//        }
//
//
//        return itemDtoList;
//    }
//}