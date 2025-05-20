import java.time.LocalDateTime;

public class Recepcjonista {
    public boolean dodajCene(Cennik cennik, String nazwa, double cena) {
        if (cena < 0 || nazwa == null || nazwa.isEmpty()) {
            return false;
        }
        try {
            cennik.dodajUsluge(new Usluga(nazwa, cena));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean przyjmijOplate(Rachunek rachunek, double kwota) {
        if (rachunek.czyOplacony()) {
            return false;
        }
        if (kwota != rachunek.getKwota()) { // ensure that the amount paid is equal to the amount due to catch any mistakes
            return false;
        }
        rachunek.setOplataGotowka(true);
        rachunek.setDataPlatnosci(LocalDateTime.now());
        return true;
    }
}
