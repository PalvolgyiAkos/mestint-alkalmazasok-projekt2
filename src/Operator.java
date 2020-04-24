public class Operator {
    private Integer sor;
    private Integer oszlop;
    private Korong szin;

    public Operator(Integer sor, Integer oszlop, Korong szin) {
        this.sor = sor;
        this.oszlop = oszlop;
        this.szin = szin;
    }

    public boolean alkalmazhato(Allapot allapot) {
        boolean eredmeny = true;
        //Üres-e?
        if (!allapot.getPalya()[sor][oszlop].equals(Korong.URES)) {
            eredmeny = false;
        } else if (vanUgyanolyanSzomszedosSzin(allapot)) {
            return false;
        }
        return eredmeny;
    }

    public Allapot alkalmaz(Allapot allapot, Jatekos kovetkezo) {
        Allapot eredmeny = new Allapot(allapot.getPalya(), kovetkezo);
        eredmeny.getPalya()[sor][oszlop] = szin;
        eredmeny.operatorokBeallitasa();
        allapot.getKovetkezoJatekos().letesz(szin);
        return eredmeny;
    }

    public boolean hasonlit(Integer sor, Integer oszlop, Korong szin) {
        return (this.sor.equals(sor)) && (this.oszlop.equals(oszlop)) && (this.szin.equals(szin));
    }

    private boolean vanUgyanolyanSzomszedosSzin(Allapot allapot) {
        boolean eredmeny = false;
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                if (allapot.getPalya()[i][k].equals(szin) &&
                        (Math.abs(sor-i) <= 1 && Math.abs(oszlop-k) <=1)) {
                    eredmeny = true;
                }
            }
        }
        return eredmeny;
    }

    @Override
    public String toString() {
        return "sor:" + sor + ", oszlop:" + oszlop + ", szin:" + szin + ".";
    }
}
