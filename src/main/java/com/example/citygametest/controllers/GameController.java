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
    String curentCity, LastСharСity;
    String strings = "Житомир";
    {
        try {
            list = Files.readAllLines(Paths.get("src/main/resources/city.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/begin")
    public String begin() {

//        final List<String> strings = list.stream().filter(it -> it.startsWith("А")).collect(Collectors.toList());
//        String strings = "Житомир";
        LastСharСity = Character.toString(strings.toUpperCase().charAt(strings.length()-1));
        return strings + "  -"+ LastСharСity;
    }

    @GetMapping("/next")
    public String next(@RequestParam String city) {
        List<String> strings = null;
        StringBuilder strings_city = new StringBuilder(city.toUpperCase());
        //    Проверка первой буквы -city- на последнюю  -LastСharСity-
        String firstСharСity = Character.toString(strings_city.charAt(0));
        if (firstСharСity.equals(LastСharСity)){
//            System.out.println("Statement  is true");
            LastСharСity = Character.toString(strings_city.charAt(city.length()-1));

            strings = list.stream().filter(it -> it.startsWith(LastСharСity)).collect(Collectors.toList());
            // рандомное число

        }else{ return  "Ігрок ввів слово не на ту літеру."; }

        return "CITY: " +strings + " .. "+ LastСharСity;
    }

    @PostMapping("/end")
    public String end() { return " Дякуємо за гру "; }
}
