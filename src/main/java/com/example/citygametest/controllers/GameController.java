package com.example.citygametest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {

    List<String> list;
    String curentCity;

    {
        try {
            list = Files.readAllLines(Paths.get("src/main/resources/city.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/begin")
    public String begin() {


        final List<String> strings = list.stream().filter(it -> it.startsWith("А")).collect(Collectors.toList());
        return strings.toString();
    }

    @GetMapping("/next")
    public String next(@RequestParam String city) {

        return "CITY: " + city+ "yy";
    }
}
