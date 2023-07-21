package source.system;


/**
 * Kelas ini adalah representasi dari ID pemain game HIJI
 * ID tidak bisa diubah karena ID telah di set oleh konstruktor
 */
public class IDPemain<Type> {
    private Type ID;
    public IDPemain(Type ID){
        this.ID = ID;
    }

    public Type getID(){
        return this.ID;
    }
}
