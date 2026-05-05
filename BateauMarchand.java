import java.util.ArrayList;

public class BateauMarchand extends Bateau{
    private Port pDest;
    private int nbPiece;
    private ArrayList<Piece> tabPiece;


    public BateauMarchand(String nom, int x, int y, Terrain t, Port pDest){
        super(nom, x, y, t);
        this.pDest = pDest;
        this.nbPiece = (int)(Math.random() * 5) + 1;
        tabPiece = new ArrayList<Piece>();
        // System.out.println(nom);
    }

    public BateauMarchand(int x, int y, Terrain t){
        this("BM" + (cpt+1), x, y, t, null);
    }


    public void setPDest(Port pDest){
        this.pDest = pDest;
    }

    public void getItem(){
        if (t.getCase(x, y) instanceof Piece){
            tabPiece.add((Piece)t.getCase(x,y));
            t.viderCase(x, y);
        }
    }


    public String toString(){
        if (pDest == null)
            return ("BateauMarchand" + super.toString() + String.format("[PortDest: %s]", "null"));
        return ("BateauMarchand" + super.toString() + String.format("[PortDest: %s]", pDest.getNom()));
    }

}