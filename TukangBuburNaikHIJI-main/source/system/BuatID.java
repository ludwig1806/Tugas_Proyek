package source.system;



public class BuatID {
    static BuatID buat = null;
    int id_pengguna;
    int limit;
    
    private BuatID(){
        this.id_pengguna = 1;
        this.limit = 100;
    }

    /**
     * Membuat ID yang unik dengan limit yang terdefinisi
     */
    public static BuatID getInstance(){
        if (buat == null){
            buat = new BuatID();
        }
        return buat;
    }

    public IDPemain<Integer> getID(){
        if (this.id_pengguna > this.limit){
            throw new IndexOutOfBoundsException("ID melebihi batas");
        }
        IDPemain<Integer> id = new IDPemain<Integer> (this.id_pengguna);
        this.id_pengguna++;
        return id;
    }

    public int getIDTerakhir(){
        return(this.id_pengguna - 1);
    }

    public void resetID(){
        this.id_pengguna = 1;
    }
    
    public void setLimit(int batas){
        if (batas >= this.id_pengguna){
            this.limit = batas;
        }
    }

    public int getLim(){
        return this.limit;
    }
}
