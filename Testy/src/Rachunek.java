import java.time.LocalDateTime;
public class Rachunek {
    private final Usluga[] uslugi;
    private final LocalDateTime dataWystawienia;
    private LocalDateTime dataPlatnosci;
    private boolean OplataGotowka;
    private final double kwota;
    public Rachunek(Usluga[] uslugi, LocalDateTime dataWystawienia) {
        this.uslugi = uslugi;
        this.dataWystawienia = dataWystawienia;
        this.dataPlatnosci = null;
        this.OplataGotowka = false;
        this.kwota = obliczKwote();
    }

    private double obliczKwote() {
        double suma = 0;
        for (Usluga usluga : uslugi) {
            suma += usluga.getCena();
        }
        return suma;
    }

    public Usluga[] getUslugi() {
        return uslugi;
    }
    public LocalDateTime getDataWystawienia() {
        return dataWystawienia;
    }
    public LocalDateTime getDataPlatnosci() {
        return dataPlatnosci;
    }
    public void setDataPlatnosci(LocalDateTime dataPlatnosci) {
        this.dataPlatnosci = dataPlatnosci;
    }
    public boolean czyOplacony() {
        return dataPlatnosci != null;
    }
    public boolean czyOplataGotowka() {
        return OplataGotowka;
    }
    public void setOplataGotowka(boolean OplataGotowka) {
        this.OplataGotowka = OplataGotowka;
    }

    public double getKwota() {
        return kwota;
    }


}
