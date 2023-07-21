package source.hiji;

import source.tableGame.PengaturEfek;

public abstract class KartuUtama extends KartuHiji{
    
    public enum Warna {
        BLUE    { public String toString() { return "BLUE"; }},
        RED     { public String toString() { return "RED"; }},
        GREEN   { public String toString() { return "GREEN"; }},
        YELLOW  { public String toString() { return "YELLOW"; }},
    }

    public enum Nilai {
        SKIP    { public String toString() { return "SKIP"; }},
        REVERSE { public String toString() { return "REVERSE"; }},
        DRAWTWO { public String toString() { return "+2"; }},
    }

    private final KartuNormal.Warna warna;
    private final Nilai nilai;

    public KartuUtama(final KartuNormal.Warna warna, final Nilai nilai){
        this.warna = warna;
        this.nilai = nilai;
    }

    @Override
    public String ambilWarna(){
        // Mengambil warna dari kartu.
        return this.warna.name();
    }

    @Override
    public String ambilNilai(){
        // Mendapatkan nilai dari kartu
        return this.nilai.toString();
    }

    @Override
    public abstract void terapkanEfek(PengaturEfek ctrl);
    // Menerapkan efek dari kartu pada permainan

    @Override
    public boolean match(KartuHiji kartu){
        // Mengecek apakah kartu cocok atau tidak
        if(kartu.ambilWarna().equals("BLACK")){
            return true;
        }
        return (kartu.ambilWarna().equals(this.ambilWarna()) || kartu.ambilNilai().equals(this.ambilNilai()));
    }
}
