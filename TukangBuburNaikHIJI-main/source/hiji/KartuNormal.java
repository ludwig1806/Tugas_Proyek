package source.hiji;

import source.tableGame.PengaturEfek;

public class KartuNormal extends KartuHiji{
    // Kelas ini merepresentasikan satu kartu pada deck permainan hiji
    // author : 18219053 Gina Septiyani Putri

    public enum Warna {
        // enum ini merepresentasikan tipe 
        BLUE    { public String toString() { return "BLUE"; }},
        RED     { public String toString() { return "RED"; }},
        GREEN   { public String toString() { return "GREEN"; }},
        YELLOW  { public String toString() { return "YELLOW"; }},
    }

    public enum Nilai {
        ZERO    { public String toString() { return "0";}},
        ONE     { public String toString() { return "1";}},
        TWO     { public String toString() { return "2";}},
        THREE   { public String toString() { return "3";}},
        FOUR    { public String toString() { return "4";}},
        FIVE    { public String toString() { return "5";}},
        SIX     { public String toString() { return "6";}},
        SEVEN   { public String toString() { return "7";}},
        EIGHT   { public String toString() { return "8";}},
        NINE    { public String toString() { return "9";}}
    }

    private final Warna warna;
    private final Nilai nilai;

    public KartuNormal(final Warna warna, final Nilai nilai){
        this.warna = warna;
        this.nilai = nilai;
    }
    
    @Override
    public String ambilWarna(){
        return this.warna.name();
    }

    @Override
    public String ambilNilai(){
        return this.nilai.toString();
    }

    @Override
    public void terapkanEfek(PengaturEfek ctrl){

    }

    @Override
    public boolean match(KartuHiji kartu){
        if(kartu.ambilWarna().equals("BLACK")){
            return true;
        }
        return (kartu.ambilWarna().equals(this.ambilWarna()) ||
                kartu.ambilNilai().equals(this.ambilNilai()));
    }

    public static boolean validasiWarna(String stringToColor) {
        // Melakukan verifikasi apakah input warna berupa string yang dimasukkan pemain sudah sesuai atau tidak
        // Mengembalikan nilai true jika warna yang dimasukkan telah sesuai dan false jika sebaliknya
        for(Warna warna : Warna.values()){
            if(warna.name().equals(stringToColor.toUpperCase())){
                return true;
            }
        }
        return false;
    }
}
