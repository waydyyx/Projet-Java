public class TestSimulation{
    public static void main(String args[]) throws SimulationException{
        int nbLignes = 10;
        int nbColonnes = 10;
        int nbPoisson = 20;
        int nbCourant = 5;
        if (nbPoisson > Config.POISSON_MAX)
            nbPoisson = Config.POISSON_MAX;
        else if (nbPoisson < Config.POISSON_MIN)
            nbPoisson = Config.POISSON_MIN;
        if (nbCourant > Config.COURANT_MAX)
            nbCourant = Config.COURANT_MAX;
        else if (nbCourant < Config.COURANT_MIN)
            nbCourant = Config.COURANT_MIN;

        Config.validerTerrain(nbLignes, nbColonnes);
        Simulation s = Simulation.getInstance(new Terrain(10, 10), 5, 20, 2, 2);
        s.initSimulation();
        for (int i = 0; i < 20; i++){
            s.prochainTour();          
            s.afficheRessource(4);
            s.afficheAgent(4);
            try {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // s.afficheStatistique();
        // System.out.println(s.nbCaseVide());
    }
}