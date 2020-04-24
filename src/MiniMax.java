public class MiniMax {
    private static Jatekos figyeltJatekos;

    public static Operator lepes(Allapot allapot, Integer korlat, Jatekos kovetkezo) {
        figyeltJatekos = Jatekos.masolat(allapot.getKovetkezoJatekos());
        Jatekos kov = Jatekos.masolat(kovetkezo);
        Allapot masolt = Allapot.masolat(allapot, figyeltJatekos);
        Integer max = -100000;
        Operator operator = null;
        for (Operator vizsgalt : masolt.getAlkalmazhatok()) {
            Allapot ujAllapot = vizsgalt.alkalmaz(masolt, kov);
            Integer miniMaxErtrek = minimaxErtek(ujAllapot, korlat-1, figyeltJatekos);
            if (miniMaxErtrek > max) {
                max = miniMaxErtrek;
                operator = vizsgalt;
            }
        }
        return operator;
    }

    private static Integer minimaxErtek(Allapot allapot, Integer melyseg, Jatekos kovetkezo) {
        if (allapot.nyeroallapot() || allapot.vegallas() || melyseg == 0) {
            return figyeltJatekos.equals(allapot.getKovetkezoJatekos()) ? MinMaxHeur.heur(allapot) * (-1) : MinMaxHeur.heur(allapot);
        } else if (!allapot.getKovetkezoJatekos().equals(figyeltJatekos)) {
            Integer max = -1000000;
            for (Operator vizsgalt : allapot.getAlkalmazhatok()) {
                Allapot ujAllapot = vizsgalt.alkalmaz(allapot, kovetkezo);
                Integer minimaxErtek = minimaxErtek(ujAllapot, melyseg - 1, allapot.getKovetkezoJatekos());
                if (minimaxErtek > max) {
                    max = minimaxErtek;
                }
            }
            return  max;
        } else {
            Integer min = 1000000;
            for (Operator vizsgalt : allapot.getAlkalmazhatok()) {
                Allapot ujAllapot = vizsgalt.alkalmaz(allapot, kovetkezo);
                Integer minimaxErtek = minimaxErtek(ujAllapot, melyseg - 1, allapot.getKovetkezoJatekos());
                if (minimaxErtek < min) {
                    min = minimaxErtek;
                }
            }
            return  min;
        }
    }
}
