import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Allapot {
    private Korong[][] palya;
    private Jatekos kovetkezoJatekos;
    private List<Operator> alkalmazhatok = new ArrayList<>();

    public Allapot(Jatekos kezdojatekos) {
        palya = new Korong[4][4];
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                palya[i][k] = Korong.URES;
            }
        }
        kovetkezoJatekos = kezdojatekos;
        operatorokBeallitasa();
    }

    public Allapot(Korong[][] palya, Jatekos kovetkezoJatekos) {
        this.palya = new Korong[4][4];
        this.palya = new Korong[4][4];
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                this.palya[i][k] = palya[i][k];
            }
        }
        this.kovetkezoJatekos = kovetkezoJatekos;
    }

    public static Allapot masolat(Allapot allapot, Jatekos kezdo) {
        Allapot uj = new Allapot(kezdo);
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                uj.getPalya()[i][k] = allapot.getPalya()[i][k];
            }
        }
        uj.getAlkalmazhatok().clear();
        for (Operator operator : allapot.getAlkalmazhatok()) {
            uj.getAlkalmazhatok().add(operator);
        }
        return uj;
    }

    public void operatorokBeallitasa() {
        alkalmazhatok.clear();
        for (Korong szin : kovetkezoJatekos.meglevoKorongok()) {
            for (int i = 0; i < 4; i++) {
                for (int k = 0; k < 4; k++) {
                    Operator operator = new Operator(i, k, szin);
                    if (operator.alkalmazhato(this)) {
                        alkalmazhatok.add(operator);
                    }
                }
            }
        }
    }

    public void lehelyez(Korong korong, int sor, int oszlop) {
        palya[sor][oszlop] = korong;
    }

    public boolean nyeroallapot() {
        boolean eredmeny = false;
        Set<Korong> egyediek = new HashSet<>();
        //Sor ellenőrzés
        for (Korong[] sor : palya) {
            for (Korong mezo : sor) {
                if (!mezo.equals(Korong.URES)) {
                    egyediek.add(mezo);
                }
            }
            if (egyediek.size() == 4) {
                eredmeny = true;
            }
            egyediek.clear();
        }
        //Oszlop ellenőrzés
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                if (!palya[k][i].equals(Korong.URES)) {
                    egyediek.add(palya[k][i]);
                }
            }
            if (egyediek.size() == 4) {
                eredmeny = true;
            }
            egyediek.clear();
        }
        //1. Átló ellenőrzés
        for (int i = 0; i < 4; i++) {
            if (!palya[i][i].equals(Korong.URES)) {
                egyediek.add(palya[i][i]);
            }
        }
        if (egyediek.size() == 4) {
            eredmeny = true;
        }
        egyediek.clear();
        //2. Átló ellenőrzés
        for (int i = 0; i < 4; i++) {
            if (!palya[i][3-i].equals(Korong.URES)) {
                egyediek.add(palya[i][3-i]);
            }
        }
        if (egyediek.size() == 4) {
            eredmeny = true;
        }
        return eredmeny;
    }

    public boolean vegallas() {
        int uresSzamlalo = 0;
        for (Korong[] sor : palya) {
            for (Korong mezo : sor) {
                if (mezo.equals(Korong.URES)) {
                    uresSzamlalo++;
                }
            }
        }
        return uresSzamlalo == 4;
    }

    public List<Operator> getAlkalmazhatok() {
        return alkalmazhatok;
    }

    public Korong[][] getPalya() {
        return palya;
    }

    public Jatekos getKovetkezoJatekos() {
        return kovetkezoJatekos;
    }

    public void setKovetkezoJatekos(Jatekos kovetkezoJatekos) {
        this.kovetkezoJatekos = kovetkezoJatekos;
    }
}
