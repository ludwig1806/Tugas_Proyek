package source.interpreter;

/*
 * Berfungsi untuk identifikasi command yang diberikan oleh player yang sedang berada
 * dalam giliran dan memanggil method dari kelas command untuk melakukannya dengan benar
 */
public class Interpreter {
	
	private int State = 0;
	private Input input = new Input(System.in);
	private Commands command = new Commands();
	
	/**
	 * Method ini membaca sebuah input dengan memanggil method "bacaFields"
	 * dari kelas Input, membandingkan panjangnya apakah bisa diterima
	 * dan memilih command apa yang harus dipanggil dari kelas Command
	 * @return nilai Boolean. jika permainan selesai return true
	 */
	public boolean bacaCommands(){
		String[] fields;
		
		while(true){
			System.out.print("> ");
			fields = input.bacaFields();
			
			if(fields.length < 1 || fields.length > 4){ // 5 karena kalau mau multiple discard => max field case: MAIN YELLOW7 2 HIJI GAME
				//System.out.println("INPUT SALAH, COBA LAGI"); // apus aja
				continue;
			}
			
			for(int i = 0; i < fields.length; i++)
				fields[i] = fields[i].toUpperCase();
			
			if(fields[0].equals("KELUAR")) // EXIT
				return false;
			
			switch(this.State){
				// Cek main pertama giliran (hanya bisa "MAIN" atau "DRAW").
				case 0:
					switch(fields[0]){		
						case "MAIN": // PLAY
							// Verifikasi jika ada nama dari kartu
							if(fields.length < 2){
								System.out.println("KAMU LUPA UNTUK "
										+ "MEMILIH SEBUAH KARTU BAMBANG.");
								break;
							}
							
							if(this.command.mainKartu(fields) == false) {
								System.out.println("COBA LAGI DEH.");
								
							} else{
								if(this.command.verifikasiGameSelesai())
									return true;
								
								if(fields.length == 4 && 
										fields[2].equals("HIJI"))
									this.command.pass(true);
								else if(fields.length == 3 && 
										fields[2].equals("HIJI"))
									this.command.pass(true);
								else
									this.command.pass(false);
							}
							
							this.State = 0;
							break;
							
						case "DRAW":
							this.command.draw();
							this.State = 1;
							break;
							
						case "PASS":
							System.out.println("YOU SHALL NOT PASS.");
							this.State = 0;
							break;
						
						case "HELP":
							System.out.println("LIST COMMANDS:");
							System.out.println("- MAIN <Nama Kartu> : Mainin kartu yang ada di tangan");
							System.out.println("ex: MAIN RED1");
							System.out.println("- MAIN WILD/WILD+4 <Warna> : Mainin kartu wildcard");
							System.out.println("ex: MAIN WILD/WILD+4 BLUE");
							System.out.println("- DRAW : Mengambil kartu dari deck");
							System.out.println("- PASS : Skip giliran ini. Hanya bisa dilakukan setelah melakukan DRAW");
							break;
							
						default:
							System.out.println("NULIS APAYA? SAYA GANGERTI.");
							break;
					}
					break;
				// Cek main kedua giliran (hanya bisa "MAIN" atau "DRAW").
				case 1:
					switch(fields[0].toUpperCase()){
						case "MAIN":
							if(this.command.mainKartu(fields) == false){
								System.out.println("COBA LAGI DEH");
							}else {
								this.State = 0;

								if(this.command.verifikasiGameSelesai())
									return true;
								
								if(fields.length == 4 && 
										fields[2].equals("HIJI"))
									this.command.pass(true);
								else if(fields.length == 3 && 
										fields[2].equals("HIJI"))
									this.command.pass(true);
								else
									this.command.pass(false);
							}
							break;
														
						case "PASS":
							this.command.pass(false);
							this.State = 0;
							break;
							
						case "DRAW":
							System.out.println("MAAF, TAPI KAMU HANYA BISA MELAKUKANNYA"
									+ " SEKALI TIAP GILIRAN.");
							break;
							
						default:
							System.out.println("NULIS APAYA? SAYA GANGERTI. "
									+ "COBA LAGI YA.");
							break;
					}
					break;
			}
		}
	}
}
