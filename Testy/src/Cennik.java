import java.util.ArrayList;
public class Cennik {
    private final ArrayList<Usluga> uslugi = new ArrayList<Usluga>();
    public void dodajUsluge(Usluga usluga) {
        if (znajdzUsluge(usluga.getNazwa()) == null) {
            uslugi.add(usluga);
        }
        else {
            throw new IllegalArgumentException("Usluga o podanej nazwie juz istnieje w cenniku");
        }
    }
    public void usunUsluge(String nazwa) {
        Usluga usluga = znajdzUsluge(nazwa);
        if (usluga != null) {
            uslugi.remove(usluga);
        }
        else {
            throw new IllegalArgumentException("Usluga o podanej nazwie nie istnieje w cenniku");
        }
    }
    public ArrayList<Usluga> getUslugi() {
        return uslugi;
    }
    public Usluga znajdzUsluge(String nazwa) {
        for (Usluga usluga : uslugi) {
            if (usluga.getNazwa().equals(nazwa)) {
                return usluga;
            }
        }
        return null;
    }
}
