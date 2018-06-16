package nowak.szymon.s152948.kadry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class Kadry {

    private static final int MAKSYMALNA_ILOSC_ZATRUDNIONYCH_OSOB = 100;

    private Pracownik[] pracownicy;
    private int zatrudnienie;

    public Kadry() {
        this.pracownicy = new Pracownik[100];
        this.zatrudnienie = 0;
    }

    public void dodajPracownika(Pracownik pracownik) {
        if (MAKSYMALNA_ILOSC_ZATRUDNIONYCH_OSOB == this.zatrudnienie) {
            throw new PrzepelnionaIloscPracownikow("Nie mozna dodac kolejnego pracownika. Maksymalnie mozna przechowywac " + MAKSYMALNA_ILOSC_ZATRUDNIONYCH_OSOB + " pracownikow");
        }
        this.pracownicy[this.zatrudnienie++] = pracownik;
    }

    public int getZatrudnienie() {
        return zatrudnienie;
    }

    public void importujZPlikuTekstowego(String pathToFile) throws FileNotFoundException {
        File file = new File(pathToFile);
        Scanner scanner = new Scanner(file);
        int pracownikowWPliku = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Pracownik pracownikFromFile = mapFromFileLine(line);
            dodajPracownika(pracownikFromFile);
            pracownikowWPliku++;
        }
        System.out.println("Wczytano poprawnie dane " + pracownikowWPliku + " pracownikow z pliku: " + pathToFile);
    }

    public double sredniZarobek() {
        return sredniZarobek(Stream.of(pracownicy).filter(Objects::nonNull));
    }

    public double sredniZarobek(int dzial) {
        return sredniZarobek(Stream.of(pracownicy)
                .filter(Objects::nonNull)
                .filter(pracownik -> pracownik.czyPracujeWDziale(dzial)));
    }

    public void pisz() {
        System.out.println("Pracownikow: " + this.zatrudnienie);
        for (int i = 0; i < this.zatrudnienie; i++) {
            Pracownik pracownik = this.pracownicy[i];
            System.out.println((i + 1) + ". "+ pracownik.toString());
        }

        System.out.println();
        System.out.println("Srednia placa");
        System.out.println("\tCala firma:\n\t\t" + String.format( "%.2f", this.sredniZarobek()));
        System.out.println("\tDzialy");
        int[] dzialy = dzialy();
        for (int dzial : dzialy) {
            System.out.println("\t\t" + dzial + ": " + String.format( "%.2f", sredniZarobek(dzial)));
        }
    }

    private int[] dzialy() {
        return Stream.of(pracownicy)
                .filter(Objects::nonNull)
                .mapToInt(Pracownik::getDzial)
                .distinct()
                .toArray();
    }

    private double sredniZarobek(Stream<Pracownik> stream) {
        return stream
                .mapToDouble(Pracownik::getPlaca)
                .average()
                .getAsDouble();
    }

    private Pracownik mapFromFileLine(String line) {
        String[] array = line.split(" ");
        Pracownik pracownik = null;
        try {
            pracownik = new Pracownik(array[0], array[1], Double.valueOf(array[2]), array[3].charAt(0), Integer.valueOf(array[4]));
        } catch (Exception e) {
            throw new NiepoprawnyFormatPliku("Dane w pliku zapisane sa w niepoprawnym formacie");
        }
        return pracownik;
    }
}
