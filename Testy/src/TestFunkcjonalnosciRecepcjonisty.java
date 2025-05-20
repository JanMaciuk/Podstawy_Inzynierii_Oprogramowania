import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestFunkcjonalnosciRecepcjonisty {
    private Recepcjonista recepcjonista;
    private Cennik cennik;
    private ArrayList<Rachunek> rachunki;

    @BeforeEach
    void setUp() {
        recepcjonista = new Recepcjonista();
        cennik = new Cennik();
        cennik.dodajUsluge(new Usluga("Konsultacja", 100));
        cennik.dodajUsluge(new Usluga("Konsultacja specjalistyczna", 200));
        cennik.dodajUsluge(new Usluga("Wystawienie zwolnienia", 50));
        rachunki = new ArrayList<>();
        rachunki.add(new Rachunek(new Usluga[]{cennik.getUslugi().get(0)}, LocalDateTime.now()));
    }
    @Test
    @DisplayName("Poprawne dodanie nowej pozycji do cennika")
    void testDodajNowaPozycjeDoCennika() {
        assertTrue(recepcjonista.dodajCene(cennik, "Czyszczenie uszu", 100));
        assertEquals(4, cennik.getUslugi().size());
    }

    @Test
    @DisplayName("Próba dodania pozycji która już istnieje w cenniku")
    void testDodajPozycjeKtoraJuzIstnieje() {
        assertFalse(recepcjonista.dodajCene(cennik, "Konsultacja", 100));
        assertEquals(3, cennik.getUslugi().size());
    }

    @Test
    @DisplayName("Próba dodania niepoprawnej pozycji do cennika (cena ujemna)")
    void testDodajNiepoprawnaPozycjeDoCennika() {
        assertFalse(recepcjonista.dodajCene(cennik, "Czyszczenie uszu", -100));
        assertEquals(3, cennik.getUslugi().size());
    }

    @Test
    @DisplayName("Próba dodania niepoprawnej pozycji do cennika (pusta nazwa)")
    void testDodajNiepoprawnaPozycjeDoCennika2() {
        assertFalse(recepcjonista.dodajCene(cennik, "", 100));
        assertEquals(3, cennik.getUslugi().size());
    }

    @Test
    @DisplayName("Poprawne przyjęcie opłaty")
    void testPrzyjmijOplate() {
        assertTrue(recepcjonista.przyjmijOplate(rachunki.get(0), 100));
        assertTrue(rachunki.get(0).czyOplacony());
    }

    @Test
    @DisplayName("Próba przyjęcia opłaty za już opłacony rachunek")
    void testPrzyjmijOplate2() {
        rachunki.get(0).setOplataGotowka(true);
        rachunki.get(0).setDataPlatnosci(LocalDateTime.now());
        assertFalse(recepcjonista.przyjmijOplate(rachunki.get(0), 100));
    }

    @Test
    @DisplayName("Próba przyjęcia opłaty za rachunek z inną kwotą")
    void testPrzyjmijOplate3() {
        assertFalse(recepcjonista.przyjmijOplate(rachunki.get(0), 50));
    }

}