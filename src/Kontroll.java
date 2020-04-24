import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Kontroll {
    private List<Jatekos> jatekosok;
    private Allapot allapot;
    private BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public Kontroll() {
        jatekosok = new ArrayList<>();
        jatekosok.add(new Jatekos("A", Korong.PIROS, Korong.KEK));
        jatekosok.add(new Jatekos("B", Korong.ZOLD, Korong.SARGA));
        allapot = new Allapot(jatekosok.get(0));
    }

    public void jatszunk() throws IOException {
        System.out.println("--JATEK_ELINDULT--");
        while (vege() == null) {
            System.out.println("\"A\" játékos köre elkezdődött!");
            Operator valasztott;
            while (true) {
                //Pálya kiíratás
                System.out.println("   | 0  1  2  3 ");
                System.out.println("---+------------");
                int i = 0;
                for (Korong[] sor : allapot.getPalya()) {
                    System.out.print(" " + i++ + " |");
                    for (Korong mezo : sor) {
                        System.out.print(" " + mezo.name().substring(0, 1) + " ");
                    }
                    System.out.println();
                }
                System.out.println("Játékos heurisztikája: " + MinMaxHeur.heur(allapot));
                //Meglévő korongok
                System.out.println("Birtokolt korongok:");
                for (Map.Entry<Korong, Integer> entry : allapot.getKovetkezoJatekos().getKorongok().entrySet()) {
                    System.out.println("   " + entry.getKey() + ": " + entry.getValue());
                }
                //korong választás
                Korong valasztottSzin;
                while (true) {
                    System.out.println("Írja be a kiválasztott korong színét!" + allapot.getKovetkezoJatekos().meglevoKorongok());
                    valasztottSzin = Korong.valueOf(reader.readLine());
                    if (allapot.getKovetkezoJatekos().meglevoKorongok().contains(valasztottSzin)) {
                        break;
                    }
                    System.out.println("Nincs ilyen színű korong!");
                }
                //pozíció választás
                Integer valasztottSor;
                Integer valasztottOszlop;
                while (true) {
                    System.out.println("Kérem adja meg a pozíciót megfelelő formátumban(sor-oszlop)!");
                    String pozicio = reader.readLine();
                    valasztottSor = Integer.valueOf(pozicio.substring(0, 1));
                    valasztottOszlop = Integer.valueOf(pozicio.substring(2, 3));
                    if (valasztottSor < 4 && valasztottSor >= 0 && valasztottOszlop >= 0 && valasztottOszlop < 4) {
                        break;
                    }
                    System.out.println("Nincs ilyen pozíció!");
                }
                valasztott = null;
                for (Operator vizsgalt : allapot.getAlkalmazhatok()) {
                    if (vizsgalt.hasonlit(valasztottSor, valasztottOszlop, valasztottSzin)) {
                        valasztott = vizsgalt;
                    }
                }
                if (valasztott != null) {
                    break;
                }
                System.out.println("Nem megfelelő operátor. Kérem adjon meg másikat!");
            }
            allapot = valasztott.alkalmaz(allapot, jatekosok.get(1));
            if (vege() != null) {
                break;
            }
            //---------------------------------------------------------B
            System.out.println("\"B\" játékos köre elkezdődött!");
            System.out.println("Birtokolt korongok:");
            for (Map.Entry<Korong, Integer> entry : allapot.getKovetkezoJatekos().getKorongok().entrySet()) {
                System.out.println("   " + entry.getKey() + ": " + entry.getValue());
            }
            Operator botValasztasa = MiniMax.lepes(allapot, 3, jatekosok.get(0));
            System.out.println("A B játékos lépése:");
            System.out.println(botValasztasa);
            /*
            //--- 2 játékos mód ---
            while (true) {
                //Pálya kiíratás
                System.out.println("   | 0  1  2  3 ");
                System.out.println("---+------------");
                int i = 0;
                for (Korong[] sor : allapot.getPalya()) {
                    System.out.print(" " + i++ + " |");
                    for (Korong mezo : sor) {
                        System.out.print(" " + mezo.name().substring(0, 1) + " ");
                    }
                    System.out.println();
                }
                //Meglévő korongok
                System.out.println("Birtokolt korongok:");
                for (Map.Entry<Korong, Integer> entry : allapot.getKovetkezoJatekos().getKorongok().entrySet()) {
                    System.out.println("   " + entry.getKey() + ": " + entry.getValue());
                }
                //korong választás
                Korong valasztottSzin;
                while (true) {
                    System.out.println("Írja be a kiválasztott korong színét!" + allapot.getKovetkezoJatekos().meglevoKorongok());
                    valasztottSzin = Korong.valueOf(reader.readLine());
                    if (allapot.getKovetkezoJatekos().meglevoKorongok().contains(valasztottSzin)) {
                        break;
                    }
                    System.out.println("Nincs ilyen színű korong!");
                }
                //pozíció választás
                Integer valasztottSor;
                Integer valasztottOszlop;
                while (true) {
                    System.out.println("Kérem adja meg a pozíciót megfelelő formátumban(sor-oszlop)!");
                    String pozicio = reader.readLine();
                    valasztottSor = Integer.valueOf(pozicio.substring(0, 1));
                    valasztottOszlop = Integer.valueOf(pozicio.substring(2, 3));
                    if (valasztottSor < 4 && valasztottSor >= 0 && valasztottOszlop >= 0 && valasztottOszlop < 4) {
                        break;
                    }
                    System.out.println("Nincs ilyen pozíció!");
                }
                valasztott = null;
                for (Operator vizsgalt : allapot.getAlkalmazhatok()) {
                    if (vizsgalt.hasonlit(valasztottSor, valasztottOszlop, valasztottSzin)) {
                        valasztott = vizsgalt;
                    }
                }
                if (valasztott != null) {
                    break;
                }
                System.out.println("Nem megfelelő operátor. Kérem adjon meg másikat!");
            }*/
            allapot = botValasztasa.alkalmaz(allapot, jatekosok.get(0));
        }
        System.out.println("---------------------------------------------\nA pálya a végállásban:");
        System.out.println("   | 0  1  2  3 ");
        System.out.println("---+------------");
        int i = 0;
        for (Korong[] sor : allapot.getPalya()) {
            System.out.print(" " + i++ + " |");
            for (Korong mezo : sor) {
                System.out.print(" " + mezo.name().substring(0, 1) + " ");
            }
            System.out.println();
        }
        if (allapot.nyeroallapot()) {
            String vesztes = allapot.getKovetkezoJatekos().getNev();
            String gyoztes = "A";
            if (vesztes.equals("A")) {
                gyoztes = "B";
            }
            System.out.println(gyoztes + " játékos nyert. " + vesztes + " játékos veszített.");
        } else if (allapot.vegallas()) {
            System.out.println("Döntetlen állás következett be.");
        }
        System.out.println("--JATEK-VEGE--");
    }

    private Jatekos vege() {
        Jatekos eredmeny = null;
        if (allapot.nyeroallapot()) {
            if (allapot.getKovetkezoJatekos().equals(jatekosok.get(0))) {
                eredmeny = jatekosok.get(1);
            } else {
                eredmeny = jatekosok.get(0);
            }
        } else if (allapot.vegallas()) {
            eredmeny = Jatekos.dontetlen();
        }
        return eredmeny;
    }

}
