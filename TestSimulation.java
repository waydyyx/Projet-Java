public class TestSimulation{
    public static void main(String args[]) throws SimulationException{
        int nbLignes = 10;
        int nbColonnes = 10;
        int nbPoisson = 20;
        int nbCourant = 5;
        int nbBateauMarchand = 2;
        int nbBateauPirate = 2;
        if (nbPoisson > Config.POISSON_MAX)
            nbPoisson = Config.POISSON_MAX;
        else if (nbPoisson < Config.POISSON_MIN)
            nbPoisson = Config.POISSON_MIN;
        if (nbCourant > Config.COURANT_MAX)
            nbCourant = Config.COURANT_MAX;
        else if (nbCourant < Config.COURANT_MIN)
            nbCourant = Config.COURANT_MIN;

        Simulation s = Simulation.getInstance(nbLignes, nbColonnes, nbCourant, nbPoisson, nbBateauMarchand, nbBateauPirate);
        if (s == null)
            return ;
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