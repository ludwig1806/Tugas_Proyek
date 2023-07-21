package source.hiji;

public class Hand extends KoleksiKartu <KartuHiji>{
    // Kelas ini merepresentasikan kartu-kartu yang ada ditangan pemain
    // Pada kelas ini ada beberapa operasi dasar yaitu tambah, dapatkan, dan lihat kartu
    // author : 18219053 Gina Septiyani Putri

    public Hand(){
        super();
    }

    @Override
    public boolean tambahKartu(KartuHiji kartu){
        // Menambahkan kartu yang ada ditangan pemain
        return this.listKartu.add(kartu);
    }

    @Override
    public KartuHiji ambilKartu(int Idx){
        // Menghapus kartu dari tangan pemain
        // idx adalah parameter kartu yang diinginkan
        // Mengembalikan nilai berupa kartu hiji yang dihapus
        try{
            return this.listKartu.remove(Idx);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public String tampilkanKartu(int idx){
        // Menampilkan kartu yang ada ditangan pemain
        // idx adalah parameter kartu yang diinginkan
        try{
            return this.listKartu.get(idx).toString();
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
    }
}
