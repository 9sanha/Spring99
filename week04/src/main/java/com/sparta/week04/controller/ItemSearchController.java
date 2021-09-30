package com.sparta.week04.controller;


import com.sparta.week04.dto.ItemDto;
import com.sparta.week04.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;
@Controller
class ItemSearchController {
    private final ItemSearchService itemSearchService;
    @Autowired
    public ItemSearchController(ItemSearchService itemSearchService) {
        this.itemSearchService = itemSearchService;
    }
    @GetMapping("/api/search")
    @ResponseBody
    public List<ItemDto> getItems(@RequestParam String query) throws IOException {
        return itemSearchService.getItems(query);
    }
}