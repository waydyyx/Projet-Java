import java.util.ArrayList;

public class Simulation{
    private static final boolean LOG = true;
    private ArrayList<Agent> listeAgent;
    private int nbCourant;
    private int nbPoisson;
    private int nbBM;
    private int nbBP;
    private Port portDepart;
    private Port portArrive;
    private Agent[][] m;
    private Terrain t;

    public Simulation(Terrain t, int nbCourant, int nbPoisson, int nbBM, int nbBP){
        m = new Agent[t.nbLignes][t.nbColonnes];
        this.t = t;
        this.nbCourant = nbCourant;
        this.nbPoisson = nbPoisson;
        this.nbBM = nbBM;
        this.nbBP = nbBP;
        listeAgent = new ArrayList<Agent>();
    }


    private void initRessource(){
        int i = 0;
        String[] nomCour={"^",">","v","<"};
        while (i < nbCourant){
            int dir=(int)(Math.random()*4);
            if (setCase((int)(Math.random() * (t.nbLignes) + 1), (int)(Math.random() * (t.nbLignes) + 1), new Courant(nomCour[dir],dir)))
                i++;
        }
    }

    private void initAgent(){
        int xPortDepart = (int) (Math.random() * (t.nbLignes - 1 + 1) + 1) ;
        portDepart = new Port(xPortDepart, 1, t);
        portArrive = new Port((int) (Math.random() * (t.nbLignes - 1 + 1) + 1), t.nbColonnes, t);
        setCase(portDepart);
        setCase(portArrive);
        portDepart.arriveBateau(new BateauMarchand(xPortDepart, 1, t));
    }

    public void initSimulation(){
        initRessource();
        initAgent();
    }

    public boolean setCase(int x, int y, Object o){
        if (o instanceof Agent){
            if (LOG == true)
                System.out.println("Erreur: impossible de placer un agent avec cette fonction, veuilez utiliser setCase(o)");
            return false;
        }
        if (m[x - 1][y - 1] != null && m[x - 1][y - 1] instanceof Port){
            if (LOG == true)
                System.out.println("Erreur impossible d'ajouter une ressource sur un Port");
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
        Bateau b; 
        if (Math.random() < 1.0/3)
            b = portDepart.departBateau(portArrive);
        else
            b = null;
        if (b != null)
            listeAgent.add(b);
        for (int i = 0; i < listeAgent.size(); i++){
            int x = listeAgent.get(i).getX() - 1; int y = listeAgent.get(i).getY() - 1; // Ancienne position de l'agent;
            listeAgent.get(i).action();
            if (!(m[listeAgent.get(i).getX() - 1][listeAgent.get(i).getY() - 1] instanceof Port)){
                m[listeAgent.get(i).getX() - 1][listeAgent.get(i).getY() - 1] = listeAgent.get(i);
                if (!(m[x][y] instanceof Port))
                    m[x][y] = null;
            }
        }
    }

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

    // public void afficheStatistique(){
    //     for (int i = 0; i < ge)
    // }


    
}