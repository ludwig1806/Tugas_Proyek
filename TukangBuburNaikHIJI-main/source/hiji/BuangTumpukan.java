package source.hiji;
import java.lang.UnsupportedOperationException;
import java.util.ArrayList;

// Kelas ini merepresentasikan tumpukan kartu yang sudah dimainkan
// author : 18219053 Gina Septiyani Putri

public class BuangTumpukan extends KoleksiKartu <KartuHiji>{
    
    public BuangTumpukan(){   
        //
     }

    @Override
    public boolean tambahKartu(KartuHiji kartu){
        // Menambahkan kartu yang sudah dikeluarkan pemain keatas tumpukan
        if(this.getTopCard()==null){
            return false;
        }
        if(!this.getTopCard().match(kartu)){
            return false;
        }
        return this.listKartu.add(kartu);
    }

    public void inisialisasi(KartuHiji kartu){
        // Proses pembuangan kartu pertama ke tumpukan dimulai
        if(this.listKartu.size()==0){
            this.listKartu.add(kartu);
        }
    }

    @Override
    public KartuHiji ambilKartu(int index){
        throw new UnsupportedOperationException("Proses tidak diperbolehkan");
    }

    public KartuHiji getTopCard(){
        // Fungsi ini digunakan untuk mendapatkan kartu terakhir yang di buang dari permainan
        // mengembalikan nilai berupa kartu paling atas yang ada di buang tumpukan
        KartuHiji kartuTerakhir;

        try{
            kartuTerakhir = listKartu.get(listKartu.size()-1);
        }
        catch(IndexOutOfBoundsException e){
            kartuTerakhir = null;
        }
        return kartuTerakhir;
    }

    public ArrayList<KartuHiji> takeCardsBack(){
        // Method ini digunakan untuk mengambil kartu yang ada di buang tumpukan dan membuat deck yang baru.
        // Hanya kartu yang terakhir yang dibuang dalam operasi ini
        // Mengembalikan nilai berupa list semua kartu yang ada di buang tumpukan kecuali kartu yang paling atas
        ArrayList<KartuHiji> newDeck = new ArrayList<KartuHiji>();

        while(this.listKartu.size() > 1){
            newDeck.add(this.listKartu.remove(0));
        }
        return newDeck;
    }
}

