import Entities.Cavalo;
import Entities.Tabuleiro;

public class Main {
    public static void main(String[] args) {
        Cavalo cavalo = new Cavalo(0,5);
        Tabuleiro n = new Tabuleiro(cavalo);

        System.out.println(n);
    }
}
