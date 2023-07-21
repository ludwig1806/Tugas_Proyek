package source.hiji;

import source.tableGame.PengaturEfek;

public class WildCard extends KartuHiji{
    // Kelas ini merepresentasikan wild card
    // author : 18219053 Gina Septiyani Putri


    public enum Nilai {
        WILD            { public String toString() { return "WILD";}},
        WILDDRAWFOUR    { public String toString() { return "WILD+4";}}
    }

    private final String warna = "BLACK";
    private final Nilai nilai;
    private boolean used;
    private String effectiveColor;

    public WildCard(){
        this.nilai = Nilai.WILD;
        this.used = false;
    }

    @Override
    public String ambilWarna(){
        // Mengambil warna dari kartu. Jika kartu sudah dimainkan maka akan ditampilkan warna baru yang diinginkan pemain
        if(!this.used){
            return this.warna;
        }
        return this.effectiveColor;
    }

    @Override
    public String ambilNilai(){
        // Mengambil nilai dari WildCard
        return this.nilai.name();
    }

    @Override
    public void terapkanEfek(PengaturEfek ctrl){
        // Merapkan efek wild card dalam permainan
        // Mengubah warna dan pemain berikutnya akan kehilangan giliran untuk bermain
        this.setWarna(ctrl.applyWild());
    }

    @Override
    public boolean match(KartuHiji kartu){
        // Hanya cock dengan kaertu lain jika warnanya berbeda dari black
        if(this.ambilWarna().equals("BLACK")){
            return false;
        }
        else if(kartu.ambilWarna().equals("BLACK")){
            return true;
        }
        return (kartu.ambilWarna().equals(this.ambilWarna()));
    }

    public boolean setWarna(String warna){
        // Menetapkan warna dari wild card
        if(this.used){
            return false;
        }
        if(!KartuNormal.validasiWarna(warna)){
            throw new IllegalArgumentException();
        }
        this.effectiveColor = warna;
        this.used = true;
        return true;
    }

    @Override
    public String toString(){
        // Merepresentasikan wild card
        // akan mengembalikan nilai wild card jika kartunya belum digunakan
        // jika sudah digunakan akan mengembalikan nilai berupa warna yang dipilih dan jenisnya
        if(this.used){
            return super.toString();
        }
        return this.ambilNilai();
    }
}
