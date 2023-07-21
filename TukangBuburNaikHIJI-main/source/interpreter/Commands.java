package source.interpreter;
import source.Game.Match;
import source.hiji.KartuNormal;
/**
 * Kelas ini berfungsi untuk mengimplementasi commands yang digunakan oleh player. 
 * commands akan dipanggil oleh kelas interpreter saat command tersebut termasuk ke input standar yang telah ditentukan
 */
public class Commands {
	private Match match = Match.getInstance();
		
	/**
	 * mainKartu merepresentasikan hal yang dijalankan saat player mencoba memainkan kartu di tumpukan discard. 
	 * Jika kartu adalah kartu normal, maka kartu tersebut harus diterima jika warna atau angkanya sama. jika kartu adalah kartu special,
	 * maka kartu yang dimainkan bergantung pada efek yang dihasilkan dari kartu tersebut.
	 * @param Name dari kartu yang harus dimainkan.
	 * @return Mereturn nilai boolean. jika memungkinkan mereturn true, jika tidak maka false.
	 */
	public boolean mainKartu(String[] fields){ // PlayCard, fields
		if (fields[1].startsWith("WILD") && (fields.length < 3 || !KartuNormal.validasiWarna(fields[2])))
			return false;
		
		if(this.match.pemainMainKartu(fields[1])){	 // playerPlayCard	
			if(fields[1].startsWith("WILD")){				 
				if(this.match.terapkanEfek(fields[2]) == false) // applyEffect
					return false;
				
			} else{
				this.match.terapkanEfek(); // applyEffect
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Permainan selesai saat satu player memiliki tangan kosong. 
	 * Method ini memverifikasi jika ada tangan yang kosong.
	 * @return jika tangan kosong return true, jika tidak return false.
	 */
	public boolean verifikasiGameSelesai(){ // verifyEndGame
		return match.kartuHabis(); // isEmptyHand
	}
	
	/**
	 * Saat pemain meminta kartu baru dari deck, interpreter akan memanggil method ini. 
	 * Method ini berfungsi untuk mengimlementasikan pemain untuk mengambil kartu dari sebuah tabel dan menambahkannya ke tangan. 
	 * Method ini juga menunjukkan state dari tangan.
	 */
	public void draw(){
		match.pemainAmbilKartu();	// playerTakeCard
		System.out.println("\n---------------------------------------"
				+ "-----------------------------------------");
		System.out.println("KARTU BARU DITAMBAHKAN DI AKHIR DARI LIST.");
		match.showHandStatus(); // showHandStatus
	}
	
	/**
	 * Pemain bisa pass/skip gilirannya saat memainkan kedua kalinya dalam giliran yang sama. 
	 * Saat interpreter menerima command ini, interpreter akan memanggil method ini yang melanjutkan giliran ke pemain selanjutnya
	 */ 
	public void pass(boolean hijiAdvertise){ // unoAdvertise
		match.lewatGiliran(hijiAdvertise); // passTurn
	}

}
