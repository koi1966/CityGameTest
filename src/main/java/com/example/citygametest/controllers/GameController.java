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
    String curentCity,LastСharСity;

    {
        try {
            list = Files.readAllLines(Paths.get("src/main/resources/city.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/begin")
    public String begin() {

        int b = list.size();
        int random_list = (int) (Math.random() * b);
        curentCity = list.get(random_list);
        return curentCity.toUpperCase();
    }

    @GetMapping("/next")
    public String next(@RequestParam String city) {
        List<String> strings = null;
        StringBuilder strings_city = new StringBuilder(city.toUpperCase());

        //    перша буква - firstСharСity
        String firstСharСity = Character.toString(strings_city.charAt(0));
        //           остання буква слова
        LastСharСity = Character.toString(curentCity.toUpperCase().charAt(curentCity.length()-1));

        // проверка на совпадение
        if (firstСharСity.equals(LastСharСity)){
//           остання буква слова
            LastСharСity = Character.toString(strings_city.charAt(strings_city.length()-1));

            strings = list.stream().filter(it -> it.startsWith(LastСharСity)).collect(Collectors.toList());
            // рандомное число
            int b = strings.size();
            if (!(b == 0)) {
            int random_list = (int) (Math.random() * b);
            curentCity = strings.get(random_list);
            }
            else { return  " Вітаю Ви виграли. Кінець гри";}

        }else{ return  "Ігрок ввів слово не на ту літеру."; }

        return "CITY: " +curentCity;
    }

    @PostMapping("/end")
    public String end() { return " Дякуємо за гру "; }
}
