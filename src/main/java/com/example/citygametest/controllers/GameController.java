package com.example.citygametest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    char lastCharCity;

    {
        try {
            list = Files.readAllLines(Paths.get("src/main/resources/city.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/begin")
    public String begin() {

        int randomNumber = (int) (Math.random() * list.size());
        curentCity = list.get(randomNumber).toUpperCase();
        return curentCity;
    }

    @GetMapping("/next")
    public String next(@RequestParam String city) {
        List<String> strings;
        city = city.toUpperCase();

        if (city.equals(curentCity)) {return " !!!  Міста мають відрізнятись. Попереднє місто - "+curentCity;}

        char firstCharCity = city.charAt(0);
        lastCharCity = curentCity.charAt(curentCity.length() - 1);

        if (lastCharCity == firstCharCity) {
            lastCharCity = city.charAt(city.length() - 1);

            strings = list.stream().filter(it -> it.startsWith(String.valueOf(lastCharCity))).collect(Collectors.toList());

            int size = strings.size();
            if (size == 0) {
                return " Вітаю Ви виграли. Кінець гри";
            } else {
                int randomList = (int) (Math.random() * size);
                curentCity = strings.get(randomList).toUpperCase();
            }

        } else {
            return "Ігрок ввів слово не на ту літеру. Пореднє місто - "+curentCity;
        }

        return "CITY: " + curentCity;
    }

    @PostMapping("/end")
    public String end() {
        return " Дякуємо за гру ";
    }
}
