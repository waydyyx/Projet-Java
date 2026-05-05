public class BateauPirate extends Bateau{

    public BateauPirate(String nom, int x, int y, Terrain t){
        super(nom, x, y, t);
    }

    public BateauPirate(int x, int y, Terrain t){
        this("BP"+(cpt+1), x, y, t);
    }

    public String toString(){
        return ("BateauPirate" + super.toString());
    }

    public String getNom(){
        return (nom);
    }
}