package source.tableGame;

import source.hiji.KartuNormal;
import source.hiji.KartuHiji;
import source.system.Pemain;

// kegunaan class adalah untuk membuat efek dari Uno Card dalam game 

public class PengaturEfek{
    private Meja meja;
    private PengelolaPemain playerControl;
    private String wildColor;
    private int count; 

    public PengaturEfek(Meja meja, PengelolaPemain players){
        this.meja = meja;
        this.playerControl = players;
        this.wildColor = null;
        this.count = 1;
    }

    //memeriksa input untuk wild card apakah warna valid dan kapital
    public boolean setWildColor(String warna){
        if (KartuNormal.validasiWarna(warna)){
            this.wildColor = warna;
            return true;
        }
        return false;
    }

    //me-return warna terakhir
    public String getWildColor(){
        return this.wildColor;
    }

    //pemain selanjutnya bisa draw two dan tidak mendapat turn atau menambahkan kartu draw two
    public void applyDrawTwo(){
        KartuHiji kartu;
        int i = 0;
        Pemain currentPemain = this.playerControl.getCurrent();
        Pemain nextPemain = this.playerControl.getNext();
        while (i < nextPemain.jumlahKartu() && !nextPemain.searchHand(i).contains("+2")){
            i++;
        }
        try{
            if (nextPemain.searchHand(i).contains("+2")){
                while(nextPemain.searchHand(i).contains("+2")){
                    nextPemain.mainkanKartu(nextPemain.searchHand(i)).ambilNilai().equals("+2");
                    nextPemain = currentPemain;
                    this.count++;
                }
                for (i = 0; i < this.count*2; i++){
                    kartu = this.meja.pullKartu();
                    nextPemain.takeCard(kartu);
                }
            } else{
                for (i = 0; i < this.count*2; i++){
                    kartu = this.meja.pullKartu();
                    nextPemain.takeCard(kartu);
                }
                this.playerControl.rotateNextPlayer();
            }
        }
        catch(NullPointerException e){
            for (i = 0; i < this.count*2; i++){
                kartu = this.meja.pullKartu();
                nextPemain.takeCard(kartu);
            }
            this.playerControl.rotateNextPlayer();
        }
        this.count = 1;
    }
    //pemain selanjutnya harus draw four dan tidak mendapat turn
    public void applyDrawFour(){
        KartuHiji kartu;
        for (int i = 0 ; i < 4; i++){
            kartu = this.meja.pullKartu();
            this.playerControl.getNext().takeCard(kartu);
        }
        this.playerControl.rotateNextPlayer();
    }

    //melakukan reverse
    public void applyReverse(){
        this.playerControl.gantiArah();
        if (this.playerControl.jumlahPemain() == 2){
            this.playerControl.rotateNextPlayer();
        }
    }

    //skip pemain selanjutnya
    public void applySkip(){
        System.out.println(playerControl.getCurrent().getName());
        this.playerControl.rotateNextPlayer();
    }

    //me-return warna baru dari wild card
    public String applyWild(){
        return this.getWildColor();
    }
}
