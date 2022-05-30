package ru.geekbrains;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PhoneBook {

    HashMap<String, Long> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void add(String name, long number) {
        phoneBook.put(name, number);
    }

    public void get(String name) {
        if (phoneBook.containsKey(name)) {
        Set<Map.Entry<String, Long>> entry = phoneBook.entrySet();
            for (Map.Entry<String, Long> a : entry) {
                if (a.getKey().equals(name)) {
                    System.out.println(a.getKey() + " : " + a.getValue());

                }
            }
        }else{
            System.out.printf("%s : absent in phonebook.\n", name);
        }
    }
}
