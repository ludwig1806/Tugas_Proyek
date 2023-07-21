package source.hiji;

import source.tableGame.PengaturEfek;

public abstract class KartuHiji {
        // Kelas ini menjelaskan kartu Hiji secara umum.
        // Pada permainan hiji ada kartu yang diberi angka 0-9
        // Kartu juga diberi 5 warna yang berbeda
        // Maka kartu harus memiliki angka dan warna
        // author : 18219053 Gina Septiyani Putri

        public abstract String ambilWarna();
            // Mendapatkan warna dari kartu hiji
            // Mengembalikan nilai berupa string yang merupakan warna dari kartu hiji
        
        public abstract String ambilNilai();
            // Mendapatkan nilai pada kartu hiji. Bisa berupa angka atau efek kartu
            // Mengembalikan nilai berupa string yang merupakan nilai dari kartu hiji

        public abstract void terapkanEfek(PengaturEfek ctrl);
            // Menerapkan efek pada kartu hiji
            // Menerapkan efek ke permainan hiji
        
        public abstract boolean match(KartuHiji kartu);
            // Memverifikasi apakah kartu yang dikeluarkan pemain cocok dengan kartu terakhir yang dikeluarkan pemain sebelumnya
            // Mengembalikan nilai true jika 2 kartu hiji cocok atau false jika tidak cocok
        
        @Override
        public String toString(){
            // Mendapatkan representasi dari kartu hiji
            // Mengembalikan nilai berupa string yang berisi representasi dari kartu hiji
            return this.ambilWarna() + this.ambilNilai();
        }
}

