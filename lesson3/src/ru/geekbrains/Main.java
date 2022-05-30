package ru.geekbrains;

/**
 *Java2. HomeWork2
 *
 * @autor Andrey Zhilenkov
 * @ version 18.10.2021
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Argentum");
        list.add("Aurum");
        list.add("Titan");
        list.add("Tsilitsium");
        list.add("Brom");
        list.add("Helium");
        list.add("Oxigen");
        list.add("Ferrum");
        list.add("Selen");
        list.add("Silur");
        list.add("Helium");
        list.add("Argentum");
        list.add("Alluminium");
        list.add("Phosphor");
        list.add("Astat");
        list.add("Phosphor");
        list.add("Brom");
        list.add("Uranium");
        list.add("Titan");
        list.add("Phosphor");

        System.out.println();
        System.out.println("Full list: \n");
        list.forEach(System.out::println);

        System.out.println();
        System.out.println("Unique list: \n");
        new ArrayList<>(new HashSet<>(list)).forEach(System.out::println);

        System.out.println();
        System.out.println("Repeat list: \n");
        Map<String, Integer> wordCount = new HashMap<>();
        for (String a : list) {
            wordCount.put(a, wordCount.getOrDefault(a, 0) + 1);
        }
        wordCount.entrySet().forEach(System.out::println);

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ivanov", 89322342221L);
        phoneBook.add("Petrov", 89322342324L);
        phoneBook.add("Sidorov", 89322342323L);
        phoneBook.add("Smirnov", 89322342322L);
        phoneBook.add("Pavlov", 89322342327L);
        phoneBook.add("Tiunov", 89322342326L);
        phoneBook.add("Ivanov", 89322342325L);
        phoneBook.add("Zibrov", 89322342329L);

        System.out.println();
        phoneBook.get("Ivanov");
        phoneBook.get("Petrov");
        phoneBook.get("Sidorov");
        phoneBook.get("Pavlov");
        phoneBook.get("Tiunov");
        phoneBook.get("Komarovv");
        phoneBook.get("Zibrov");
    }
}

