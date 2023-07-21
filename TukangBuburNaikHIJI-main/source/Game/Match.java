package source.Game;
import source.system.Pemain;
import source.tableGame.*;
import source.hiji.KartuHiji;

public class Match implements Game {
	
	private PengaturEfek eControl;
	private PengelolaPemain pControl;
	private Meja meja;
	private static Match match = null;
	private final static int N_Kartu_INI = 7;
	

	/* Constructor*/ 
	private Match(){
		this.meja = Meja.getInstance();
		this.pControl = PengelolaPemain.getInstance();
		this.eControl = new PengaturEfek(this.meja,this.pControl);
	}

	
	public static Match getInstance(){
		if(match == null)
			match = new Match();
		
		return match;
	}
	
	/**
	 * persiapan untuk memulai permainan
	 */
	@Override
	public void init() {
		this.meja.prepareTable();		
	}

	/**
	 * Memulai permainan dengan membagikan kartu
	 */
	@Override
	public void start() {
		if(this.verifyNumPemain() == false){
			System.out.println("Pemain Kurang atau melebihi.");
			return;
		}
		
		this.pembagianKartu();
		
		this.showMatchStatus();
	}
	
	/**
	 * Pemain sekarang mengambil kartu
	 * @return true jika opersai berhasil dan false jika gagal
	 */
	public boolean pemainAmbilKartu(){
		Pemain cPlayer = this.pControl.getCurrent();
		KartuHiji card = this.meja.pullKartu();
		
		return cPlayer.takeCard(card);
		
	}
	
	/**
	 * Verifikasi apakah jumlah pemain valid. Agar valid, gamenya
	 *  harus berisi setidaknya dua pemain dan maksimal sepuluh.  
	 * @return true jika jumlah pemain dalam interval yang valid
	 */
	public boolean verifyNumPemain(){
		if(this.pControl.jumlahPemain() <= 10 && 
				this.pControl.jumlahPemain() > 1)
			return true;
		
		return false;
	}
	
	/**
	 * Membagikan 7 kartu ke semua pemain.
	 * @return 
	 */
	private boolean pembagianKartu(){
		Pemain p = this.pControl.getCurrent();
		int firstID = p.getID();
		int i;
		KartuHiji kartu;

		
		do{
			for(i = 0; i < N_Kartu_INI; i++){
				kartu = this.meja.pullKartu();
				if(kartu == null)
					return false;
				
				p.takeCard(kartu);
			}
			
			pControl.rotate();
			p = this.pControl.getCurrent();
		}while(p.getID() != firstID);
		
		return true;
	}
	
	/**
	 * Pemain saat ini memainkan kartu yang diinginkan.
	 * @param name: nama kartu yang akan dimainkan.
	 * @return 
	 */
	public boolean pemainMainKartu (String nama){
		Pemain cPlayer = this.pControl.getCurrent();
		KartuHiji kartu = cPlayer.mainkanKartu(nama);
		if(kartu == null)
			return false;
		
		if(this.meja.pushKartu(kartu) == false){
			cPlayer.takeCard(kartu);
			return false;
		} 
		return true;
	}
	
	/**
	 * Mengakifkan efek
	 */
	public void terapkanEfek(){
		KartuHiji kartu = this.meja.tampilkanKartuTerakhir();
		kartu.terapkanEfek(eControl);
		
	}
	
	/**
	 * mengaktifkan efek ganti warna
	 * @param wildColor - warna yang dipilih
	 */
	public boolean terapkanEfek(String wildColor){
		if(this.eControl.setWildColor(wildColor) == false)
			return false;
		
		this.terapkanEfek();
		return true;
	}
	
	/**
	 * Selesaikan giliran pemain. Jika pemain hanya memiliki satu kartu lagi,
	 * dan harus memanggil 'HIJI'.
	 * @param advertHiji 
	 */
	public void lewatGiliran (boolean advertHiji){
		if(advertHiji){
			if(pControl.getCurrent().jumlahKartu() != 1){
				this.pemainAmbilKartu();
				this.pemainAmbilKartu();
			} else{
				this.announceHiji();
			}	

		} else{
			if(pControl.getCurrent().jumlahKartu() == 1){
				this.pemainAmbilKartu();
				this.pemainAmbilKartu();
			}
		}
		
		pControl.rotate();
		this.showMatchStatus();
			
	}

	/**
	 * Verifikasi apakah pemain saat ini tidak memiliki kartu lagi.
	 * @return 
	 */
	public boolean kartuHabis(){
		return (0 == pControl.getCurrent().jumlahKartu());
	}
	
	/**
	 * Tampilkan status game 
	 */
	public void showMatchStatus(){
		Pemain p = this.pControl.getCurrent();
		String pStatus;
		int i;
		
		System.out.println("\n---------------------------------------"
				+ "-----------------------------------------");
		
		pStatus = this.pControl.getStatusPemain();
		if(pStatus.length() > 80){
			for(i = 0; i < pStatus.length()/80; i++)
				System.out.println(pStatus.substring(i*80, i*80 + 80));
			System.out.println(pStatus.substring(i*80));
		} else{
			System.out.println(pStatus);
		}
		
		System.out.print("KARTU TERAKHIR DIMAINKAN : " 
				+ this.meja.tampilkanKartuTerakhir().toString() + "\t\t\t");
		System.out.println(meja.jumlahKartuPadaDeck() + " KARTU TERSISA DI DECK");
		System.out.println("---------------------------------------"
				+ "-----------------------------------------");
		System.out.print(p.getName() + "'S GILIRAN. ");
		this.showHandStatus();		
	}
	
	/**
	 * Tunjukkan status kartu yang ada di tangan pemain.
	 */
	public void showHandStatus(){
		Pemain p = pControl.getCurrent();
		System.out.println("Pilih 1 Kartu :");
		p.printHand();
		System.out.println("---------------------------------------"
				+ "-----------------------------------------");
	}
	
	/**
	 * Pesan terakhir yang menginformasikan akhir permainan dan pemenangnya.
	 */
	@Override
	public void finish() {
		Pemain winner = pControl.cariPemenang();
		
		if(winner == null)
			System.out.println("\nTIDAK ADA PEMENANG. =(");
		else
			System.out.println("\nSELAMAT Anda Adalah PEMENANGNYA " + winner.getName()
					 + "! \\o/\\o/\\o/\n KAMU MENGALAHKAN " + (pControl.jumlahPemain()-1)
					 + " MUSUH.");
		
		System.out.println("\nTERIMA KASIH SUDAH MEMAINKAN. "
				+ "\nCOME BACK ANYTIME YOU WANT.\n");
		 
	}
	
	/**
	 * Gabut pengen coba ini
	 * gk gabut deng banyak tubes tapi pengen bikin aja coba2
	 */
	private void announceHiji(){
		/*menyusul*/
		System.out.println(" ___                                                 ___");
		System.out.println("|   |_______________________________________________|   |");
		System.out.println("|___|                                               |___|");
		System.out.println("  |                                                   |");
		System.out.println("  |    ____     ____    _____   _______   _____       |");
		System.out.println("  |   |    |   |    |  |     | |___    | |     |      |");
		System.out.println("  |   |    |___|    |  |     |     |   | |     |      |");
		System.out.println("  |   |             |  |     |     |   | |     |      |");
		System.out.println("  |   |     ___     |  |     |  ___|   | |     |      |");
		System.out.println("  |   |____|   |____|  |_____| |_______| |_____|      |");
		System.out.println(" ___                                                 ___");
		System.out.println("|   |_______________________________________________|   |");
		System.out.println("|___|                                               |___|");

	}
}