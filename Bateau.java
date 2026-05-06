import java.util.ArrayList;


public abstract class Bateau extends Agent{
    protected static int cpt = 0;
    protected int id;
    protected int nbPiece;
    protected ArrayList<Piece> tabPiece;

    protected Bateau(String nom, int x, int y, Terrain t){
        super(nom, x, y, t);
        tabPiece = new ArrayList<Piece>();
        this.nbPiece = (int)(Math.random() * 5) + 1;
        id = ++cpt;
    }

    protected void deplacer(int x, int y){
        if ((x - 1) < 0 || (x - 1) >= t.nbLignes || (y - 1) < 0 || (y - 1)  >= t.nbColonnes)
            return ;
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return (String.format("[id: %d nom: %s position(%d, %d)]", id, nom, x, y));
    }

    public void getItem(){
        if (t.getCase(x, y) instanceof Piece){
            tabPiece.add((Piece)t.getCase(x,y));
            t.viderCase(x, y);
            if (LOG == true)
                System.out.println(this + " a ramasse une piece");
        }
    }

}