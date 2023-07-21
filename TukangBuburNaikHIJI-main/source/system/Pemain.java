package source.system;

import source.hiji.Hand;
import source.hiji.KartuHiji;


public class Pemain {
    private IDPemain<Integer> id_pemain;
    private Hand hand_pemain;
    private String nama_pemain;
    
    public Pemain(String nama, int id_pemain){
        this.id_pemain = new IDPemain<Integer> (id_pemain);
        this.hand_pemain = new Hand();
        this.nama_pemain = nama;
    }

    public Pemain(IDPemain<Integer> id_pemain){
        this("Pemain ",id_pemain.getID());
        this.nama_pemain += this.id_pemain.getID();
    }

    public Pemain(String nama, IDPemain<Integer> id_pemain){
        this(nama, id_pemain.getID());
    }

    /**
     * @return ID pemain
     */
    public int getID(){
        return this.id_pemain.getID();
    }

    /**
     * @return nama pemain
     */
    public String getName(){
        return this.nama_pemain;
    }

    /**
     * @return banyak kartu di tangan
     */
    public int jumlahKartu(){
        return hand_pemain.jumlahKartu();
    }

    /**
     * Pemain mengambil kartu
     * @return true apabila pemain dapat mengambil kartu
     */
    public boolean takeCard(KartuHiji kartu){
        return this.hand_pemain.tambahKartu(kartu);
    }

    public KartuHiji mainkanKartu(String namaKartu){
        int i = 0;
        while (i != this.jumlahKartu()){
            if (this.hand_pemain.tampilkanKartu(i).equals(namaKartu)){
                return this.hand_pemain.ambilKartu(i);
            }
            i++;
        }
        return null;
    }

    /**
     * Memainkan kartu ke i
     * @param i index kartu
     * @return kartu ke-i
     */
    public KartuHiji mainkanKartu(int i){
        return this.hand_pemain.ambilKartu(i);
    }

    /**
     * Print semua kartu di tangan
     */
    public void printHand(){
        int nKartu = this.jumlahKartu();
        String print = "";
        int i;
        if (nKartu == 0){
            System.out.println("TANGAN KOSONG");
        }
        else{
            for(i = 0; i < nKartu; i++){
                print += ("[" + this.hand_pemain.tampilkanKartu(i) + "]");
            }
        }

        if (print.length() > 80){
            for (i = 0; i < print.length()/80; i++){
                System.out.println(print.substring(i*80,i*80+80));
            }
            System.out.println(print.substring(i*80));

        }
        else{
            System.out.println(print);
        }
    }
    /**
     * Mencari indeks kartu spesifik di hand pemain
     * @return string namakartu yang dicari
     */
    public String searchHand(int idx){
        return this.hand_pemain.tampilkanKartu(idx);
    }

    public String searchHand(){
        String search = "";
        if (this.jumlahKartu() != 0){
            for (int i = 0; i < this.jumlahKartu(); i++){
                search += (this.hand_pemain.tampilkanKartu(i) + " ");
            }
        }
        return search;
    }

    /**
     * Kosongkan Hand
     */
    public void clearHand(){
        while(!this.hand_pemain.isEmpty()){
            this.hand_pemain.ambilKartu(0);
        }
    }







}
