package source.hiji;

import source.tableGame.PengaturEfek;

public class SkipCard extends KartuUtama{
    // Kelas ini merepresentasikan  kartu skip
    // author : 18219053 Gina Septiyani Putri

    public SkipCard(KartuNormal.Warna warna){
        super(warna, KartuUtama.Nilai.SKIP);
    }

    @Override
    public void terapkanEfek(PengaturEfek ctrl){
        // Melewati pemain yang setelahnya agar tidak bermain
        ctrl.applySkip();
    }
    
}
