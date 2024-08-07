package com.telran.gardenshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GardenShopController {
    @GetMapping("/garden")
    public String helloMessage(){
        return "Hello from my excellent website!";
    }
}
