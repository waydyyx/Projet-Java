import java.util.ArrayList;

public class Simulation{
    private static final boolean LOG = true;
    // private ArrayList<Agent> listeAgent;
    private int nbPieces;
    private int nbProvision;
    private int nbBM;
    private int nbBP;
    private Port portDepart;
    private Port portArrive;
    private Agent[][] m;
    private Terrain t;

    public Simulation(Terrain t, int nbPieces, int nbProvision, int nbBM, int nbBP){
        m = new Agent[t.nbLignes][t.nbColonnes];
        this.t = t;
        this.nbPieces = nbPieces;
        this.nbProvision = nbProvision;
        this.nbBM = nbBM;
        this.nbBP = nbBP;
    }

    public void initSimulation(){
        setCase(new Port((int) (Math.random() * (t.nbLignes - 1 + 1) + 1), 1, t));
        setCase(new Port((int) (Math.random() * (t.nbLignes - 1 + 1) + 1), t.nbColonnes, t));
        setCase(new BateauMarchand(1, 1, t));
        setCase(1, 1, (Object)new Ressource("piece", 4));
    }


    public boolean setCase(int x, int y, Object o){
        if (o instanceof Agent){
            if (LOG == true)
                System.out.println("Erreur: impossible de placer un agent avec cette fonction, veuilez utiliser setCase(o)");
            return false;
        }
        return t.setCase(x, y, (Ressource)o);
    }

    public boolean setCase(Object o){
        if (o instanceof Ressource){
            if (LOG == true)
                System.out.println("Erreur: impossible de placer une ressource avec cette fonction, veuilez utiliser setCase(x, y, o)");
            return false;
        }
        if (!(((Agent)o).getX() - 1 < 0 || ((Agent)o).getX() - 1 >= t.nbLignes || ((Agent)o).getY() - 1 < 0 || ((Agent)o).getY() - 1 >= t.nbColonnes)){
            if (m[((Agent)o).getX() - 1][((Agent)o).getY() - 1] == null){
                m[((Agent)o).getX() - 1][((Agent)o).getY() - 1] = (Agent)o;
                if (LOG == true)
                    System.out.println(String.format("Ajout de %s effectue", o));
                return true;
            }
            if (m[((Agent)o).getX() - 1][((Agent)o).getY() - 1] instanceof Port && o instanceof BateauMarchand){
                ((Port)m[((Agent)o).getX() - 1][((Agent)o).getY() - 1]).arriveBateau((BateauMarchand)o);
                return true;
            }
            if (LOG == true)
                System.out.println(String.format("La case (%d, %d) n'est pas vide", ((Agent)o).getX(), ((Agent)o).getY()));
            return false;

        }
        if (LOG == true)
            System.out.println("Les coordonnees sont hors du terrain");
        return false;
        
    }

    public int nbCaseVide(){
        int nbCaseVide = 0;
        for (int i = 0; i < t.nbLignes; i++){
            for (int j = 0; j < t.nbColonnes; j++){
                if (t.getCase(i + 1, j + 1) == null && m[i][j] == null)
                    nbCaseVide++;
            }
        }
        return nbCaseVide;
    }

    public void prochainTour(){

    }

    // public boolean setCase(int x, int y, Agent a){
    //     if (x - 1 < 0 || x - 1 >= t.nbLignes || y - 1 < 0 || y - 1 >= t.nbColonnes)
    //     {
    //         System.out.println("Erreur de coordoonnes");
    //         return false;
    //     }
    //     if (m[x - 1][y - 1] != null)
    //     {
    //         System.out.println(String.format("La case (%d, %d) est occupe", x, y));
    //         return false;
    //     }
    //     m[x - 1][y - 1] = a;
    //     ((Agent)a).setPosition(x - 1, y - 1);
    //     return true;
    // }

    private void afficheLigne(int nbColones, int nbTiret){
        for (int i = 0; i < nbColones; i++){
            System.out.print(":");
            for (int j = 0; j < nbTiret; j++){
                System.out.print("-");
            }
        }
        System.out.println(":");
    }

    public void afficheAgent(int n){
        for (int i = 0; i < m.length; i++){
            System.out.print("\n");
            afficheLigne(m[0].length, n);
            System.out.print("|");
            for (int j = 0; j < m[i].length; j++){
                if (m[i][j] == null){
                    for (int x = 0; x < n; x++)
                        System.out.print(" ");
                }
                else if (n >=  m[i][j].getNom().length()){
                    System.out.print(m[i][j].getNom());
                    for (int x = 0; x < n - m[i][j].getNom().length(); x++)
                        System.out.print(" ");
                }
                else{
                    System.out.print(m[i][j].getNom().substring(0, n));
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");
        afficheLigne(m[0].length, n);
    }

    public void afficheRessource(int n){
        t.afficher(n);
    }


    
}