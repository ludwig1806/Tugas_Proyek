package source.hiji;
import java.util.ArrayList;

// Kelas ini merupakan kelas abstract yang akan merepresentasikan koleksi umum dari kartu-kartu yang akan digunakan dalam permainan HIji ini
// Operasi basic akan disediakan dalam kelas abstract ini yaitu
// Operasi penambahan kartu ke dalam koleksi, pengeluaran kartu dari koleksi, Perhitungan jumlah kartu dalam koleksi, dan verifikasi apakah koleksi kosong atau tidak
// author : 18219053 Gina Septiyani Putri
public abstract class KoleksiKartu <TipeKartu> {
    protected ArrayList <TipeKartu> listKartu;

    public KoleksiKartu() {
        // Konstruktor untuk membuat list kartu dengan menggunakan array bertipe TipeKartu
        this.listKartu = new ArrayList<TipeKartu>();
    }

    public abstract boolean tambahKartu(TipeKartu kartu);
    public abstract TipeKartu ambilKartu(int index);

    public int jumlahKartu(){
        // Penghitungan jumlah kartu yang ada di dalam koleksi
        // Mengembalikan integer yang merupakan sebuah jumlah kartu dalam koleksi
        return this.listKartu.size();
    }

    public boolean isEmpty(){
        // Melakukan verifikasi apakah koleksi kosong atau tidak
        // Mengembalikan sebuah boolean yang bernilai true jika kolesi kosong, atau false jika koleksi tidak kosong
        if(this.listKartu.size() == 0){
            return true;
        }
        return false;
    }
}
