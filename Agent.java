public abstract class Agent{
    protected static final boolean LOG = true;
    protected String nom;
    protected int x;
    protected int y;
    protected Terrain t;

    protected Agent(String nom, int x, int y, Terrain t){
        this.nom = nom;
        this.t = t;

        if (x - 1 < 0)
            this.x = 1;
        else if (x - 1 >= t.nbLignes)
            this.x = t.nbLignes;
        else 
            this.x = x;

        if (y - 1 < 0)
            this.y = 1;
        else if (y - 1  >= t.nbColonnes)
            this.y = t.nbColonnes;
        else
            this.y = y;
    }

    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

    public String getNom(){
        return (nom);
    }
}