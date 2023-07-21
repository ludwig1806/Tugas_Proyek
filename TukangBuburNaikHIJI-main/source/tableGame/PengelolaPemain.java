package source.tableGame;

import java.util.ArrayList;
import source.system.Pemain;


// kegunaan class untuk mengurus hal yang berkaitan dengan pemain

public class PengelolaPemain{
    private ArrayList <Pemain> listPemain;
    private boolean canAdd;
    private int currElem;
    private int nextElem;
    private boolean keKanan;
    private static PengelolaPemain pengelolaPemain = null;

    private PengelolaPemain(){
        this.listPemain = new ArrayList<Pemain>();
        this.canAdd = true;
        this.currElem = 0;
        this.nextElem = 0;
        this.keKanan = true;
    }

    public static PengelolaPemain getInstance(){
        if (pengelolaPemain == null){
            pengelolaPemain = new PengelolaPemain();
        }
        return pengelolaPemain;
    }

    // menambahkan pemain ke list jika bisa
    public boolean tambahPemain(Pemain pemain){
        if (this.canAdd){
            this.listPemain.add(pemain);
            if(this.listPemain.size() == 2){
                this.nextElem = 1;
            }
        }

        return this.canAdd;
    }

    // me-return status dari pemain dalam bentuk "nama : jumlah kartu"
    public String getStatusPemain(){
        String statusPemain = "";
        Pemain pemain;
        for (int i = 0; i < this.listPemain.size(); i++){
            pemain = this.listPemain.get(i);
            statusPemain += "[" + pemain.getName() + ": ";
            statusPemain += Integer.toString(pemain.jumlahKartu()) + "] ";
        }
        return statusPemain;
    }

    // me-return jumlah pemain
    public int jumlahPemain(){
        return this.listPemain.size();
    }

    // menandakan tidak boleh tambah pemain
    public void lockAdd(){
        this.canAdd = false;
    }

    // menandakan boleh tambah pemain
    public void unlockAdd(){
        this.canAdd = true;
    }

    // me-return arah dari permainan
    public String getArah(){
        if (this.keKanan){
            return "KANAN";
        } else{
            return "KIRI";
        }
    }

    // mengubah arah permainan
    public void gantiArah(){
        this.keKanan ^= true;
        if (this.keKanan){
            this.nextElem = (this.currElem + 1) % listPemain.size();
        } else{
            this.nextElem = (this.currElem - 1 + listPemain.size()) % listPemain.size();
        }
    }

    // ganti pemain
    public void rotate(){
        this.currElem = this.nextElem;
        this.rotateNextPlayer();
    }

    // ganti pemain selanjutnya
    public void rotateNextPlayer(){
        if (this.keKanan){
            this.nextElem = (this.nextElem + 1) % listPemain.size();
        } else{
            this.nextElem = (this.nextElem - 1 + listPemain.size()) % listPemain.size();
        }
    }

    // return current pemain 
    public Pemain getCurrent(){
        Pemain pemain;
        try{
            pemain = this.listPemain.get(this.currElem);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Tidak ada pemain.");
            pemain = null;
        }
        return pemain;
    }

    // return pemain selanjutnya
    public Pemain getNext(){
        return this.listPemain.get(this.nextElem % listPemain.size());
    }

    // mencari pemain yang sudah tidak memiliki kartu
    public Pemain cariPemenang(){
        for (int i = 0; i < this.listPemain.size(); i++){
            if (this.listPemain.get(i).jumlahKartu() == 0){
                return this.listPemain.get(i);
            }
        }
        return null;
    }
}