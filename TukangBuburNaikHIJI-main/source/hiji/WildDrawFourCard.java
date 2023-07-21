package source.hiji;

import source.tableGame.PengaturEfek;

public class WildDrawFourCard extends WildCard{
    // Kelas ini merepresentasikan Wild draw four card
    // author : 18219053 Gina Septiyani Putri
    private Nilai nilai;

    public WildDrawFourCard(){
        super();
        this.nilai = Nilai.WILDDRAWFOUR;
    }

    @Override
    public String ambilNilai(){
        // Mendapatkan nilai dari kartu wild draw four
        return this.nilai.toString();
    }

    @Override
    public void terapkanEfek(PengaturEfek ctrl){
        ctrl.applyDrawFour();
        super.setWarna(ctrl.applyWild());
    }

}
