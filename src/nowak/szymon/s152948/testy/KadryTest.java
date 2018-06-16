package nowak.szymon.s152948.testy;

import nowak.szymon.s152948.kadry.Kadry;
import nowak.szymon.s152948.kadry.Pracownik;

import java.io.FileNotFoundException;

public class KadryTest {

    public static void main(String[] args) throws Exception {
        should_add_new_pracownik_to_Kadry();
        should_add_multiple_pracownik_to_Kadry();
        should_throw_an_exception_when_trying_to_add_101_pracownik();
        should_import_from_file_without_error();
        should_throw_an_Exception_while_trying_to_import_from_file_with_bad_format();
        should_return_correct_average_of_placa();
        should_return_correct_average_of_placa_for_given_dzial();

        run_pisz_method();
    }

    private static void should_add_new_pracownik_to_Kadry() {
        Kadry kadry = new Kadry();
        Pracownik pracownik = new Pracownik("Szymon", "Nowak", 1000.0, 'm', 10);

        kadry.dodajPracownika(pracownik);

        boolean result = kadry.getZatrudnienie() == 1;

        check(result, "should_add_new_pracownik_to_Kadry");
    }

    private static void should_add_multiple_pracownik_to_Kadry() {
        Kadry kadry = new Kadry();
        Pracownik pracownik = new Pracownik("Szymon", "Nowak", 1000.0, 'm', 10);
        Pracownik pracownik2 = new Pracownik("Anna", "Kowalska", 2000.0, 'k', 12);

        kadry.dodajPracownika(pracownik);
        kadry.dodajPracownika(pracownik2);

        boolean result = kadry.getZatrudnienie() == 2;

        check(result, "should_add_multiple_pracownik_to_Kadry");
    }

    private static void should_throw_an_exception_when_trying_to_add_101_pracownik() {
        Kadry kadry = new Kadry();
        for (int i = 0; i < 100; i++) {
            kadry.dodajPracownika(new Pracownik("Szymon", "Nowak", 1000.0, 'm', 10));
        }
        boolean result = false;
        try {
            //dodaje 101 pracownika
            kadry.dodajPracownika(new Pracownik("Szymon", "Nowak", 1000.0, 'm', 10));
        } catch (RuntimeException e) {
            result = true; //bo powinien rzucic wyjatkiem
        }
        check(result, "should_throw_an_exception_when_trying_to_add_101_pracownik");
    }

    private static void should_import_from_file_without_error() throws FileNotFoundException {
        String pathToFile = "J:\\Desktop\\programy\\kolokwium-propgramowanie-obiektowe\\pracownicy.txt";
        Kadry kadry = new Kadry();

        kadry.importujZPlikuTekstowego(pathToFile);

        boolean result = kadry.getZatrudnienie() == 7;
        check(result, "should_import_from_file_without_error");
    }

    private static void should_throw_an_Exception_while_trying_to_import_from_file_with_bad_format() throws Exception {
        String pathToFile = "J:\\Desktop\\programy\\kolokwium-propgramowanie-obiektowe\\pracownicy-broken.txt";
        Kadry kadry = new Kadry();

        boolean result = false;
        try {
            kadry.importujZPlikuTekstowego(pathToFile);
        } catch (Exception e) {
            result = true;
        }
        check(result, "should_throw_an_Exception_while_trying_to_import_from_file_with_bad_format");
    }

    private static void should_return_correct_average_of_placa() {
        Kadry kadry = new Kadry();
        Pracownik pracownik = new Pracownik("Szymon", "Nowak", 1000.0, 'm', 10);
        Pracownik pracownik2 = new Pracownik("Anna", "Kowalska", 2000.0, 'k', 12);

        kadry.dodajPracownika(pracownik);
        kadry.dodajPracownika(pracownik2);

        double sredniZarobek = kadry.sredniZarobek();

        boolean result = sredniZarobek == 1500.0;
        check(result, "should_return_correct_average_of_placa");
    }

    private static void should_return_correct_average_of_placa_for_given_dzial() {
        Kadry kadry = new Kadry();
        Pracownik pracownik = new Pracownik("Szymon", "Nowak", 1000.0, 'm', 10);
        Pracownik pracownik2 = new Pracownik("Anna", "Kowalska", 2000.0, 'k', 12);
        Pracownik pracownik3 = new Pracownik("Andrzej", "Duda", 3000.0, 'm', 12);

        kadry.dodajPracownika(pracownik);
        kadry.dodajPracownika(pracownik2);
        kadry.dodajPracownika(pracownik3);

        double sredniZarobek = kadry.sredniZarobek(12);

        boolean result = sredniZarobek == 2500.0;
        check(result, "should_return_correct_average_of_placa_for_given_dzial");
    }

    private static void run_pisz_method() {
        Kadry kadry = new Kadry();

        Pracownik pracownik = new Pracownik("Szymon", "Nowak", 1000.0, 'm', 10);
        Pracownik pracownik2 = new Pracownik("Anna", "Kowalska", 2000.0, 'k', 12);
        Pracownik pracownik3 = new Pracownik("Andrzej", "Duda", 3000.0, 'm', 12);

        kadry.dodajPracownika(pracownik);
        kadry.dodajPracownika(pracownik2);
        kadry.dodajPracownika(pracownik3);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        kadry.pisz();
    }

    private static void check(boolean result, String testName) {
        if (!result) {
            throw new RuntimeException("Assertion error in " + testName);
        }
        System.out.println(testName + ": OK");
    }
}
