package source.tableGame;

import java.util.ArrayList;
import source.hiji.*;

/*
* kegunaan class untuk membuat meja dari permainan
* termasuk discard pile, deck, pemain dan tangannya, 
* dan state dari permainan
*/

public class Meja{
    private BuangTumpukan tumpukan;
    private Deck deck;
    private static Meja meja = null;

    private Meja(){
        this.tumpukan = new BuangTumpukan();
        this.deck = new Deck();
    }

    public static Meja getInstance(){
        if (meja == null){
            meja = new Meja();
        }
        return meja;
    }

    /* menyiapkan meja, jika ada kartu di discard pile,
     * kartu akan dimasukkan ke deck dan diacak
     * kemudian satu kartu disimpan ke discard pile
     */
    public void prepareTable(){
        if (this.tumpukan.jumlahKartu() != 0){
            ArrayList<KartuHiji> list = tumpukan.takeCardsBack();
            for (int i = 0; i < list.size(); i++){
                this.deck.tambahKartu(list.remove(0));
            }
        }
        deck.acak();

        KartuHiji kartu = this.deck.ambilKartu(0);
        while (kartu.ambilWarna().equals("BLACK") || 
                kartu.ambilNilai().equals("SKIP") || 
                kartu.ambilNilai().equals("REVERSE") || 
                kartu.ambilNilai().equals("DRAWTWO")){
            this.deck.tambahKartu(kartu);
            kartu = this.deck.ambilKartu(0);
        }

        this.tumpukan.inisialisasi(kartu);
    }

    // me-return kartu yang terakhir dimainkan
    public KartuHiji tampilkanKartuTerakhir(){
        return this.tumpukan.getTopCard();
    }

    //menyimpan kartu pada pile
    public boolean pushKartu(KartuHiji kartu){
        if (kartu == null){
            return false;
        }
        if (this.tumpukan.getTopCard().match(kartu)){
            this.tumpukan.tambahKartu(kartu);
            return true;
        } else{
            return false;
        }
    }

    // mengambil kartu dari deck, jika deck kosong,kartu pada discard pile disimpan pada deck
    public KartuHiji pullKartu(){
        KartuHiji kartu = null;
        if (this.deck.isEmpty()){
            ArrayList <KartuHiji> list = tumpukan.takeCardsBack();
            if (list.size() != 0){
                for (int i = 0; i < list.size(); i++){
                    kartu = list.remove(0);
                }

                if (kartu.ambilNilai().equals("WILD+4")){
                    this.deck.tambahKartu(new WildDrawFourCard());
                } else if(kartu.ambilNilai().equals("WILD")){
                    this.deck.tambahKartu(new WildCard());
                } else{
                    this.deck.tambahKartu(kartu);
                }

                this.deck.acak();
            } else{
                System.out.println("Tidak ada kartu yang bisa diambil.");
                return null;
            }
        }
        return this.deck.ambilKartu(0);
    }

    // me-return jumlah kartu pada deck
    public int jumlahKartuPadaDeck(){
        return this.deck.jumlahKartu();
    }
}

