package nowak.szymon.s152948.kadry;

public class Pracownik {
    private String imie;
    private String nazwisko;
    private double placa;
    private char plec;
    private int dzial;

    public Pracownik(String imie, String nazwisko, double placa, char plec, int dzial) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.placa = placa;
        this.plec = plec;
        this.dzial = dzial;
    }

    public boolean czyPracujeWDziale(int dzial) {
        return this.dzial == dzial;
    }

    public double getPlaca() {
        return placa;
    }

    public int getDzial() {
        return dzial;
    }

    public String toString() {
        return imie + "\t" + nazwisko + "\t" + plec + "\t" + dzial + "\t" + placa + " zl";
    }
}
