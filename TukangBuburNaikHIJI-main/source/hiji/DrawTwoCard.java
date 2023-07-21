package source.hiji;

import source.tableGame.PengaturEfek;

public class DrawTwoCard extends KartuUtama{
    public DrawTwoCard(KartuNormal.Warna warna){
        super(warna, KartuUtama.Nilai.DRAWTWO);
    }

    @Override
    public void terapkanEfek(PengaturEfek ctrl){
        // Kartu ini akan membuat pemain selanjutnya mengambil 2 kartu
        // Pemain yang terkena kartu efek akan kehilangan gilirannya untuk bermain
        ctrl.applyDrawTwo();
    }
}
