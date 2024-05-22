package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Ogólne uwagi
// Kod jest czytelny i zwięzły - fajnie!
// Popracowałbym na JavaDocami, ale to już bardziej kwestia stylu niż jakości kodu.
// Powydzielałbym metody z serwisów do interfejsów, componentów, etc.
// Zmniejszyłbym odpowiedzialności niektórych serwisów
// Wszystkie klasy są otwarte. Byc może warto byłoby zastosować zasadę "Open-Closed Principle" i zamknąć klasy, które nie muszą być otwarte.
// Main.java nie wygląda dobrze ;) A Scheduler nie do końca mówi co robi.
// Nazwa pakietu org.example nie jest zbyt opisowa. Może warto byłoby zmienić na coś bardziej adekwatnego?
// Warto byłoby zastosować jakieś wzorce projektowe
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
}