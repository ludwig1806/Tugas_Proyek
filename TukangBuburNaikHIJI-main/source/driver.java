import source.Game.Match;
import source.interpreter.Interpreter;
import source.system.DaftarinPemain;

public class driver {
    public static void main(String[] args){
        Interpreter baca = new Interpreter();
        DaftarinPemain daftar = new DaftarinPemain();
        Match permainan = Match.getInstance();

        System.out.println("\t\t\t\tGAME DIMULAI");

        permainan.init();
        while(daftar.daftar() == false);
        permainan.start();
        baca.bacaCommands();
        permainan.finish();

    }
}
