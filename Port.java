import java.util.ArrayList;

public class Port extends Agent{
    private int id;
    private static int cpt = 0;
    private ArrayList<BateauMarchand> listeBM;
    private int nbBateau;

    public Port(String nom, int x, int y, Terrain t, Agent[][] m){
        super(nom, x, y, t, m);
        listeBM = new ArrayList<BateauMarchand>();
        nbBateau = 0;
        id = ++cpt;
        if (LOG)
            System.out.println(String.format("Creation de %s", this));

    }

    public Port(Port p){
        this(p.nom, p.x, p.y, p.t, p.m);
    }

    public Port(int x, int y, Terrain t, Agent[][] m){
        this("Port" + (cpt+1), x, y, t, m);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void arriveBateau(BateauMarchand b){
        listeBM.add(b);
        b.setPDest(null);
        b.deplacer(x, y);
        nbBateau++;
        if (LOG == true)
            System.out.println("Arrive de " + b);
    }

    public BateauMarchand departBateau(Port pDest){
        if (nbBateau <= 0)
        {
            if (LOG)
                System.out.println("Il n'y as plus de bateau dans le port");
            return null;
        }
        BateauMarchand b = listeBM.remove(--nbBateau);
        b.setPDest(pDest);
        if (LOG)
            System.out.println("Depart de " + b);
        // b.deplaceVersPort();
        return b;
    }

    public String toString(){
        return String.format("Port[id: %d nom: %s position: (%d, %d) nbBateau: %d]", id, nom, x, y, nbBateau);
    }

    public ArrayList<BateauMarchand> getBateauMarchands(){
        return (listeBM);
    }

    public void afficheBateaux(){
        for (int i = 0; i < nbBateau; i++){
            System.out.println(listeBM.get(i));
        }
    }

    public void action(){

    }
}
