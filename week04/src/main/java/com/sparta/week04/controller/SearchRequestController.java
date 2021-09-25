package com.sparta.week04.controller;

import com.sparta.week04.models.ItemDto;
import com.sparta.week04.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor // final 로 선언된 클래스를 자동으로 생성합니다.
@RestController // JSON으로 응답함을 선언합니다.
public class SearchRequestController {

    //이거 하려고 네이버검색 컴포넌트로 만들어 줬다다
    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")
    public List<ItemDto> execSearch(@RequestParam String query) {// 넘어오는 파라미터 중에 query값이 있으면
        System.out.println("query: "+query);
        //그녀석을 넣어줘라!
        String resultString = naverShopSearch.search(query);


        return naverShopSearch.fromJSONtoItems(resultString);
    }
}