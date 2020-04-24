import java.util.HashSet;
import java.util.Set;

public class MinMaxHeur {
    public static Integer heur(Allapot allapot) {
        Integer eredmeny = -100000;
        int jatekosSzamlalo = 0;
        int ellenfelSzamlalo = 0;
        boolean vanIsmetlodo = false;
        Set<Korong> korongok = new HashSet<>();
        for (Korong[] sor : allapot.getPalya()) {
            jatekosSzamlalo = 0;
            ellenfelSzamlalo = 0;
            for (Korong mezo : sor) {
                if (!mezo.equals(Korong.URES)) {
                    if (korongok.contains(mezo)) {
                        vanIsmetlodo = true;
                    }
                    if (allapot.getKovetkezoJatekos().meglevoKorongok().contains(mezo)) {
                        jatekosSzamlalo++;
                    } else {
                        ellenfelSzamlalo++;
                    }
                    korongok.add(mezo);
                }
            }
            int sorErtek;
            if (vanIsmetlodo) {
                sorErtek = -100000;
            } else {
                sorErtek = ertekszamol(jatekosSzamlalo, ellenfelSzamlalo);
            }
            if (sorErtek > eredmeny) {
                eredmeny = sorErtek;
            }
            vanIsmetlodo = false;
            korongok.clear();
        }
        //Oszlop ellenőrzés
        for (int i = 0; i < 4; i++) {
            jatekosSzamlalo = 0;
            ellenfelSzamlalo = 0;
            for (int k = 0; k < 4; k++) {
                if (!allapot.getPalya()[k][i].equals(Korong.URES)) {
                    if (korongok.contains(allapot.getPalya()[k][i])) {
                        vanIsmetlodo = true;
                    }
                    if (allapot.getKovetkezoJatekos().meglevoKorongok().contains(allapot.getPalya()[k][i])) {
                        jatekosSzamlalo++;
                    } else {
                        ellenfelSzamlalo++;
                    }
                    korongok.add(allapot.getPalya()[k][i]);
                }
            }
            int oszlopErtek;
            if (vanIsmetlodo) {
                oszlopErtek = -100000;
            } else {
                oszlopErtek = ertekszamol(jatekosSzamlalo, ellenfelSzamlalo);
            }
            if (oszlopErtek > eredmeny) {
                eredmeny = oszlopErtek;
            }
            vanIsmetlodo = false;
            korongok.clear();
        }
        //1. Átló ellenőrzés
        jatekosSzamlalo = 0;
        ellenfelSzamlalo = 0;
        for (int i = 0; i < 4; i++) {
            if (!allapot.getPalya()[i][i].equals(Korong.URES)) {
                if (korongok.contains(allapot.getPalya()[i][i])) {
                    vanIsmetlodo = true;
                }
                if (allapot.getKovetkezoJatekos().meglevoKorongok().contains(allapot.getPalya()[i][i])) {
                    jatekosSzamlalo++;
                } else {
                    ellenfelSzamlalo++;
                }
                korongok.add(allapot.getPalya()[i][i]);
            }
        }
        int atloErtek;
        if (vanIsmetlodo) {
            atloErtek = -100000;
        } else {
            atloErtek = ertekszamol(jatekosSzamlalo, ellenfelSzamlalo);
        }
        if (atloErtek > eredmeny) {
            eredmeny = atloErtek;
        }
        vanIsmetlodo = false;
        korongok.clear();
        //2. Átló ellenőrzés
        jatekosSzamlalo = 0;
        ellenfelSzamlalo = 0;
        for (int i = 0; i < 4; i++) {
            if (!allapot.getPalya()[i][3-i].equals(Korong.URES)) {
                if (korongok.contains(allapot.getPalya()[i][3-i])) {
                    vanIsmetlodo = true;
                }
                if (allapot.getKovetkezoJatekos().meglevoKorongok().contains(allapot.getPalya()[i][3-i])) {
                    jatekosSzamlalo++;
                } else {
                    ellenfelSzamlalo++;
                }
                korongok.add(allapot.getPalya()[i][3-i]);
            }
        }
        if (vanIsmetlodo) {
            atloErtek = -100000;
        } else {
            atloErtek = ertekszamol(jatekosSzamlalo, ellenfelSzamlalo);
        }
        if (atloErtek > eredmeny) {
            eredmeny = atloErtek;
        }
        vanIsmetlodo = false;
        korongok.clear();
        return eredmeny;
    }

    private static int ertekszamol(int jatekosSzamlalo, int ellenfelSzamlalo) {
        int eredmeny = jatekosSzamlalo + 2*ellenfelSzamlalo;
        if (jatekosSzamlalo == 0) {
            eredmeny--;
        }
        if (ellenfelSzamlalo == 0) {
            eredmeny-=2;
        }
        eredmeny*=jatekosSzamlalo+ellenfelSzamlalo;
        if (jatekosSzamlalo > 1) {
            eredmeny*=2;
        }
        if (ellenfelSzamlalo > 1) {
            eredmeny*=3;
        }
        return eredmeny;
    }
}
