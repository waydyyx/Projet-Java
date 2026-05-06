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
            if (setCase((int)(Math.random() * (t.nbLignes) + 1), (int)(Math.random() * (t.nbLignes) + 1), new Courant(nomCour[dir],dir))){
                i++;
            }
        }
        while (i < nbPoisson){
            if (setCase((int)(Math.random() * (t.nbLignes) + 1), (int)(Math.random() * (t.nbLignes) + 1), new Poisson("Poisson")))
                i++;
        }
    }

    private void initAgent(){
        int xPortDepart = (int) (Math.random() * (t.nbLignes - 1 + 1) + 1);
        int xPortArrive = (int) (Math.random() * (t.nbLignes - 1 + 1) + 1);
        portDepart = new Port(xPortDepart, 1, t, m);
        portArrive = new Port(xPortArrive, t.nbColonnes, t, m);
        setCase(portDepart);
        setCase(portArrive);
        portDepart.arriveBateau(new BateauMarchand(xPortDepart, 1, t, m));
        portDepart.arriveBateau(new BateauMarchand("caca", xPortDepart, 1, t, m));
        BateauPirate p = new BateauPirate(xPortArrive, 5, t,m);
        setCase(p);
        listeAgent.add(p);
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

    private void updateAgent(){
    if (Math.random() < 1.0/3) {
        Bateau b = portDepart.departBateau(portArrive);
        if (b != null)
            listeAgent.add(b);
    }

    for (int i = 0; i < listeAgent.size(); i++){
        Agent agent = listeAgent.get(i);

        int oldX = agent.getX() - 1;
        int oldY = agent.getY() - 1;

        agent.action();

        int newX = agent.getX() - 1;
        int newY = agent.getY() - 1;

        // Placer l'agent à sa nouvelle position si ce n'est pas un Port
        if (!(m[newX][newY] instanceof Port)){
            m[newX][newY] = agent;
        }

        // Effacer l'ancienne case SEULEMENT si l'agent a bougé,
        // que l'ancienne case n'est pas un Port,
        // ET que l'ancienne case contient encore CET agent (pas un autre qui s'y est déplacé)
        if ((oldX != newX || oldY != newY)
                && !(m[oldX][oldY] instanceof Port)
                && m[oldX][oldY] == agent){
            m[oldX][oldY] = null;
        }

        // Arrivée au port destination
        if (agent instanceof BateauMarchand && m[newX][newY] == portArrive){
            portArrive.arriveBateau((BateauMarchand) agent);
            m[newX][newY] = portArrive;
            listeAgent.remove(i);
            i--;
            continue; // ← important : ne pas tomber dans le bloc "coulé" après
        }

        // Bateau coulé : retirer de la matrice ET de la liste
        if (agent instanceof BateauMarchand && ((BateauMarchand) agent).getCoule()){
            m[newX][newY] = null;
            listeAgent.remove(i);
            i--;
        }
    }
}

    private void updateRessource(){
        for (int i = 0; i < t.lesRessources().size(); i++){
            if (t.lesRessources().get(i) instanceof Poisson){
                Poisson p = (Poisson)t.lesRessources().get(i);
                System.out.println(String.format("Quantite poisson avant: %d", p.getNbPoisson()));
                p.evoluer();
                System.out.println(String.format("Quantite poisson apres: %d", p.getNbPoisson()));
            }
        }
    }

    public void prochainTour(){
        updateAgent();
        updateRessource();
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