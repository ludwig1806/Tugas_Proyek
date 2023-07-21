package source.hiji;

import source.tableGame.PengaturEfek;

public class ReverseCard extends KartuUtama{
    // Kelas ini merepresentasikan kartu yang dapat membalikkan urutan permainan
    // author : 18219053 Gina Septiyani Putri

    public ReverseCard(KartuNormal.Warna warna){
        super(warna, KartuUtama.Nilai.REVERSE);
    }

    @Override
    public void terapkanEfek(PengaturEfek ctrl){
        // Membalikkan arah permainan
        // Jika permainan hanya terdiri dari 2 pemain maka kartu ini akan sama dengan kartu skip
        ctrl.applyReverse();
    }
}
