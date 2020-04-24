import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jatekos {
    private String nev;
    private Map<Korong, Integer> korongok = new HashMap<>();

    public Jatekos(String nev, Korong egyik, Korong masik) {
        this.nev = nev;
        korongok.put(egyik, 3);
        korongok.put(masik, 3);
    }

    private Jatekos(String nev) {
        this.nev = nev;
    }

    public void letesz(Korong korong) {
        korongok.replace(korong, korongok.get(korong) - 1);
    }

    public List<Korong> meglevoKorongok() {
        List<Korong> korongs = new ArrayList<>();
        for (Map.Entry<Korong, Integer> entry : korongok.entrySet()) {
            if (entry.getValue() != 0) {
                korongs.add(entry.getKey());
            }
        }
        return korongs;
    }

    public static Jatekos dontetlen() {
        return new Jatekos("Döntetlen", Korong.PIROS, Korong.KEK);
    }

    public static Jatekos masolat(Jatekos jatekos) {
        Jatekos uj = new Jatekos(jatekos.getNev());
        uj.getKorongok().clear();
        for (Map.Entry<Korong, Integer> entry : jatekos.getKorongok().entrySet()) {
            uj.getKorongok().put(entry.getKey(), entry.getValue());
        }
        return uj;
    }

    public Map<Korong, Integer> getKorongok() {
        return korongok;
    }

    public String getNev() {
        return nev;
    }
}
