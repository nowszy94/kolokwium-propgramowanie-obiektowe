package nowak.szymon.s152948;

import nowak.szymon.s152948.kadry.Kadry;
import nowak.szymon.s152948.kadry.Pracownik;

import java.io.FileNotFoundException;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        Kadry kadry = new Kadry();
        kadry.importujZPlikuTekstowego("J:\\Desktop\\programy\\kolokwium-propgramowanie-obiektowe\\pracownicy.txt");
        System.out.println();
        kadry.pisz();

        kadry.dodajPracownika(new Pracownik("Robert", "Smith", 3000, 'M', 14));
        System.out.println();
        System.out.println();
        System.out.println();
        kadry.pisz();
    }
}
