import java.util.ArrayList;

public class BateauMarchand extends Bateau{
    private Port pDest;
    private int nbPoisson;
    private boolean coule=false;


    public BateauMarchand(String nom, int x, int y, Terrain t, Port pDest, Agent[][] m){
        super(nom, x, y, t, m);
        this.pDest = pDest;
        this.nbPoisson = 0;
        
        // System.out.println(nom);
    }

    public BateauMarchand(String nom, int x, int y, Terrain t, Agent[][] m){
        this(nom, x, y, t, null, m);
    }
    public BateauMarchand(int x, int y, Terrain t, Agent[][] m){
        this("BM" + (cpt+1), x, y, t, null, m);
    }


    public void setPDest(Port pDest){
        this.pDest = pDest;
    }

    public void deplaceVersPort(){
        int DistX = x - pDest.getX();
        int DistY = y - pDest.getY();

        if (DistX == 0 && DistY == 0)
        {
            if (LOG == true)
                System.out.println(this + " est arrive a destination");
            return;
        }
        if (DistY == 0){
            if (DistX > 0)
                this.deplacer(this.x-1,this.y);
            else
                this.deplacer(this.x+1,this.y);
            return ;
        }
        if (DistX == 0){
            if (DistY > 0)
                this.deplacer(this.x,this.y-1);
            else
                this.deplacer(this.x,this.y+1);
            return ;
        }
        if (Math.random() < 0.5){
            if (DistX > 0)
                this.deplacer(this.x-1,this.y);
            else
                this.deplacer(this.x+1,this.y);
        }
        else{
            if (DistY > 0)
                this.deplacer(this.x,this.y-1);
            else
                this.deplacer(this.x,this.y+1);
        }
    }

    private void pecher(){
        Poisson p = (Poisson)t.getCase(x,y);
        int nbPoissonPeche = (int)(Math.random() * p.getNbPoisson());
        nbPoisson += nbPoissonPeche;
        p.poissonPecher(nbPoissonPeche);
        if (p.getNbPoisson() == 0)
            t.viderCase(x, y);
        if (LOG == true)
            System.out.println(this + String.format(" a peche %d poisson sur %d poisson", nbPoissonPeche, p.getNbPoisson() + nbPoissonPeche));
    }
    public void agirCase(){
        if (t.getCase(x, y) instanceof Poisson){
            pecher();
        }
    }

    public void action(){
        deplaceVersPort();
        agirCase();
    }

    public String toString(){
        if (pDest == null)
            return ("BateauMarchand" + super.toString() + String.format("[PortDest: %s]", "null"));
        return ("BateauMarchand" + super.toString() + String.format("[PortDest: %s]", pDest.getNom()));
    }

    public void couler(){
        coule=true;
        System.out.println("bateau coulé");
    }

    public boolean getCoule(){
        return coule;
    }
}