package source.interpreter;
import java.io.InputStream;
import java.util.Scanner;

/**
 * 
 * Kelas ini berfungsi untuk membaca masukan dari input standar (keyboard). 
 * Memprosesnya dengan melakukan split menjadi beberapa fields dan me-return dalam array of strings.
 */ 
public class Input {

	 private Scanner input = null;
	 
	 public Input(InputStream in) {
	 	input = new Scanner(in);
	 }
	 	
	 public String[] bacaFields() {
	 	return this.input.nextLine().trim().split("\\s+");
	 }
	 	
	 public void close() {
	 	if(null != input)
	 		this.input.close();
	 }
}
