public abstract class Bateau extends Agent{
    protected static int cpt = 0;
    protected int id;

    protected Bateau(String nom, int x, int y, Terrain t){
        super(nom, x, y, t);
        id = ++cpt;
    }

    protected void deplacer(int x, int y){
        if (x - 1 < 0 || x - 1 >= t.nbLignes || y - 1 < 0 || y - 1  < t.nbColonnes)
            return ;
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return (String.format("[id: %d nom: %s position(%d, %d)]", id, nom, x, y));
    }
}