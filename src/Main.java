import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Kontroll jatek = new Kontroll();
        try {
            jatek.jatszunk();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
