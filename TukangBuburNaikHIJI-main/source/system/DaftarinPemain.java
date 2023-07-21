package source.system;

import java.util.InputMismatchException;
import java.util.Scanner;

import source.tableGame.PengelolaPemain;

public class DaftarinPemain {
    private Scanner input = new Scanner(System.in);
    private PengelolaPemain manager = PengelolaPemain.getInstance();
    private BuatID generate = BuatID.getInstance();

    public boolean daftar(){
        int jumlah;
        System.out.print("MASUKKAN JUMLAH PEMAIN (2 s.d 6): ");
        try{
            jumlah = input.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("TOLONG MASUKKAN ANGKA YA BAMBANG.");
            input.next();
            return false;
        }

        while (jumlah < 2 || jumlah > 6){
            System.out.println("PEMAIN MINIMAL 2 DAN MAKSIMAL 6");
            System.out.print("MASUKKAN JUMLAH PEMAIN (2 s.d 6): ");
            jumlah = input.nextInt();
        }

        for(int i = 0; i < jumlah; i++){
            System.out.print("PEMAIN "+ String.valueOf(i+1) + ": ");
            manager.tambahPemain(new Pemain(input.next(), generate.getID()));
        }

        return true;
    }
}
