package source.hiji;

import java.util.Random;

public class Deck extends KoleksiKartu <KartuHiji>{
    // Kelas ini merepresentasikan deck dari kartu hiji
    // author : 18219053 Gina Septiyani Putri

    private final static Random random = new Random();
    public final static int LENGTH = 108;

    public Deck(){
        for(KartuNormal.Warna warna : KartuNormal.Warna.values()){
            for(int j = 0; j < 2 ; j++){
                for(KartuNormal.Nilai nilai : KartuNormal.Nilai.values()){
                    if(nilai.equals(KartuNormal.Nilai.ZERO) && j == 1){
                        continue;
                    }
                    else{
                        listKartu.add(new KartuNormal(warna, nilai));
                    }
                }
            }
        }

        for(KartuNormal.Warna warna : KartuNormal.Warna.values()){
            for(int j = 0; j < 2; j++){
                listKartu.add(new SkipCard(warna));
                listKartu.add(new ReverseCard(warna));
                listKartu.add(new DrawTwoCard(warna));
            }
        }

        for(int j = 0; j < 4; j++){
            listKartu.add(new WildCard());
            listKartu.add(new WildDrawFourCard());
        }
    }

    public void acak(){
        // Mengacak kartu di deck
        for(int i = listKartu.size()-1; i > 0; --i){
            int idx = random.nextInt(i);
            KartuHiji h = listKartu.get(idx);
            listKartu.set(idx, listKartu.get(i));
            listKartu.set(i, h);
        }
    }

    public KartuHiji get(final int i){
        // Mendapatkan kartu dari deck
        try{
            return this.listKartu.get(i);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public String toString() {
        // Membuat semua string kartu yang ada di dalam deck
        final StringBuilder a = new StringBuilder();
        for(KartuHiji h : listKartu){
            a.append(h.toString() + " ");
        }
        return a.toString();
    }

    @Override
    public boolean tambahKartu(KartuHiji kartu){
        // Menambahkan kartu kedalam deck
        return this.listKartu.add(kartu);
    }
    
    @Override
    public KartuHiji ambilKartu(int idx){
        // Mengambil kartu dari deck
        try{
            return this.listKartu.remove(idx);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
}
